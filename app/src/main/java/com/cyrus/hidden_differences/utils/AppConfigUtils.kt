package com.cyrus.hidden_differences.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.cyrus.hidden_differences.HiddenDifferencesApp
import com.cyrus.hidden_differences.application.ApplicationContext.sessionContext
import com.cyrus.hidden_differences.ext.CoroutineExt
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object AppConfigUtils {

	lateinit var displayMetrics: DisplayMetrics

	val widthScreen: Int
		get() = displayMetrics.widthPixels

	val heightScreen: Int
		get() = displayMetrics.heightPixels

	private fun getScreen(context: Context): DisplayMetrics {
		val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val dm = DisplayMetrics()
		windowManager.defaultDisplay.getRealMetrics(dm)
		return dm
	}

	fun setColorText(textView: TextView, colorCode: String) {
		val width = textView.paint.measureText(textView.text.toString())
		val textShader = LinearGradient(
			width, 0f, textView.textSize, 0f,
			intArrayOf(
				Color.parseColor(colorCode),
				Color.parseColor(colorCode),
				Color.parseColor(colorCode)
			),
			null, Shader.TileMode.CLAMP
		)
		textView.paint.shader = textShader
	}

	fun getStringFromAssets(name: String): String? {
		return try {
			HiddenDifferencesApp.instance.run { assets.open(name).readTextAndClose() }
		} catch (e: IOException) {
			null
		}
	}

	fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String? {
		return bufferedReader(charset).use { it.readText() }
	}

	fun dpToPx(dp: Float): Int =
		TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()

	fun dp2Px(dp: Float): Float =
		TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)

	fun pxToDp(px: Int): Float =
		px / displayMetrics.density

	fun spToPx(sp: Float): Float =
		TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, displayMetrics)

	fun getNavigationBarHeight(context: Context): Int {
		val resources: Resources = context.resources
		val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
		return if (resourceId > 0) {
			resources.getDimensionPixelSize(resourceId)
		} else 0
	}

	fun pushDownAnimClick(view: View, callbackAction: () -> Unit) {
		val defaultInterval = 1000
		if (SystemClock.elapsedRealtime() - sessionContext.lastTimeClicked > defaultInterval) {
			// Scale down to 90% of the original size
			val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f)
			val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f)

			scaleDownX.duration = 100
			scaleDownY.duration = 100

			scaleDownX.start()
			scaleDownY.start()

			scaleDownX.addListener(object : Animator.AnimatorListener {
				override fun onAnimationStart(animation: Animator) {}

				override fun onAnimationEnd(animation: Animator) {
					val scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 1f)
					val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1f)

					scaleUpX.duration = 100
					scaleUpY.duration = 100

					scaleUpX.start()
					scaleUpY.start()
					CoroutineExt.runOnMainAfterDelay(112) {
						callbackAction.invoke()
					}
					sessionContext.lastTimeClicked = SystemClock.elapsedRealtime()
				}

				override fun onAnimationCancel(animation: Animator) {}

				override fun onAnimationRepeat(animation: Animator) {}
			})
		}
	}
}
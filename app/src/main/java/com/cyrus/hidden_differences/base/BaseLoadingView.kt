package com.cyrus.hidden_differences.base

import android.view.View
import com.cyrus.hidden_differences.ext.CoroutineExt
import com.cyrus.hidden_differences.ext.gone
import com.cyrus.hidden_differences.ext.visible
import com.cyrus.hidden_differences.utils.CommonUtils
import com.facebook.shimmer.ShimmerFrameLayout

interface BaseLoadingView {
    fun getLayoutParent(): View
    fun getLayoutShimmer(): ShimmerFrameLayout

    fun setupLoading(isLoading: Boolean, timeDelayLoading: Long, callback: (() -> Unit?)? = null) {
        if (isLoading) {
            startLoading()
        }
        if (!isLoading) {
            callback?.invoke()
            CoroutineExt.runOnMainAfterDelay(if (timeDelayLoading > 0) timeDelayLoading + 500.toLong() else 500) {
                stopLoading(
                    callback
                )
            }
        }
    }

    fun startLoading() {
        getLayoutParent().visible()
        getLayoutParent().animate().alpha(1F)
        getLayoutShimmer().apply {
            startShimmer()
            showShimmer(true)
        }
    }

    fun stopLoading(callback: (() -> Unit?)? = null) {
        getLayoutParent().animate().alpha(0F)
        getLayoutShimmer().apply {
            stopShimmer()
            hideShimmer()
            getLayoutParent().gone()
        }
    }
}
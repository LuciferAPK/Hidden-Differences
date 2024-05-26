package com.cyrus.hidden_differences.ui.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.base.BaseDialog
import com.cyrus.hidden_differences.databinding.DialogEarnItemBinding

class EarnItemDialog : BaseDialog<DialogEarnItemBinding>() {

    private var onClickWatchAds: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun getGravityForDialog(): Int = Gravity.CENTER

    override fun getLayoutResource(): Int = R.layout.dialog_earn_item

    override fun init(saveInstanceState: Bundle?, view: View?) {
        this.isCancelable = true
    }

    override fun setUp(view: View?) {
        initListener()
    }

    override fun onStart() {
        super.onStart()
        if (dialog?.window != null) {
            dialog?.window?.setLayout(
                requireActivity().window.decorView.width * 90 / 100,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun initListener() {
        binding.btnWatchAds.setOnClickListener {
            onClickWatchAds?.invoke()
            dismissAllowingStateLoss()
        }
    }

    fun setOnClickWatchAds(onClickWatchAds: (() -> Unit)? = null) {
        this.onClickWatchAds = onClickWatchAds
    }
}
package com.cyrus.hidden_differences.ui.activity.gameplay

import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.base.BaseActivity
import com.cyrus.hidden_differences.base.BaseLoadingView
import com.cyrus.hidden_differences.databinding.ActivityGamePlayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamePlayActivity : BaseActivity<ActivityGamePlayBinding>() {

    override fun getContentLayout(): Int = R.layout.activity_game_play

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initListener() {
        TODO("Not yet implemented")
    }

    override fun observerLiveData() {
        TODO("Not yet implemented")
    }

    override fun getLayoutLoading(): BaseLoadingView? = null

}
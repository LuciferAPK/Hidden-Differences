package com.cyrus.hidden_differences.ui.activity.main

import android.util.Log
import androidx.activity.viewModels
import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.ads.banner.BannerAdsManager
import com.cyrus.hidden_differences.ads.reward.RewardedAdsManager
import com.cyrus.hidden_differences.base.BaseActivity
import com.cyrus.hidden_differences.base.BaseLoadingView
import com.cyrus.hidden_differences.data.model.GameType
import com.cyrus.hidden_differences.databinding.ActivityMainBinding
import com.cyrus.hidden_differences.ui.adapter.GameAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var bannerAdsManager: BannerAdsManager

    @Inject
    lateinit var rewardedAdsManager: RewardedAdsManager

    /**ViewModel*/
    val mainViewModel: MainViewModel by viewModels()

    var gameAdapter: GameAdapter? = null
    var listGameType: ArrayList<GameType> = arrayListOf()

    var onDismissRewardCallBack: ((Boolean) -> Unit)? = { _ ->
        
    }

    override fun getContentLayout(): Int = R.layout.activity_main

    override fun initView() {
        getNumberHeart()
        loadRewardAds()
        loadBannerAds()
        setupListData()
        setupAdapter()
    }

    override fun initListener() {
        binding.btnHighScore.setOnClickListener { onClickBtnHighScore() }
        binding.imgGif.setOnClickListener { onClickEarnItem() }
        viewpagerListener()
    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): BaseLoadingView? = null
}
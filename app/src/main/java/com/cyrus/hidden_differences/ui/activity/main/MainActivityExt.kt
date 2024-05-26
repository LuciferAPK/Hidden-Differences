package com.cyrus.hidden_differences.ui.activity.main

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.data.model.GameType
import com.cyrus.hidden_differences.enums.GameViewType
import com.cyrus.hidden_differences.navigation.NavigationManager
import com.cyrus.hidden_differences.ui.adapter.GameAdapter
import com.cyrus.hidden_differences.ui.dialog.EarnItemDialog
import com.cyrus.hidden_differences.utils.AppConfigUtils.pushDownAnimClick
import kotlin.math.abs

fun MainActivity.loadBannerAds() {
    bannerAdsManager.loadAdsBanner(
        parentViewAds = binding.layoutBannerAds,
        activity = this
    )
}

fun MainActivity.loadRewardAds() {
    rewardedAdsManager.onDismissRewardCallback = onDismissRewardCallBack
    rewardedAdsManager.loadRewardedAds(2)
}

fun MainActivity.setupListData() {
    listGameType.add(GameType(type = GameViewType.TAB_TYPE.viewType, gameType = GameViewType.TAB_TYPE.nameGame, isLock = false))
    listGameType.add(GameType(type = GameViewType.TAP_NUMBER_TYPE.viewType, gameType = GameViewType.TAP_NUMBER_TYPE.nameGame, isLock = false))
    listGameType.add(GameType(type = GameViewType.TAP_TEXT_TYPE.viewType, gameType = GameViewType.TAP_TEXT_TYPE.nameGame, isLock = false))
    listGameType.add(GameType(type = GameViewType.TAP_IMAGE_TYPE.viewType, gameType = GameViewType.TAP_IMAGE_TYPE.nameGame, isLock = false))
    listGameType.add(GameType(type = GameViewType.TAP_FRUIT_TYPE.viewType, gameType = GameViewType.TAP_FRUIT_TYPE.nameGame, isLock = false))
    listGameType.add(GameType(type = GameViewType.TAP_ULTRA_HARD_TYPE.viewType, gameType = GameViewType.TAP_ULTRA_HARD_TYPE.nameGame, isLock = false))
}

fun MainActivity.getNumberHeart() {
    binding.numberHeart.text = mainViewModel.getNumberHeart().toString()
}

fun MainActivity.onClickBtnHighScore() {
    pushDownAnimClick(binding.btnHighScore) {
        NavigationManager.navigationToHighScoreFragment(supportFragmentManager)
    }
}

fun MainActivity.onClickEarnItem() {
    pushDownAnimClick(binding.imgGif) {
        val earnItemDialog = EarnItemDialog()
        earnItemDialog.apply {
            setOnClickWatchAds {
                if (!rewardedAdsManager.isRewardedAdsNull()) {
                    rewardedAdsManager.show(this@onClickEarnItem)
                }
            }
        }
        earnItemDialog.show(supportFragmentManager, earnItemDialog.tag)
    }
}

fun MainActivity.setupAdapter() {
    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(MarginPageTransformer(70))
    compositePageTransformer.addTransformer { page, position ->
        val r = 1 - abs(position)
        page.scaleY = 0.85f + r * 0.15f
    }
    gameAdapter = GameAdapter(onClickItem = {

    })
    binding.viewPager.apply {
        adapter = gameAdapter
        clipToOutline = false
        clipChildren = false
        offscreenPageLimit = 3
        val recyclerView = getChildAt(0) as RecyclerView
        recyclerView.apply {
            setPadding(
                resources.getDimensionPixelOffset(R.dimen.dp70),
                0,
                resources.getDimensionPixelOffset(R.dimen.dp70),
                0
            )
            clipToPadding = false
        }
        setPageTransformer(compositePageTransformer)
    }
    gameAdapter?.submitList(listGameType)
}

fun MainActivity.viewpagerListener() {
    binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            when (position) {
                GameViewType.TAB_TYPE.viewType -> binding.txtNameGame.text = getString(R.string.tap_color)
                GameViewType.TAP_NUMBER_TYPE.viewType -> binding.txtNameGame.text = getString(R.string.tap_number)
                GameViewType.TAP_TEXT_TYPE.viewType -> binding.txtNameGame.text = getString(R.string.tap_text)
                GameViewType.TAP_IMAGE_TYPE.viewType -> binding.txtNameGame.text = getString(R.string.tap_image)
                GameViewType.TAP_FRUIT_TYPE.viewType -> binding.txtNameGame.text = getString(R.string.tap_fruit)
                GameViewType.TAP_ULTRA_HARD_TYPE.viewType -> binding.txtNameGame.text = getString(R.string.ultra_hard)
            }
        }
    })
}
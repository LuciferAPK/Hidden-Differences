package com.cyrus.hidden_differences.ads.reward

import androidx.appcompat.app.AppCompatActivity
import com.cyrus.hidden_differences.HiddenDifferencesApp
import com.cyrus.hidden_differences.ads.AdsManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedAdsManager {
    private val TAG = "RewardedAdsManager"
    private var myRewardedAd: RewardedAd? = null
    private var isRewardEarned = false
    private var isLoading = false
    var onDismissRewardCallback: ((Boolean) -> Unit)? = null

    private val onUserEarnedListener =
        OnUserEarnedRewardListener {
            isRewardEarned = true
        }

    fun isRewardedAdsNull(): Boolean = myRewardedAd == null

    private val fullScreenCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            if (isRewardEarned) onDismissRewardCallback?.invoke(true)
            else onDismissRewardCallback?.invoke(false)
            myRewardedAd = null
            loadRewardedAds(2)
        }

        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
            myRewardedAd = null
            loadRewardedAds(2)
        }

        override fun onAdShowedFullScreenContent() {

        }
    }

    fun loadRewardedAds(retry: Int) {
        if (!HiddenDifferencesApp.instance.googleMobileAdsConsentManager.canRequestAds) return
        if (isLoading) return
        isLoading = true
        var mRetry = retry
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(
            HiddenDifferencesApp.instance,
            AdsManager.REWARD_ADS_ID,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    myRewardedAd = rewardedAd
                    isLoading = false
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    isLoading = false
                    if (mRetry != 0) {
                        loadRewardedAds(--mRetry)
                    }
                }
            })
    }

    fun show(activity: AppCompatActivity?) {
        if (myRewardedAd != null) {
            if (activity == null) return
            isRewardEarned = false
            myRewardedAd?.fullScreenContentCallback = fullScreenCallback
            myRewardedAd?.show(activity, onUserEarnedListener)
        }
    }
}
package com.cyrus.hidden_differences.ads.banner

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import com.cyrus.hidden_differences.HiddenDifferencesApp
import com.cyrus.hidden_differences.ads.AdsManager
import com.cyrus.hidden_differences.databinding.LayoutBannerAdsBinding
import com.cyrus.hidden_differences.ext.CoroutineExt
import com.cyrus.hidden_differences.utils.LoggerUtils
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class BannerAdsManager {
    private val TAG = "BannerAdsManager"
    private var mAdView: AdView? = null
    private var parentViewAds: LayoutBannerAdsBinding? = null
    private var retry = 0
    private val totalRetry = 2
    private var activity: Activity? = null

    private val bannerAdListener: AdListener = object : AdListener() {
        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
            super.onAdFailedToLoad(loadAdError)
            LoggerUtils.d(TAG, "onAdFailedToLoad: ${loadAdError.message}")
            retry++
            if (retry < totalRetry) {
                parentViewAds?.let {
                    CoroutineExt.runOnMainAfterDelay {
                        if (activity != null)
                            doAdsBanner(it, activity = activity!!)
                    }
                }
            }
        }

        override fun onAdImpression() {
            super.onAdImpression()
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            LoggerUtils.d(TAG, "onAdLoaded: ${mAdView.toString()}")
            parentViewAds?.layoutAd?.removeAllViews()
            parentViewAds?.layoutAd?.addView(mAdView)
        }
    }

    @SuppressLint("MissingPermission", "VisibleForTests")
    fun loadAdsBanner(
        parentViewAds: LayoutBannerAdsBinding,
        activity: Activity
    ) {
        retry = 1
        this.activity = activity
        doAdsBanner(parentViewAds, activity)
    }

    /**
     * This method does not reset Retry value
     */
    private fun doAdsBanner(
        parentViewAds: LayoutBannerAdsBinding,
        activity: Activity
    ) {
        if (!HiddenDifferencesApp.getGoogleMobileAdsConsent().canRequestAds) return
        this.parentViewAds = parentViewAds
        try {
            mAdView = AdView(activity)
            mAdView!!.setAdSize(getAdSize(activity, parentViewAds.root))
            mAdView?.adUnitId = AdsManager.BANNER_ADS_ID
            mAdView?.adListener = bannerAdListener
            parentViewAds.layoutAd.addView(mAdView)
            mAdView?.loadAd(buildAdRequest())
        } catch (e: Exception) {
            LoggerUtils.d(TAG, e.message.toString())
        }
    }

    @SuppressLint("VisibleForTests")
    private fun buildAdRequest(/*isAdsCollapsible: Boolean = false*/): AdRequest {
        val extras = Bundle()
        /*if (isAdsCollapsible) {
            extras.putString("collapsible", "bottom")
            extras.putString("collapsible_request_id", UUID.randomUUID().toString())
        }*/
        return AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter::class.java, extras).build()
    }

    @SuppressLint("VisibleForTests")
    private fun getAdSize(activity: Activity, viewBannerCustom: View): AdSize {
        // Determine the screen width (less decorations) to use for the ad width.
        val display: Display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density: Float = outMetrics.density
        var adWidthPixels = viewBannerCustom.width.toFloat()

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }

    fun onDestroy() {
        mAdView?.removeAllViews()
        mAdView?.destroy()
        mAdView = null
        parentViewAds?.layoutAd?.removeAllViews()
        parentViewAds = null
    }
}
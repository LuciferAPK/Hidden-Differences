package com.cyrus.hidden_differences.ui.activity.splash

import android.annotation.SuppressLint
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.ads.AdsManager
import com.cyrus.hidden_differences.base.BaseActivity
import com.cyrus.hidden_differences.base.BaseLoadingView
import com.cyrus.hidden_differences.cmp.GoogleMobileAdsConsentManager
import com.cyrus.hidden_differences.databinding.ActivitySplashBinding
import com.cyrus.hidden_differences.ext.CoroutineExt
import com.cyrus.hidden_differences.navigation.NavigationManager
import com.cyrus.hidden_differences.ui.activity.main.MainViewModel
import com.cyrus.hidden_differences.utils.LoggerUtils
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var googleMobileAdsConsentManager: GoogleMobileAdsConsentManager

    private val splashViewModel: SplashViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    private val isMobileAdsInitializeCalled = AtomicBoolean(false)

    override fun getContentLayout(): Int = R.layout.activity_splash

    override fun initView() {
        checkConsent()
    }

    override fun initListener() {}

    override fun observerLiveData() {}

    override fun getLayoutLoading(): BaseLoadingView? = null

    private fun checkConsent() {
        if (googleMobileAdsConsentManager.canRequestAds) {
            initializeMobileAdsSdk()
            return
        } else {
            LoggerUtils.d(CONSENT_TAG,"##### CAN'T Request Ads")
        }

        googleMobileAdsConsentManager.gatherConsent(this) { consentError ->
            if (consentError != null) {
                // Consent not obtained in current session.
                LoggerUtils.d(CONSENT_TAG,"##### ${consentError.errorCode}: ${consentError.message}")
                initializeMobileAdsSdk()
            }

            LoggerUtils.d(CONSENT_TAG,"======CanRequestAds: ${googleMobileAdsConsentManager.canRequestAds}======")
            if (googleMobileAdsConsentManager.canRequestAds) {
                LoggerUtils.d(CONSENT_TAG,"##### Init MobileAds SDK after finish gatherConsent")
                initializeMobileAdsSdk()
            } else {
                handleStartActivity()
                return@gatherConsent
            }
        }

        // This sample attempts to load ads using consent obtained in the previous session.
        if (googleMobileAdsConsentManager.canRequestAds) {
            LoggerUtils.d(CONSENT_TAG,"##### Init MobileAds SDK after CALL gatherConsent")
            initializeMobileAdsSdk()
        }
    }

    private fun initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            LoggerUtils.d(CONSENT_TAG,"##### Ready Init MobileAds SDK, bails")
            return
        }
        // Initialize the Mobile Ads SDK
        setupAdsModule()
        handleStartActivity()
    }

    private fun setupAdsModule() {
        MobileAds.initialize(this) {}
        AdsManager.context = this
        MobileAds.setAppMuted(true)
        AdsManager.initialize()
    }

    private fun handleStartActivity() {
        binding.imgBg.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_zoom))
        if (splashViewModel.isFirstOpenApp()) {
            mainViewModel.saveNumberHeart(5)
            mainViewModel.saveNumberPause(3)
            mainViewModel.saveNumberFind(3)
            splashViewModel.saveFirstOpenApp()
        }
        CoroutineExt.runOnMainAfterDelay(2000) {
            NavigationManager.navigationToMainActivity(this)
        }
    }

    companion object {
        private const val CONSENT_TAG = "GoogleMobileAdsConsentManager"
    }
}
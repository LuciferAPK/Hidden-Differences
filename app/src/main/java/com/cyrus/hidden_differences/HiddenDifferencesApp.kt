package com.cyrus.hidden_differences

import android.app.Application
import android.content.Context
import com.cyrus.hidden_differences.cmp.GoogleMobileAdsConsentManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiddenDifferencesApp : Application() {

    @Inject
    lateinit var googleMobileAdsConsentManager: GoogleMobileAdsConsentManager

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: HiddenDifferencesApp
        fun getContext(): Context = instance.applicationContext
        fun getGoogleMobileAdsConsent() = instance.googleMobileAdsConsentManager
    }
}

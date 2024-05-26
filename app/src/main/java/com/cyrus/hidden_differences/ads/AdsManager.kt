package com.cyrus.hidden_differences.ads

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.blankj.utilcode.BuildConfig
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@SuppressLint("StaticFieldLeak")
object AdsManager {
    var BANNER_ADS_ID: String = ""
    var INTER_ADS_ID: String = ""
    var REWARD_ADS_ID: String = ""
    var OPEN_ADS_ID: String = ""
    var context: Context? = null

    init {
        if (BuildConfig.DEBUG) {
            BANNER_ADS_ID = "ca-app-pub-3940256099942544/2014213617"
            INTER_ADS_ID = "ca-app-pub-3940256099942544/1033173712"
            REWARD_ADS_ID = "ca-app-pub-3940256099942544/5224354917"
            OPEN_ADS_ID = "ca-app-pub-3940256099942544/9257395921"
        } else {
            BANNER_ADS_ID = "ca-app-pub-3940256099942544/2014213617"
            INTER_ADS_ID = "ca-app-pub-3940256099942544/1033173712"
            REWARD_ADS_ID = "ca-app-pub-3940256099942544/5224354917"
            OPEN_ADS_ID = "ca-app-pub-3940256099942544/9257395921"
        }
    }

    fun initialize() {
        if (BuildConfig.DEBUG) {
            val androidDeviceId: String = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
            val adMobDeviceId: String? = calculateMD5(androidDeviceId)
            if (adMobDeviceId != null) {
                val requestConfiguration = RequestConfiguration.Builder()
                    .setTestDeviceIds(listOf(adMobDeviceId))
                    .build()
                MobileAds.setRequestConfiguration(requestConfiguration)
            }
        }
    }

    private fun calculateMD5(input: String): String? = runCatching {
        val md = MessageDigest.getInstance("MD5")
        val array = md.digest(input.toByteArray(StandardCharsets.UTF_8))
        val md5Hash = array.joinToString("") { String.format("%02x", it.toInt() and 0xFF) }
        return md5Hash.uppercase()
    }.onFailure {}.getOrNull()
}
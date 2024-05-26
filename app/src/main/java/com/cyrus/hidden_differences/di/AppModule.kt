package com.cyrus.hidden_differences.di

import android.content.Context
import com.cyrus.hidden_differences.ads.banner.BannerAdsManager
import com.cyrus.hidden_differences.ads.reward.RewardedAdsManager
import com.cyrus.hidden_differences.cmp.GoogleMobileAdsConsentManager
import com.cyrus.hidden_differences.local.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerCMP(
        @ApplicationContext context: Context,
    ) = GoogleMobileAdsConsentManager(context)

    @Singleton
    @Provides
    fun providePreferencesManager(
        @ApplicationContext context: Context
    ) = PreferencesManager(context)

    /**
     * Ads
     * */
    @Singleton
    @Provides
    fun provideBannerAds() = BannerAdsManager()

    @Singleton
    @Provides
    fun provideRewardedAds() = RewardedAdsManager()
}

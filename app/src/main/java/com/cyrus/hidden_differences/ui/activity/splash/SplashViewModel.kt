package com.cyrus.hidden_differences.ui.activity.splash

import androidx.lifecycle.ViewModel
import com.cyrus.hidden_differences.repository.SystemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val systemRepository: SystemRepository
) : ViewModel() {

    fun isFirstOpenApp(): Boolean = systemRepository.isFirstOpenApp()
    fun saveFirstOpenApp() = systemRepository.saveFirstOpenApp()
}
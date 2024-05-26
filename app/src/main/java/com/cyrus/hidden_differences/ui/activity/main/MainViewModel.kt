package com.cyrus.hidden_differences.ui.activity.main

import androidx.lifecycle.ViewModel
import com.cyrus.hidden_differences.local.PreferencesKey
import com.cyrus.hidden_differences.repository.SystemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val systemRepository: SystemRepository
) : ViewModel() {

    /**HighScore Tab*/
    fun getHighScoreTab(): Int = systemRepository.getHighScoreTab()
    fun saveHighScoreTab(point: Int) = systemRepository.saveHighScoreTab(point)

    /**HighScore Tap Number*/
    fun getHighScoreTapNumber(): Int = systemRepository.getHighScoreTapNumber()
    fun saveHighScoreTapNumber(point: Int) = systemRepository.saveHighScoreTapNumber(point)

    /**HighScore Tap Text*/
    fun getHighScoreTapText(): Int = systemRepository.getHighScoreTapText()
    fun saveHighScoreTapText(point: Int) = systemRepository.saveHighScoreTapText(point)

    /**HighScore Tap Image*/
    fun getHighScoreTapImage(): Int = systemRepository.getHighScoreTapImage()
    fun saveHighScoreTapImage(point: Int) = systemRepository.saveHighScoreTapImage(point)

    /**HighScore Tap Fruit*/
    fun getHighScoreTapFruit(): Int = systemRepository.getHighScoreTapFruit()
    fun saveHighScoreTapFruit(point: Int) = systemRepository.saveHighScoreTapFruit(point)

    /**HighScore Tap Hard*/
    fun getHighScoreTapHard(): Int = systemRepository.getHighScoreTapHard()
    fun saveHighScoreTapHard(point: Int) = systemRepository.saveHighScoreTapHard(point)

    /**Item Heart*/
    fun getNumberHeart(): Int = systemRepository.getNumberHeart()
    fun saveNumberHeart(point: Int) = systemRepository.saveNumberHeart(point)

    /**Item Pause*/
    fun getNumberPause(): Int = systemRepository.getNumberPause()
    fun saveNumberPause(point: Int) = systemRepository.saveNumberPause(point)

    /**Item Find*/
    fun getNumberFind(): Int = systemRepository.getNumberFind()
    fun saveNumberFind(point: Int) = systemRepository.saveNumberFind(point)
}
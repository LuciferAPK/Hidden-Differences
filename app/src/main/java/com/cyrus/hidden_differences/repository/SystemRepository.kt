package com.cyrus.hidden_differences.repository

import com.cyrus.hidden_differences.local.PreferencesKey.FIRST_OPEN_APP
import com.cyrus.hidden_differences.local.PreferencesKey.HIGH_SCORE_TAB
import com.cyrus.hidden_differences.local.PreferencesKey.HIGH_SCORE_TAP_FRUIT
import com.cyrus.hidden_differences.local.PreferencesKey.HIGH_SCORE_TAP_HARD
import com.cyrus.hidden_differences.local.PreferencesKey.HIGH_SCORE_TAP_IMAGE
import com.cyrus.hidden_differences.local.PreferencesKey.HIGH_SCORE_TAP_NUMBER
import com.cyrus.hidden_differences.local.PreferencesKey.HIGH_SCORE_TAP_TEXT
import com.cyrus.hidden_differences.local.PreferencesKey.NUMBER_FIND
import com.cyrus.hidden_differences.local.PreferencesKey.NUMBER_HEART
import com.cyrus.hidden_differences.local.PreferencesKey.NUMBER_PAUSE
import com.cyrus.hidden_differences.local.PreferencesManager
import javax.inject.Inject

class SystemRepository @Inject constructor(
    private val preferencesManager: PreferencesManager
) {

    fun isFirstOpenApp(): Boolean = preferencesManager.getBoolean(FIRST_OPEN_APP, true)
    fun saveFirstOpenApp() = preferencesManager.save(FIRST_OPEN_APP, false)

    /**HighScore Tab*/
    fun getHighScoreTab(): Int = preferencesManager.getInt(HIGH_SCORE_TAB, 0)
    fun saveHighScoreTab(point: Int) = preferencesManager.save(HIGH_SCORE_TAB, point)

    /**HighScore Tap Number*/
    fun getHighScoreTapNumber(): Int = preferencesManager.getInt(HIGH_SCORE_TAP_NUMBER, 0)
    fun saveHighScoreTapNumber(point: Int) = preferencesManager.save(HIGH_SCORE_TAP_NUMBER, point)

    /**HighScore Tap Text*/
    fun getHighScoreTapText(): Int = preferencesManager.getInt(HIGH_SCORE_TAP_TEXT, 0)
    fun saveHighScoreTapText(point: Int) = preferencesManager.save(HIGH_SCORE_TAP_TEXT, point)

    /**HighScore Tap Image*/
    fun getHighScoreTapImage(): Int = preferencesManager.getInt(HIGH_SCORE_TAP_IMAGE, 0)
    fun saveHighScoreTapImage(point: Int) = preferencesManager.save(HIGH_SCORE_TAP_IMAGE, point)

    /**HighScore Tap Fruit*/
    fun getHighScoreTapFruit(): Int = preferencesManager.getInt(HIGH_SCORE_TAP_FRUIT, 0)
    fun saveHighScoreTapFruit(point: Int) = preferencesManager.save(HIGH_SCORE_TAP_FRUIT, point)

    /**HighScore Tap Hard*/
    fun getHighScoreTapHard(): Int = preferencesManager.getInt(HIGH_SCORE_TAP_HARD, 0)
    fun saveHighScoreTapHard(point: Int) = preferencesManager.save(HIGH_SCORE_TAP_HARD, point)

    /**Item Heart*/
    fun getNumberHeart(): Int = preferencesManager.getInt(NUMBER_HEART, 0)
    fun saveNumberHeart(point: Int) = preferencesManager.save(NUMBER_HEART, point)

    /**Item Pause*/
    fun getNumberPause(): Int = preferencesManager.getInt(NUMBER_PAUSE, 0)
    fun saveNumberPause(point: Int) = preferencesManager.save(NUMBER_PAUSE, point)

    /**Item Find*/
    fun getNumberFind(): Int = preferencesManager.getInt(NUMBER_FIND, 0)
    fun saveNumberFind(point: Int) = preferencesManager.save(NUMBER_FIND, point)
}
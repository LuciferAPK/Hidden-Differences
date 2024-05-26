package com.cyrus.hidden_differences.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.ui.activity.main.MainActivity
import com.cyrus.hidden_differences.ui.fragment.highscore.HighScoreFragment
import com.cyrus.hidden_differences.utils.LoggerUtils

object NavigationManager {
    private const val TAG = "NavigationManager"

    fun navigationToMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun navigationToHighScoreFragment(parentFragmentManager: FragmentManager) {
        try {
            val highScoreFragment = HighScoreFragment.newInstance()
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            fragmentTransaction.add(R.id.constraint_main_activity, highScoreFragment)
            fragmentTransaction.addToBackStack(highScoreFragment.tag)
            fragmentTransaction.commit()
        } catch (e: Exception) {
            LoggerUtils.d(TAG, e.message)
        }
    }
}
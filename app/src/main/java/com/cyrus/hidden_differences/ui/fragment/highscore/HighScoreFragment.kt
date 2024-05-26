package com.cyrus.hidden_differences.ui.fragment.highscore

import androidx.fragment.app.activityViewModels
import com.cyrus.hidden_differences.R
import com.cyrus.hidden_differences.ads.banner.BannerAdsManager
import com.cyrus.hidden_differences.base.BaseFragment
import com.cyrus.hidden_differences.base.BaseLoadingView
import com.cyrus.hidden_differences.databinding.FragmentHighScoreBinding
import com.cyrus.hidden_differences.ui.activity.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HighScoreFragment : BaseFragment<FragmentHighScoreBinding>() {

    @Inject
    lateinit var bannerAdsManager: BannerAdsManager

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getContentLayout(): Int = R.layout.fragment_high_score

    override fun initView() {
        getPointScore()
        bannerAdsManager.loadAdsBanner(
            parentViewAds = binding.layoutBannerAds,
            activity = requireActivity()
        )
    }

    override fun initListener() {
        binding.btnBack.setOnClickListener { onBackPress() }
    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): BaseLoadingView? = null

    private fun onBackPress() {
        parentFragmentManager.popBackStack()
    }

    private fun getPointScore() {
        binding.txtScoreTab.text = mainViewModel.getHighScoreTab().toString()
        binding.txtScoreTapNumber.text = mainViewModel.getHighScoreTapNumber().toString()
        binding.txtScoreTapText.text = mainViewModel.getHighScoreTapText().toString()
        binding.txtScoreTapImage.text = mainViewModel.getHighScoreTapImage().toString()
        binding.txtScoreTapFruit.text = mainViewModel.getHighScoreTapFruit().toString()
        binding.txtScoreTapHard.text = mainViewModel.getHighScoreTapHard().toString()
    }

    companion object {
        const val TAG = "HighScoreFragment"
        fun newInstance() = HighScoreFragment()
    }
}
package com.nitok_ict.socialgen.socialgen_app.ui.reinforcement

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nitok_ict.socialgen.socialgen_app.R
import com.nitok_ict.socialgen.socialgen_app.databinding.ReinforcementFragmentBinding

class ReinforcementFragment : Fragment() {
    private val viewModel: ReinforcementViewModel by viewModels()

    private lateinit var dashboardViewModel: ReinforcementViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return ReinforcementFragmentBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@ReinforcementFragment.viewModel

            }.run {
                root
            }
    }
}
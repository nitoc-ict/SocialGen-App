package com.nitok_ict.socialgen.socialgen_app.ui.reinforcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.run{
            saveData.observe(viewLifecycleOwner, {})
            statusData.observe(viewLifecycleOwner, {})
        }
    }
}
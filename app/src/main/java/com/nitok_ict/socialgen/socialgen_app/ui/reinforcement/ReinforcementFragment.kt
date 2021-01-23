package com.nitok_ict.socialgen.socialgen_app.ui.reinforcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nitok_ict.socialgen.socialgen_app.R

class ReinforcementFragment : Fragment() {

    private lateinit var dashboardViewModel: ReinforcementViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(ReinforcementViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reinforcement, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
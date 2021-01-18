package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nitok_ict.socialgen.socialgen_app.R

class RankingFragment : Fragment() {

    private lateinit var rankingViewModel: RankingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rankingViewModel =
                ViewModelProvider(this).get(RankingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ranking, container, false)
        val textView: TextView = root.findViewById(R.id.text_ranking)
        rankingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root

    }
}
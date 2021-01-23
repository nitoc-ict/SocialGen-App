package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.nitok_ict.socialgen.socialgen_app.databinding.RankingFragmentBinding

class RankingFragment : Fragment() {
    companion object{
        fun newInstance() = RankingFragment()
    }

    private val viewModel: RankingViewModel by viewModels()
    private lateinit var userRankListAdapter: UserRankListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return RankingFragmentBinding.inflate(inflater, container, false)
            .apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@RankingFragment.viewModel

            rankinRecyclerView.run {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter =
                    UserRankListAdapter(viewLifecycleOwner, this@RankingFragment.viewModel).also {
                        userRankListAdapter = it
                    }
            }
        }.run {
            root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getData()

        viewModel.run {
            rankingLiveData.observe(viewLifecycleOwner, {
                userRankListAdapter.submitList(it)
            })
        }
    }
}
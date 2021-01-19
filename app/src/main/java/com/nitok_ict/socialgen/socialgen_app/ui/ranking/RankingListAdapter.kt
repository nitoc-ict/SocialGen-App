package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nitok_ict.socialgen.socialgen_app.databinding.ItemRankingBinding
import com.nitok_ict.socialgen.socialgen_app.model.UserRank

private object DiffCallback : DiffUtil.ItemCallback<UserRank>() {
    override fun areItemsTheSame(oldItem: UserRank, newItem: UserRank): Boolean {
        return oldItem.userName == newItem.userName
    }

    override fun areContentsTheSame(oldItem: UserRank, newItem: UserRank): Boolean {
        return oldItem == newItem
    }
}

class RankingListAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: RankingViewModel
) : ListAdapter<UserRank, RankingListAdapter.UserViewHolder>(DiffCallback) {

    class UserViewHolder(private val binding: ItemRankingBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserRank, viewLifecycleOwner: LifecycleOwner, viewModel: RankingViewModel){
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                userRank = item
                this.viewModel = viewModel

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(ItemRankingBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
    }
}
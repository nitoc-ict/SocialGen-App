package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nitok_ict.socialgen.socialgen_app.databinding.UserRankViewBinding
import com.nitok_ict.socialgen.socialgen_app.model.UserRank
private object DiffCallback : DiffUtil.ItemCallback<UserRank>() {
    override fun areItemsTheSame(oldItem: UserRank, newItem: UserRank): Boolean {
        return oldItem.userName == newItem.userName
    }

    override fun areContentsTheSame(oldItem: UserRank, newItem: UserRank): Boolean {
        return oldItem == newItem
    }
}

class UserRankListAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: RankingViewModel
) : ListAdapter<UserRank, UserRankListAdapter.UserRankViewHolder>(DiffCallback) {

    class UserRankViewHolder(private val binding: UserRankViewBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserRank){
            binding.run {
                userRank = item

                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRankViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserRankViewHolder(UserRankViewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserRankViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package com.thescreamer74.mycvandroid.presentation.ui.adapter

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.GraduationItemViewHolderBinding
import com.thescreamer74.mycvandroid.model.GraduationUIModel

class GraduationItemAdapter(private val onClickListener: OnClickListener) : ListAdapter<GraduationUIModel.Graduation, GraduationItemAdapter.GraduationItemViewHolder>(GraduationItemViewHolder.DiffCallback) {

    class GraduationItemViewHolder(private var binding: GraduationItemViewHolderBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(graduation: GraduationUIModel) {
            when (graduation) {
                is GraduationUIModel.Graduation -> {
                    binding.graduation?.officeIcon
                    binding.graduation = graduation
                    binding.executePendingBindings()
                }
                GraduationUIModel.Error -> {
                    binding.graduation?.name = "Error"
                    binding.executePendingBindings()
                }
            }
        }

        fun showOrHideDesc() {
            if(binding.descGraduation.isVisible) {
                binding.imageView.setImageResource(R.drawable.ic_arrow_right)
                TransitionManager.beginDelayedTransition(binding.containerDesc)
                binding.descGraduation.visibility = View.GONE
            } else {
                binding.imageView.setImageResource(R.drawable.ic_arrow_down)
                TransitionManager.beginDelayedTransition(binding.containerDesc)
                binding.descGraduation.visibility = View.VISIBLE
            }
        }

        companion object DiffCallback : DiffUtil.ItemCallback<GraduationUIModel.Graduation>() {
            override fun areItemsTheSame(
                oldItem: GraduationUIModel.Graduation,
                newItem: GraduationUIModel.Graduation
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: GraduationUIModel.Graduation,
                newItem: GraduationUIModel.Graduation
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.town == newItem.town
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : GraduationItemViewHolder {
        return GraduationItemViewHolder(GraduationItemViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: GraduationItemViewHolder, position: Int) {
        val graduation = getItem(position)
        holder.itemView.setOnClickListener {
            //onClickListener.onClick(position)
            holder.showOrHideDesc()
        }
        holder.bind(graduation)
    }
    class OnClickListener(val clickListener: (graduationId: Int) -> Unit) {
        fun onClick(graduationId: Int) = clickListener(graduationId)
    }
}
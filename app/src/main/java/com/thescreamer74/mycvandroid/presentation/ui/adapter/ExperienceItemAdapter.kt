package com.thescreamer74.mycvandroid.presentation.ui.adapter

import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.transition.Slide
import android.transition.Transition
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.ProExpItemViewHolderBinding
import com.thescreamer74.mycvandroid.model.ExperienceUIModel

class ExperienceItemAdapter(private val onClickListener: OnClickListener) : ListAdapter<ExperienceUIModel.Experience, ExperienceItemAdapter.ExperienceItemViewHolder>(ExperienceItemViewHolder.DiffCallback) {

    class ExperienceItemViewHolder(private var binding: ProExpItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(experience : ExperienceUIModel) {
            when (experience) {
                is ExperienceUIModel.Experience -> {
                    binding.experience = experience
                    binding.executePendingBindings()
                }
                ExperienceUIModel.Error -> {
                    binding.experience?.expName = "Error"
                    binding.executePendingBindings()
                }
            }
        }

        fun showOrHideDesc() {
            if(binding.descExp.isVisible) {
                binding.imageView.setImageResource(R.drawable.ic_arrow_right)
                binding.descExp.visibility = View.GONE

            } else {
                binding.imageView.setImageResource(R.drawable.ic_arrow_down)
                TransitionManager.beginDelayedTransition(binding.containerDesc, Slide(Gravity.TOP) as Transition)
                binding.descExp.visibility = View.VISIBLE
            }
        }

        companion object DiffCallback : DiffUtil.ItemCallback<ExperienceUIModel.Experience>() {
            override fun areItemsTheSame(
                oldItem: ExperienceUIModel.Experience,
                newItem: ExperienceUIModel.Experience
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: ExperienceUIModel.Experience,
                newItem: ExperienceUIModel.Experience
            ): Boolean {
                return oldItem.expName == newItem.expName && oldItem.officeName == newItem.officeName
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : ExperienceItemViewHolder {
        return ExperienceItemViewHolder(ProExpItemViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: ExperienceItemViewHolder, position: Int) {
        val experience = getItem(position)
        holder.itemView.setOnClickListener{
            holder.showOrHideDesc()
            //onClickListener.onClick(position)
        }
        holder.bind(experience)
    }
    class OnClickListener(val clickListener: (experienceId: Int) -> Unit) {
        fun onClick(experienceId: Int) = clickListener(experienceId)
    }
}
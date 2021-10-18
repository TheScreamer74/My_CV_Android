package com.thescreamer74.mycvandroid.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thescreamer74.mycvandroid.databinding.SkillsItemViewHolderBinding
import com.thescreamer74.mycvandroid.model.GraduationUIModel

class SkillItemAdapter(private val onClickListener: OnClickListener) : ListAdapter<GraduationUIModel.Skill, SkillItemAdapter.SkillItemViewHolder>(SkillItemViewHolder.DiffCallback) {

    class SkillItemViewHolder(private var binding: SkillsItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(skill: GraduationUIModel.Skill) {
            binding.skill = skill
            binding.executePendingBindings()
        }

        companion object DiffCallback : DiffUtil.ItemCallback<GraduationUIModel.Skill>() {
            override fun areItemsTheSame(
                oldItem: GraduationUIModel.Skill,
                newItem: GraduationUIModel.Skill
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: GraduationUIModel.Skill,
                newItem: GraduationUIModel.Skill
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.level == newItem.level
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : SkillItemViewHolder {
        return SkillItemViewHolder(SkillsItemViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: SkillItemViewHolder, position: Int) {
        val experience = getItem(position)
        holder.itemView.setOnClickListener{
            //onClickListener.onClick(position)
        }
        holder.bind(experience)
    }
    class OnClickListener(val clickListener: (experienceId: Int) -> Unit) {
        fun onClick(experienceId: Int) = clickListener(experienceId)
    }
}
package com.thescreamer74.mycvandroid.presentation.ui.adapter

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thescreamer74.mycvandroid.databinding.LanguageItemViewHolderBinding
import com.thescreamer74.mycvandroid.model.PersonalUIModel

class LanguageItemAdapter (private val onClickListener: OnClickListener) : ListAdapter<PersonalUIModel.Language, LanguageItemAdapter.LanguageItemViewHolder>(LanguageItemViewHolder.DiffCallback) {

    class LanguageItemViewHolder(private var binding: LanguageItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(language: PersonalUIModel.Language) {
            binding.language = language
            binding.executePendingBindings()
        }

        fun showOrHideDesc() {
            if(binding.containerLangInfo.isVisible) {
                TransitionManager.beginDelayedTransition(binding.containerLangInfo)
                binding.containerLangInfo.visibility = View.GONE
            } else {
                TransitionManager.beginDelayedTransition(binding.containerLangInfo)
                binding.containerLangInfo.visibility = View.VISIBLE
            }
        }

        companion object DiffCallback : DiffUtil.ItemCallback<PersonalUIModel.Language>() {
            override fun areItemsTheSame(
                oldItem: PersonalUIModel.Language,
                newItem: PersonalUIModel.Language
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: PersonalUIModel.Language,
                newItem: PersonalUIModel.Language
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.level == newItem.level
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : LanguageItemViewHolder {
        return LanguageItemViewHolder(LanguageItemViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: LanguageItemViewHolder, position: Int) {
        val language = getItem(position)
        holder.itemView.setOnClickListener {
            //onClickListener.onClick(position)
            holder.showOrHideDesc()
        }
        holder.bind(language)
    }
    class OnClickListener(val clickListener: (langId: Int) -> Unit) {
        fun onClick(langId: Int) = clickListener(langId)
    }
}
package com.thescreamer74.mycvandroid.presentation.ui.adapter

import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.HobbiesItemViewHolderBinding
import com.thescreamer74.mycvandroid.databinding.LanguageItemViewHolderBinding
import com.thescreamer74.mycvandroid.model.PersonalUIModel

class HobbyItemAdapter (private val onClickListener: OnClickListener) : ListAdapter<PersonalUIModel.Hobby, HobbyItemAdapter.HobbyItemViewHolder>(HobbyItemViewHolder.DiffCallback) {

    class HobbyItemViewHolder(private var binding: HobbiesItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hobby: PersonalUIModel.Hobby) {
            binding.hobby = hobby
            binding.executePendingBindings()
        }

        fun showOrHideDesc() {
            if(binding.hobbyLevel.isVisible) {
                binding.imgArrowDevelop.setImageResource(R.drawable.ic_arrow_right)
                binding.hobbyLevel.visibility = View.GONE
            } else {
                binding.imgArrowDevelop.setImageResource(R.drawable.ic_arrow_down)
                TransitionManager.beginDelayedTransition(binding.containerHobbyInfo, Slide(Gravity.TOP) as Transition)
                binding.hobbyLevel.visibility = View.VISIBLE
            }
        }

        companion object DiffCallback : DiffUtil.ItemCallback<PersonalUIModel.Hobby>() {
            override fun areItemsTheSame(
                oldItem: PersonalUIModel.Hobby,
                newItem: PersonalUIModel.Hobby
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: PersonalUIModel.Hobby,
                newItem: PersonalUIModel.Hobby
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.level == newItem.level
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : HobbyItemViewHolder {
        return HobbyItemViewHolder(HobbiesItemViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: HobbyItemViewHolder, position: Int) {
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
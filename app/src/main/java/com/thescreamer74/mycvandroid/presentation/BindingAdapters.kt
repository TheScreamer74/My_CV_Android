package com.thescreamer74.mycvandroid.presentation

import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.model.GraduationUIModel
import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.thescreamer74.mycvandroid.model.ExperienceUIModel
import com.thescreamer74.mycvandroid.model.PersonalUIModel
import com.thescreamer74.mycvandroid.presentation.ui.adapter.*

@BindingAdapter("image")
fun ImageView.bindImage(image: String?) {
    image?.let { url ->
        val parsedImageUrl = url.toUri().buildUpon().scheme("https").build()
        this.load("$parsedImageUrl") {
            placeholder(R.drawable.ic_image_placeholder)
            scale(Scale.FILL)
            Log.d("API IMAGE", parsedImageUrl.toString())
        }
    }
}

@BindingAdapter("imageProfile")
fun ImageView.bindImageProfile(image: String?) {
    image?.let { url ->
        val parsedImageUrl = url.toUri().buildUpon().scheme("https").build()
        this.load("$parsedImageUrl") {
            placeholder(R.drawable.ic_image_placeholder)
            scale(Scale.FILL)
            transformations(CircleCropTransformation())
            Log.d("API IMAGE", parsedImageUrl.toString())
        }
    }
}

@BindingAdapter("listGraduationData")
fun bindRecyclerViewGraduation(recyclerView: RecyclerView, data: Array<GraduationUIModel.Graduation>?){
    val adapter = recyclerView.adapter as GraduationItemAdapter
    adapter.submitList(data?.toList())
}
@BindingAdapter("listExperienceData")
fun bindRecyclerViewExperience(recyclerView: RecyclerView, data: Array<ExperienceUIModel.Experience>?){
    val adapter = recyclerView.adapter as ExperienceItemAdapter
    adapter.submitList(data?.toList())
}
@BindingAdapter("listLanguageData")
fun bindRecyclerViewLanguage(recyclerView: RecyclerView, data: Array<PersonalUIModel.Language>?){
    val adapter = recyclerView.adapter as LanguageItemAdapter
    adapter.submitList(data?.toList())
}
@BindingAdapter("listHobbyData")
fun bindRecyclerViewHobby(recyclerView: RecyclerView, data: Array<PersonalUIModel.Hobby>?){
    val adapter = recyclerView.adapter as HobbyItemAdapter
    adapter.submitList(data?.toList())
}

@BindingAdapter("listSkillData")
fun bindRecyclerViewSkill(recyclerView: RecyclerView, data: Array<GraduationUIModel.Skill>?){
    val adapter = recyclerView.adapter as SkillItemAdapter
    adapter.submitList(data?.toList())
}




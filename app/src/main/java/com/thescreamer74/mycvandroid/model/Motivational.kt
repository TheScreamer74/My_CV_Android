package com.thescreamer74.mycvandroid.model

import com.google.gson.annotations.SerializedName

sealed class MotivationalUIModel {
    data class Motivational (
        @SerializedName("motivational")
        val motivational: String?
    )
}
package com.thescreamer74.mycvandroid.model

import com.google.gson.annotations.SerializedName

sealed class ExperienceUIModel {

    data class Result (
        @SerializedName("results")
        val data: Data
    )

    data class Data (
        @SerializedName("experiences")
        val results: Array<Experience>
    )

    data class Experience(
        @SerializedName("office_name")
        var officeName: String?,
        @SerializedName("exp_name")
        var expName: String?,
        @SerializedName("office_icon")
        val officeIcon: String?,
        @SerializedName("contract")
        val contract: String?,
        @SerializedName("town")
        val town: String?,
        @SerializedName("year_start")
        val yearStart: String?,
        @SerializedName("year_end")
        val yearEnd: String?,
        @SerializedName("desc")
        val desc: String?
    ): ExperienceUIModel()

    object Error: ExperienceUIModel()

}



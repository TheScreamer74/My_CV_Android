package com.thescreamer74.mycvandroid.model

import com.google.gson.annotations.SerializedName

sealed class GraduationUIModel {

    data class Result (
            @SerializedName("results")
            val data: Data
            )

    data class Data (
        @SerializedName("graduations")
        val graduations: Array<Graduation>,

        @SerializedName("skills")
        val skills: Array<Skill>
    )

    data class Skill(
        @SerializedName("name")
        var name: String?,
        @SerializedName("level")
        val level: Int?,
        @SerializedName("icon")
        val icon: String
    )

    data class Graduation(
        @SerializedName("name")
        var name: String?,
        @SerializedName("office_name")
        val officeName: String?,
        @SerializedName("office_icon")
        val officeIcon: String?,
        @SerializedName("town")
        val town: String?,
        @SerializedName("year_start")
        val yearStart: String?,
        @SerializedName("year_end")
        val yearEnd: String?,
        @SerializedName("desc")
        val desc: String?
    ): GraduationUIModel()

object Error: GraduationUIModel()

}



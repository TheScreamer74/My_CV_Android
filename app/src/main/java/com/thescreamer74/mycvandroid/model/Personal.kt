package com.thescreamer74.mycvandroid.model

import com.google.gson.annotations.SerializedName

sealed class PersonalUIModel {
    data class Result (
        @SerializedName("results")
        val data: Data
            )

    data class Data (
        @SerializedName("personal")
        val results: Personal
            )

    data class WebProfiles (
        @SerializedName("linkedin")
        val linkedin: String?,
        @SerializedName("github")
        val github: String?,
            )

    data class Language (
        @SerializedName("name")
        val name: String?,
        @SerializedName("level")
        val level: String?,
        @SerializedName("icon")
        val icon: String?,
            )

    data class Hobby (
        @SerializedName("name")
        val name: String?,
        @SerializedName("level")
        val level: String?,
        @SerializedName("icon")
        val icon: String?,
            )

    data class Personal (
        @SerializedName("name")
        val name: String?,
        @SerializedName("surname")
        val surname: String?,
        @SerializedName("photo")
        val photo: String?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("nationality")
        val nationality: String?,
        @SerializedName("driving_licence")
        val drivingLicence: String?,
        @SerializedName("address")
        val address: String?,
        @SerializedName("town")
        val town: String?,
        @SerializedName("zip_code")
        val zipCode: String?,
        @SerializedName("age")
        val age: String?,
        @SerializedName("web_profiles")
        val webProfiles: WebProfiles,
        @SerializedName("languages")
        val languages: Array<Language>?,
        @SerializedName("hobbies")
        val hobbies: Array<Hobby>?,
            )
}
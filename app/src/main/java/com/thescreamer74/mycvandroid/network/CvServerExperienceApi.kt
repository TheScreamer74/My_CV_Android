package com.thescreamer74.mycvandroid.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CvServerExperienceApi {
    companion object {
        const val GET_EXPERIENCES_URL = "/experiences"
    }

    @GET(GET_EXPERIENCES_URL)
    suspend fun getExperience(): Response<ResponseBody>
}
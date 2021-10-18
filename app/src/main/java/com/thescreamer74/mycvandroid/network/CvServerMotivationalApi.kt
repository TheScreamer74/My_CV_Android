package com.thescreamer74.mycvandroid.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CvServerMotivationalApi {
    companion object {
        const val GET_MOTIVATIONAL_URL = "/motivational"
    }

    @GET(GET_MOTIVATIONAL_URL)
    suspend fun getMotivational(): Response<ResponseBody>
}
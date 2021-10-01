package com.thescreamer74.mycvandroid.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CvServerPersonalApi {
    companion object {
        const val GET_GRADUATIONS_URL = "/personal"
    }

    @GET(GET_GRADUATIONS_URL)
    suspend fun getPersonal(): Response<ResponseBody>
}
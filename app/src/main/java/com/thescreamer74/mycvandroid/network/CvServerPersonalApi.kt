package com.thescreamer74.mycvandroid.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CvServerPersonalApi {
    companion object {
        const val GET_PERSONAL_URL = "/personal"
    }

    @GET(GET_PERSONAL_URL)
    suspend fun getPersonal(): Response<ResponseBody>
}
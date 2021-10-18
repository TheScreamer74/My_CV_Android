package com.thescreamer74.mycvandroid.network

import org.koin.core.component.KoinApiExtension


interface CvServerRetrofitInterface {
    @KoinApiExtension
    fun onResult()
}
package com.thescreamer74.mycvandroid.presentation.ui.graduation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thescreamer74.mycvandroid.model.GraduationUIModel
import com.thescreamer74.mycvandroid.network.CvServerGraduationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class GraduationViewModel: ViewModel(), KoinComponent {

    private val _api : CvServerGraduationApi by inject()

    private val _graduations = MutableLiveData<GraduationUIModel.Result>()
    val graduations: LiveData<GraduationUIModel.Result>
        get() =_graduations

    init {
        getGraduationsList()
    }

    private fun getGraduationsList() {
        viewModelScope.launch {
            val getGraduationsListDeferred = _api.getGraduations()
            try {
                val result = withContext(Dispatchers.IO) { getGraduationsListDeferred }
                Log.d("API CALL", result.toString())
                result.body()?.let {
                    Log.d("API CALL", it.toString())
                    _graduations.value = withContext(Dispatchers.Default) {
                        Gson().fromJson(it.string(), GraduationUIModel.Result::class.java)
                    }!!
                    Log.d("API RESULT", _graduations.value?.data?.results?.size.toString())

                }

            } catch (t: Throwable) {
                Log.d("API CALL", "Error : $t")
            }
        }
    }

}
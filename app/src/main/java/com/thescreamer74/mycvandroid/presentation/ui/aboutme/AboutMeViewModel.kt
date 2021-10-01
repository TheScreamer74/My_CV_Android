package com.thescreamer74.mycvandroid.presentation.ui.aboutme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thescreamer74.mycvandroid.model.GraduationUIModel
import com.thescreamer74.mycvandroid.model.PersonalUIModel
import com.thescreamer74.mycvandroid.network.CvServerPersonalApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class AboutMeViewModel: ViewModel(), KoinComponent {

    private val _api : CvServerPersonalApi by inject()

    private val _personal = MutableLiveData<PersonalUIModel.Result>()
    val personal: LiveData<PersonalUIModel.Result>
        get() = _personal

    init {
        getPersonal()
    }

    private fun getPersonal() {
        viewModelScope.launch {
            val getPersonalDeferred = _api.getPersonal()
            try {
                val result = withContext(Dispatchers.IO) { getPersonalDeferred }
                Log.d("API CALL", result.toString())
                result.body()?.let {
                    Log.d("API CALL", it.toString())
                    _personal.value = withContext(Dispatchers.Default) {
                        Gson().fromJson(it.string(), PersonalUIModel.Result::class.java)
                    }!!
                    Log.d("API RESULT", _personal.value?.data?.results?.languages?.get(0)?.name + " " + _personal.value?.data?.results?.languages?.get(0)?.level )

                }

            } catch (t: Throwable) {
                Log.d("API CALL", "Error : $t")
            }
        }
    }
}
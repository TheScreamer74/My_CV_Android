package com.thescreamer74.mycvandroid.presentation.ui.proexp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thescreamer74.mycvandroid.model.ExperienceUIModel
import com.thescreamer74.mycvandroid.model.GraduationUIModel
import com.thescreamer74.mycvandroid.network.CvServerExperienceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ProExpViewModel : ViewModel(), KoinComponent {

    private val _api : CvServerExperienceApi by inject()

    private val _experiences = MutableLiveData<ExperienceUIModel.Result>()
    val experiences: LiveData<ExperienceUIModel.Result>
        get() =_experiences

    init {
        getExperiencesList()
    }

    private fun getExperiencesList() {
        viewModelScope.launch {
            val getExperiencesDeferred = _api.getExperience()
            try {
                val result = withContext(Dispatchers.IO) { getExperiencesDeferred }
                Log.d("API CALL", result.toString())
                result.body()?.let {
                    Log.d("API CALL", it.toString())
                    _experiences.value = withContext(Dispatchers.Default) {
                        Gson().fromJson(it.string(), ExperienceUIModel.Result::class.java)
                    }!!
                    Log.d("API RESULT", _experiences.value?.data?.results?.size.toString())
                }
            } catch (t: Throwable) {
                Log.d("API CALL", "Error : $t")
            }
        }
    }


}
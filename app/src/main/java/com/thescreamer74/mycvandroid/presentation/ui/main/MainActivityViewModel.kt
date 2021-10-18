package com.thescreamer74.mycvandroid.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.thescreamer74.mycvandroid.model.MotivationalUIModel
import com.thescreamer74.mycvandroid.network.CvServerMotivationalApi
import com.thescreamer74.mycvandroid.network.CvServerRetrofitInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
class MainActivityViewModel : ViewModel(), KoinComponent{

    var isDialogComplete: Boolean = false

    private val _api : CvServerMotivationalApi by inject()

    var delegate: CvServerRetrofitInterface? = null

    private val _motivational = MutableLiveData<MotivationalUIModel.Motivational>()
    val motivational: LiveData<MotivationalUIModel.Motivational>
        get() = _motivational

    init {
        getGraduationsList()
    }

    private fun getGraduationsList() {
        viewModelScope.launch {
            val getMotivationalDeferred = _api.getMotivational()
            try {
                val result = withContext(Dispatchers.IO) { getMotivationalDeferred }
                Log.d("API CALL", result.toString())
                result.body()?.let {
                    Log.d("API CALL", it.toString())
                    _motivational.value = withContext(Dispatchers.Default) {
                        Gson().fromJson(it.string(), MotivationalUIModel.Motivational::class.java)
                    }!!
                }
                delegate?.onResult()
            } catch (t: Throwable) {
                Log.d("API CALL", "Error : $t")
            }
        }
    }


}
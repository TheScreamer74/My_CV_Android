package com.thescreamer74.mycvandroid.di

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.thescreamer74.mycvandroid.presentation.ui.aboutme.AboutMeViewModel
import com.thescreamer74.mycvandroid.presentation.ui.graduation.GraduationViewModel
import com.thescreamer74.mycvandroid.presentation.ui.main.MainActivityViewModel
import com.thescreamer74.mycvandroid.presentation.ui.proexp.ProExpViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val viewModelModule = module {
    factory { (activity : androidx.fragment.app.Fragment) ->
        ViewModelProvider(activity).get(AboutMeViewModel::class.java)
    }
    factory { (activity : androidx.fragment.app.Fragment) ->
        ViewModelProvider(activity).get(ProExpViewModel::class.java)
    }
    factory { (activity : androidx.fragment.app.Fragment) ->
        ViewModelProvider(activity).get(GraduationViewModel::class.java)
    }

    factory { (activity : androidx.appcompat.app.AppCompatActivity) ->
        ViewModelProvider(activity).get(MainActivityViewModel::class.java)
    }

}
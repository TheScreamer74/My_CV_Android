package com.thescreamer74.mycvandroid.di

import androidx.lifecycle.ViewModelProvider
import com.thescreamer74.mycvandroid.presentation.ui.aboutme.AboutMeViewModel
import com.thescreamer74.mycvandroid.presentation.ui.graduation.GraduationViewModel
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

}
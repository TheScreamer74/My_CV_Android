package com.thescreamer74.mycvandroid.di

import com.thescreamer74.mycvandroid.model.GraduationUIModel
import org.koin.dsl.module

val modelModule = module {
    //TODO Faire l'injection de dépendence pour les models
    single { GraduationUIModel.Graduation(get(), get(), get(), get(), get(), get(), get()) }
}
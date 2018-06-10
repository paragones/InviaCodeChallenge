package com.inviacodechallenge.parag.component

import com.inviacodechallenge.parag.ui.main.MainActivity
import dagger.Component
import modules.ApplicationModule
import modules.ImageModule
import modules.NetworkModule
import modules.ThreadModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        ThreadModule::class,
//        InteractorModule::class,
        NetworkModule::class,
//        RepositoryModule::class,
        ImageModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
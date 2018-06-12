package com.inviacodechallenge.parag.component

import com.inviacodechallenge.parag.SubscriberActivity
import com.inviacodechallenge.parag.modules.*
import com.inviacodechallenge.parag.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        ThreadModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DataMapperModule::class,
        ImageModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SubscriberActivity)
}
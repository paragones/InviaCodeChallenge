package com.inviacodechallenge.parag.ui.base

import android.support.v7.app.AppCompatActivity
import com.inviacodechallenge.parag.component.ActivityComponent
import com.inviacodechallenge.parag.component.DaggerActivityComponent
import modules.ApplicationModule
import modules.ImageModule
import modules.NetworkModule
import modules.ThreadModule

abstract class BaseActivity: AppCompatActivity() {

    protected fun component(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationModule(ApplicationModule(application))
                .threadModule(ThreadModule())
//                .interactorModule(InteractorModule())
                .networkModule(NetworkModule())
//                .repositoryModule(RepositoryModule())
                .imageModule(ImageModule())
                .build()
    }
}
package com.inviacodechallenge.parag.ui.base

import android.support.v7.app.AppCompatActivity
import com.inviacodechallenge.parag.component.ActivityComponent
import com.inviacodechallenge.parag.component.DaggerActivityComponent
import com.inviacodechallenge.parag.modules.*

abstract class BaseActivity: AppCompatActivity() {

    protected fun component(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationModule(ApplicationModule(application))
                .repositoryModule(RepositoryModule())
                .dataMapperModule(DataMapperModule())
                .networkModule(NetworkModule())
                .threadModule(ThreadModule())
                .imageModule(ImageModule())
                .build()
    }
}
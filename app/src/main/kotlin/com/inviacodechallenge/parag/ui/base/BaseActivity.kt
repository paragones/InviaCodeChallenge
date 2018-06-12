package com.inviacodechallenge.parag.ui.base

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
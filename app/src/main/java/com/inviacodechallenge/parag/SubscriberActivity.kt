package com.inviacodechallenge.parag

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SubscriberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscriber)
    }

    companion object {
        val KEY_REPOSITORYNAME = "KEY_REPOSITORYNAME"
        val KEY_FULLNAME = "KEY_FULLNAME"

        fun intent(context: Context, repositoryName:String?, fullName: String?): Intent {
            val intent = Intent(context, SubscriberActivity::class.java)
            intent.putExtras(Bundle().apply {
                repositoryName?.let { putString(KEY_REPOSITORYNAME, it)}
                fullName?.let { putString(KEY_FULLNAME, it) }
            })
            return intent
        }
    }
}

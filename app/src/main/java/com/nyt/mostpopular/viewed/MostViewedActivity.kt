package com.nyt.mostpopular.viewed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyt.mostpopular.R

class MostViewedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostviewed_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MostViewedFragment.newInstance())
                .commitNow()
        }
    }

}

package com.example.customimagelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customimagelist.demo.DemoFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DemoFragment.newInstance())
                .commit()
        }
    }
}

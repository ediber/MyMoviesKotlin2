package com.e.mymovieskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.e.mynavigationtest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

       // val binding = DataBindingUtil.setContentView<ActivityMain1Binding>(this, R.layout.activity_main1)

    }
}

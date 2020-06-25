package com.example.myapplication.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R


class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,IndicatorFragment.newInstance()).commit()
    }
}
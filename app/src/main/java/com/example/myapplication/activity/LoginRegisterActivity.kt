package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.adapter.ViewPagerAdapter
import com.example.myapplication.fragment.LoginFragment
import com.example.myapplication.fragment.RegisterFragment

import kotlinx.android.synthetic.main.activity_login_register.*

class LoginRegisterActivity : AppCompatActivity() {
    private val mFragments = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        initView()
    }

    private fun initView() {
        mFragments.add(LoginFragment())
        mFragments.add(RegisterFragment())
        viewPager.adapter =
            ViewPagerAdapter(supportFragmentManager, mFragments, listOf("LOGIN", "REGISTER"))
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mFragments.get(1).onActivityResult(requestCode,resultCode,data)
    }
}
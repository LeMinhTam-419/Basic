package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.myapplication.R
import com.example.myapplication.adapter.ViewPagerAdapter


import kotlinx.android.synthetic.main.fragment_todo_done.tabLayout
import kotlinx.android.synthetic.main.fragment_todo_done.viewPager

class TodoDoneFragment : Fragment() {
    companion object{
        private val instance=TodoDoneFragment()
        fun newInstance():TodoDoneFragment{
            return instance
        }
    }
    private val mFragments= mutableListOf<Fragment>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_done, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView(){
        mFragments.add(TodoFragment())
        mFragments.add(DoneFragment())

        viewPager.adapter = ViewPagerAdapter(
            fragmentManager!!,
            mFragments,
            listOf("todo", "done")
        )
        tabLayout.setupWithViewPager(viewPager)
    }

}
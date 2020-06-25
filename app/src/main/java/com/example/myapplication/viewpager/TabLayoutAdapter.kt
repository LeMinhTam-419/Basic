package com.example.myapplication.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class TabLayoutAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                Tab1Fragment()
            }
            1->{
                Tab2Fragment()
            }
            else->{
                Tab3Fragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Tab 1"
            1-> "Tab 2"
            else -> "Tab 3"
        }
    }
}
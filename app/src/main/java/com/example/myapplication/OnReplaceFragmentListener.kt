package com.example.myapplication


import androidx.fragment.app.Fragment

interface OnReplaceFragmentListener {
    fun onFragmentInteraction(fragment: Fragment, isAddToBackTack: Boolean)
}
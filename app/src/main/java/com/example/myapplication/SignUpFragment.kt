package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.fragment_sign.*
import kotlinx.android.synthetic.main.fragment_sign.edtEmail

class SignUpFragment : Fragment() {

    var onClickListener: ((fragment: Fragment, isAddToBackTack: Boolean) -> Unit) = { _, _ -> }
    private var mListener: OnReplaceFragmentListener? = null

    companion object {
        private val instance = SignUpFragment()
        fun newInstance(): SignUpFragment {
            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignUp.setOnClickListener {
            mListener?.onFragmentInteraction(
                LoginFragment.newInstance(edtFullName.text.toString(), edtEmail.text.toString()),
                true
            )
        }
        tvHere.setOnClickListener {
            mListener?.onFragmentInteraction(LoginFragment.newInstance(), false)
        }
   }

    /** III */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnReplaceFragmentListener) {
            mListener = context
        }
    }
}
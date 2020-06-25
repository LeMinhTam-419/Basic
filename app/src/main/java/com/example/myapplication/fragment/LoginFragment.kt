package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.activity.SaveDataActivity
import com.example.myapplication.database.DatabaseHelper

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private var databaseHelper: DatabaseHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener{login()}

    }

   private fun login() {
            val username = edtUserName.getText().toString()
                .trim()//trim là cái chuỗi đó bỏ đi cái khoảng trắng.
            val password = edtPassWord.getText().toString().trim()
            if (username.isEmpty()) {
                Snackbar.make(btnLogin, "k hop le", Snackbar.LENGTH_SHORT).setAction("ok", {})
                    .show()
            } else if (password.isEmpty()) {
                Snackbar.make(btnLogin, "k hop le", Snackbar.LENGTH_SHORT).setAction("ok", {})
                    .show()
            } else {
                val user = databaseHelper?.listOfUserInfo()
                if (user != null) {
                    // mSharedPrefs?.putInt(SharedPrefs.ID_USER, user.id)
                    startActivity(Intent(context, SaveDataActivity::class.java))
                } else {
                    Snackbar.make(btnLogin, "mk k dung", Snackbar.LENGTH_SHORT).setAction("ok", {})
                        .show()
                }
            }
        }
    }




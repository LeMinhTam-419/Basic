package com.example.myapplication.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.data.User
import com.example.myapplication.database.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {


    private var databaseHelper: DatabaseHelper?=null
    companion object {
        private val instance=RegisterFragment()
        fun newInstance():RegisterFragment{
            return instance
        }
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgAvatar.setOnClickListener{dispatchTakePictureIntent()}
        btnRegister.setOnClickListener{doRegister()}


        }
    private fun doRegister() {
        val username = edtUserName.getText().toString().trim()
        val nickname = edtNickName.getText().toString().trim()
        val password = edtPassWord.getText().toString().trim()

       if (username.isEmpty()) {
            Snackbar.make(btnRegister, "k hop le", Snackbar.LENGTH_SHORT).setAction("ok", {}).show()
        } else if (nickname.isEmpty()) {
            Snackbar.make(btnRegister, "k hop le", Snackbar.LENGTH_SHORT).setAction("ok", {}).show()
        } else if (password.isEmpty()) {
            Snackbar.make(btnRegister, "k hop le", Snackbar.LENGTH_SHORT).setAction("oke", {}).show()
        } else {
            val user = User()

            user.name = username
            user.nickname = nickname
            user.password = password
            val insert = databaseHelper?.insertData(user)
            if (insert == true) {
                imgAvatar.setImageResource(R.drawable.avatar)
                edtUserName.setText("") //sau khi đăng kí thành công. sẽ trả về khoảng trống. tức là k còn thấy tên hiển thị lúc mình bấm
                edtNickName.setText("")
                edtPassWord.setText("")
                Snackbar.make(btnRegister, "dk thanh cong", Snackbar.LENGTH_SHORT).setAction("ok", {}).show()
            }
            else {
                Snackbar.make(btnRegister, "dk that bai", Snackbar.LENGTH_SHORT).setAction("R.string.action_ok", {}).show()
            }
        }

    }
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    activity?.startActivityFromFragment(
                        this,
                        takePictureIntent,
                        REQUEST_IMAGE_CAPTURE
                    )
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imgAvatar.setImageBitmap(imageBitmap)
        }
    }
}
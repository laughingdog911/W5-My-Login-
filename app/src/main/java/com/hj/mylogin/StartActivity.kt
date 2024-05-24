package com.hj.mylogin

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hj.mylogin.LoginFragment
import com.hj.mylogin.databinding.ActivityStartBinding
import com.hj.mylogin.databinding.FragmentLoginBinding


class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.view = this
        binding.lifecycleOwner = this

        val transactionManager = supportFragmentManager.beginTransaction()
        transactionManager.replace(R.id.main, LoginFragment())
        transactionManager.commit()
    }


}
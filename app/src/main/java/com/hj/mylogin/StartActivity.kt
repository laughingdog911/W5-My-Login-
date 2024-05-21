package com.hj.mylogin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.RegisterFragment

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_start)

        val transactionManager = supportFragmentManager.beginTransaction()
        transactionManager.replace(R.id.main, RegisterFragment())
        transactionManager.commit()

        }
    }
}
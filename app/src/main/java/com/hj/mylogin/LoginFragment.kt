package com.hj.mylogin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hj.mylogin.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =
            DataBindingUtil.setContentView((activity as StartActivity), R.layout.fragment_login)
        return binding.root
    }

    fun onClick(v: View) {
        val email = binding.email.text.toString()
        val getPW = binding.pw.text.toString()
        val infoList: MutableList<String> = mutableListOf(email, getPW)

        when (v) {
            binding.resetPWButton -> {
                val transactionManager =
                    (activity as StartActivity).supportFragmentManager.beginTransaction()
                transactionManager.replace(R.id.entryPoint, ResetPwFragment()).commit()
            }

            binding.registerButton -> {
                val transactionManager =
                    (activity as StartActivity).supportFragmentManager.beginTransaction()
                transactionManager.replace(R.id.entryPoint, RegisterFragment()).commit()
            }

            binding.loginButton -> {
                val empty = "empty"
                for (i in infoList.indices) {
                    if (infoList[i].isEmpty()) {
                        infoList[i] = empty
                    }
                }
                if (infoList.any { it == empty }) {
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("공백 필드")
                        setMessage("모든 필드를 채워주세요.")
                        setPositiveButton("확인", null)
                        show()
                    }
                }else {
                    binding.loginButton.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}
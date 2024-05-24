package com.hj.mylogin

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.view = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun onClick(v: View) {
        val email = binding.email.text.toString()
        val getPW = binding.pw.text.toString()
        val infoList: MutableList<String> = mutableListOf(email, getPW)

        when (v) {
            binding.resetPWButton -> {
                val transactionManager =                                                        // 비밀번호 재설정 버튼: 비밀번호 재설정 화면으로 전환
                    (activity as StartActivity).supportFragmentManager.beginTransaction()
                transactionManager.replace(R.id.entryPoint, ResetPwFragment()).addToBackStack("").commit()
            }

            binding.registerButton -> {                                                         // 화원가입 버튼 : 회원가입 화면으로 전환
                val transactionManager =
                    (activity as StartActivity).supportFragmentManager.beginTransaction()
                transactionManager.replace(R.id.entryPoint, RegisterFragment()).addToBackStack("").commit()
            }

            binding.loginButton -> {                                                            // 로그인 버튼 :
                val empty = "empty"                                                             //이메일, 비밀번호 란이 비었는지 확인:
                for (i in infoList.indices) {
                    if (infoList[i].isEmpty()) {
                        infoList[i] = empty
                    }
                }
                if (infoList.any { it == empty }) {                                             // 1. 공백 필드
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("공백 필드")
                        setMessage("모든 필드를 채워주세요.")
                        setPositiveButton("확인", null)
                        show()
                    }
                }else {                                                                         //2. 다 채워 넣고 로그인 버튼을 눌렀을 때:
                    binding.progressCircular.visibility = View.VISIBLE                          //progress 띄우기.
                }
            }
        }
    }
}
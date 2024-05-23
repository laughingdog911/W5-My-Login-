package com.hj.mylogin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hj.mylogin.databinding.FragmentResetPwBinding

class ResetPwFragment : Fragment() {
    private lateinit var binding: FragmentResetPwBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =
            DataBindingUtil.setContentView((activity as StartActivity), R.layout.fragment_reset_pw)

        return binding.root
    }

    fun onClick(v: View) {
        val email = binding.email.text.toString()

        when (v) {
            binding.backBtn -> {
                (activity as StartActivity).supportFragmentManager.popBackStack()
            }

            binding.sendButton -> {
                if (email.isEmpty()) {
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("공백 필드")
                        setMessage("이메일을 입력해주세요.")
                        setPositiveButton("확인", null)
                        show()
                    }
                } else {
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_email)
                        setTitle("전송 완료")
                        setMessage("입력하신 이메일로 비밀번호 재설정 링크를 전송했습니다. \n 링크를 확인하여 비밀번호를 다시 설정해 주세요.")
                        setNeutralButton("확인", null)
                        show()
                    }
                }
            }
        }
    }
}
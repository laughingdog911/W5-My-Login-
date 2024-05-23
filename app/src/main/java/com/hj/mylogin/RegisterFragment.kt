package com.hj.mylogin

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hj.mylogin.databinding.FragmentRegisterBinding
import java.util.Calendar

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.setContentView((activity as StartActivity), R.layout.fragment_register)
        return binding.root
    }
    private fun getString():String(
        return if(selectedType.value == )
    )
    fun onClick(v: View) {
        val phoneNumber = binding.phoneno.text.toString()
        val email = binding.email.text.toString()
        val name = binding.name.text.toString()
        val birthday = binding.bdaydp.text.toString()
        val getPW = binding.pw.text.toString()
        val getCheckPW = binding.checkpw.text.toString()
        val infoList: MutableList<String> =
            mutableListOf(email, name, phoneNumber, getPW, getCheckPW)
        val eulaAgreed = binding.EULAAgree.isChecked
        val infoAgreed = binding.informaticsAgree.isChecked

        val selectedDate = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            binding.bdaydp.text = "${year}. ${month + 1}. ${day}"
        }
        val calendar = Calendar.getInstance()

        when (v) {
            binding.backBtn -> {
                (activity as StartActivity).supportFragmentManager.popBackStack()
            }

            binding.choose -> {
                DatePickerDialog(
                    (activity as StartActivity), selectedDate, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),   //this = context  required butttttttt
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            binding.stdtmember -> {TODO()}
            binding.profmember -> { TODO() }
            binding.emplmember -> { TODO() }

            binding.confirmButton -> {
                val empty = "empty"
                for (i in infoList.indices) {
                    if (infoList[i].isEmpty()) {
                        infoList[i] = empty
                    }
                }
                if (infoList.any { it == empty }) {                            //1. 공백필드 !!!!!!!!
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("공백 필드")
                        setMessage("모든 필드를 채워주세요.")
                        setPositiveButton("확인", null)
                        show()
                    }
                } else if (!email.contains("@")) {                        //2. 잘못된 이메일 형식
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("잘못된 이메일")
                        setMessage("잘못된 형식의 이메일입니다.")
                        setPositiveButton("확인", null)
                        show()
                    }
                } else if (getPW.length <= 7) {                                //3. 안전하지 않은 비밀번호
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("안전하지 않은 비밀번호")
                        setMessage("보안을 위해 8자리 이상의 비밀번호를 설정해주세요.")
                        setPositiveButton("확인", null)
                        show()
                    }
                } else if (getCheckPW != getPW) {                              //4. 비밀번호 확인과 불일치
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("일치하지 않는 비밀번호")
                        setMessage("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
                        setPositiveButton("확인", null)
                        show()
                    }
                } else if (!(eulaAgreed && infoAgreed)) {                       //5. 계약서 미동의
                    AlertDialog.Builder(activity as StartActivity).run {
                        setIcon(android.R.drawable.ic_dialog_alert)
                        setTitle("동의 필요")
                        setMessage("최종 사용권 계약과 개인정보 수집 및 처리 방침을 읽고 동의해주세요.")
                        setPositiveButton("확인", null)
                        show()
                    }
                } else {                                                       //6. 마지막 회원가입 확인
                    AlertDialog.Builder(activity as StartActivity).run {
                        setTitle("회원가입")
                        setMessage("다음 정보로 가입을 진행하시겠습니까?\n" +
                                "이름: ${name}\n" +
                                "이메일: ${email}\n" +
                                "생년월일: ${birthday}\n" +                           //회원 유형도 넣기!!!!!!
                                "전화 번호: ${phoneNumber}\n" +
                                "멤버 유형: ${selectedType.member}")
                        setIcon(android.R.drawable.ic_dialog_info)
                        setPositiveButton("예") { dialogInterface, which ->
                            dialogInterface.dismiss()
                            binding.confirmButton.visibility = View.INVISIBLE
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        setNegativeButton("아니오") { dialogInterface, which ->
                            dialogInterface.dismiss()
                        }
                        show()
                    }
                }
            }
        }
    }
}

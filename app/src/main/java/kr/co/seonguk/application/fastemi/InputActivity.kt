package kr.co.seonguk.application.fastemi

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import kr.co.seonguk.application.fastemi.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding:ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        settingEvent()

    }

    fun initView(){
        binding.apply {
            bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
                this@InputActivity, R.array.blood_types, android.R.layout.simple_list_item_1
            )
        }
    }

    fun settingEvent(){
        binding.apply {
            bithdateLayer.setOnClickListener {
                val listener = OnDateSetListener{ _, year, month, dayOfMonth ->
                    binding.birthDateValueEditText.text = "${year}년-${month.inc()}월-${dayOfMonth}일"
                }
                DatePickerDialog(this@InputActivity, listener, 2000, 1, 1).show()
            }
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                binding.notandumValueEditText.isVisible = isChecked
            }

            buttonSave.setOnClickListener {
                saveData()
                finish()
            }


        }
    }

    fun saveData(){
        binding.apply {
            val name = nameValueEditText.text.toString()
            val birthDate = birthDateValueEditText.text.toString()
            val phoneNumber = EmergencyValueEditText.text.toString()



            var editor = getSharedPreferences(USER_INFO, MODE_PRIVATE).edit()
            editor.putString(NAME, name)
            editor.putString(BLOOD_TYPE, getBloodType())
            editor.putString(EMERGENCY_CONTACT,phoneNumber )
            editor.putString(BIRTH_DATE,birthDate)
            editor.putString(WARNING,getWarning() )
            editor.apply()
            Toast.makeText(this@InputActivity, "데이터가 저장되었습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBloodType(): String {
        binding.apply {
            val blood = bloodTypeSpinner.selectedItem.toString()
            val bloodSigned = if (radioButton.isChecked){
                "-"
            }else{
                "+"
            }
            return "$bloodSigned$blood"
        }
    }

    private fun getWarning():String{
        binding.apply {
            return if (checkBox.isChecked){
                notandumValueEditText.text.toString()
            }else{
                ""
            }
        }
    }
}
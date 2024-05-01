package kr.co.seonguk.application.fastemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import kr.co.seonguk.application.fastemi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingEvent()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    fun settingEvent(){
        with(binding){
            floatingActionButton.setOnClickListener {
                val newIntent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(newIntent)
            }

            buttonDelete.setOnClickListener {
                deleteData()
            }
        }
    }

    private fun getData(){
        binding.apply {
            with(getSharedPreferences(USER_INFO, MODE_PRIVATE)){
                nameValueTextView.text = getString(NAME, "")
                birthDateValueTextView.text = getString(BIRTH_DATE, "")
                bloodTypeValueTextView.text = getString(BLOOD_TYPE, "")
                EmergencyValueTextView.text = getString(EMERGENCY_CONTACT, "")
                val warning = getString(WARNING, "")
                if (warning.isNullOrEmpty()){
                    notandumValueTextView.isVisible = false
                    textView5.isVisible = false
                }else{
                    notandumValueTextView.isVisible = true
                    textView5.isVisible = true
                    notandumValueTextView.text = getString(WARNING, "")
                }
            }
        }
    }

    private fun deleteData(){
        getSharedPreferences(USER_INFO, MODE_PRIVATE).edit().apply{
            clear()
            apply()
            getData()
        }
        Toast.makeText(this, "초기화를 완료했습니다", Toast.LENGTH_SHORT).show()
    }
}
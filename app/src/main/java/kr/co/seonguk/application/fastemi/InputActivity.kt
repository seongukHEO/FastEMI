package kr.co.seonguk.application.fastemi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kr.co.seonguk.application.fastemi.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var binding:ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    fun initView(){
        binding.apply {
            bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
                this@InputActivity, R.array.blood_types, android.R.layout.simple_list_item_1
            )
        }
    }
}
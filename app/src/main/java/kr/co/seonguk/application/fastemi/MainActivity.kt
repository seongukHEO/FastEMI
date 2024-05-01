package kr.co.seonguk.application.fastemi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.seonguk.application.fastemi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingEvent()
    }

    fun settingEvent(){
        with(binding){
            floatingActionButton.setOnClickListener {
                val newIntent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(newIntent)
            }
        }
    }
}
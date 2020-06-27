package br.com.eduardorodrigues.calculatebmi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import android.os.Bundle as Bundle1

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Captura os valores da MainActivity
        var bmi = intent.getSerializableExtra("bmi")
        var level = intent.getSerializableExtra("level")

        //Passa para o "textView" da ResultActivity os valores buscados da MainActivity
        txtResp.text = "$bmi"
        txtLevel.text = "$level"

        btnBack.setOnClickListener {
            val res = ""
            val level = ""
            startActivity(
                Intent(this@ResultActivity, MainActivity::class.java).apply {
                    putExtra("bmi", res)
                    putExtra("level", level)
                }
            )
        }

    }
}
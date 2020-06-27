package br.com.eduardorodrigues.calculatebmi

import android.content.Context
import android.content.Intent
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import android.os.Bundle as Bundle1


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                i.toString().toFloat()
                txtWeightValue.text = "$i"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                txtHeightValue.text = "$i"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnCalculate.setOnClickListener {
            if (txtHeightValue.text.toString().toFloat() <= 0 || txtWeightValue.text.toString()
                    .toFloat() <= 0
            ) {
                Toast.makeText(
                    this@MainActivity,
                    "Nenhumas das informações devem ser zero.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var level = ""
                val h = txtHeightValue.text.toString().toFloat() / 100
                val w = txtWeightValue.text.toString().toFloat()
                val decimals = DecimalFormat("#.##")
                var res = decimals.format(w / (h * h))

                if (res <= "15.0") {
                    level = "Very Severely Underweight"
                } else if (res > "15.0" && res <= "16.0") {
                    level = "Severely Underweight"
                } else if (res > "16.0" && res <= "18.5") {
                    level = "Underweight"
                } else if (res > "18.5" && res <= "25.0") {
                    level = "Normal (healthy weight)"
                } else if (res > "25.0" && res <= "30.0") {
                    level = "Overweight"
                } else if (res > "30.0" && res <= "35.0") {
                    level = "Obese Class I (moderately obese)"
                } else if (res > "35.0" && res <= "40.0") {
                    level = "Obese Class II (severely obese)"
                } else {
                    level = "Obese Class III (very severely obese)"
                }

                //Chama a ResultActivity passando dois valores da MainActivity através dos identificadores "bmi" e "level"
                startActivity(
                    Intent(this@MainActivity, ResultActivity::class.java).apply {
                        putExtra("bmi", res)
                        putExtra("level", level)
                    }
                )

                //Tirar todas as telas do empilhamento
                finishAffinity()
            }
        }
    }
}


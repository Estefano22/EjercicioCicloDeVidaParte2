package com.example.ejerciciociclodevida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.ejerciciociclodevida.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Esto hay que crearlo siempre
    lateinit var binding: ActivityMainBinding
    var contador = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Forma 1 de obtener el text view
        val textView1 = findViewById<TextView>(R.id.tv_text)
        textView1.text = "Texto modificado usando findViewById"

        // Forma 2. Usar el Binding. Está es la mejor.
        // Requiere cambios es el Gradle. Crear el binding y modificar el setContentView
        /* Esto hay que ponerlo en Gradle, dentro de la sección android
        buildFeatures {
            viewBinding = true
        }
        */
        binding.tvText.text = "Texto Modificado usando el binding"


        Log.w("Carlos", "onCreate ${contador++}")

    }

    override fun onStart() {
        super.onStart()
        Log.w("Carlos", "onStart ${contador++}")

    }

    override fun onResume() {
        super.onResume()
        Log.w("Carlos", "onResume ${contador++}")

    }

    override fun onRestart() {
        super.onRestart()
        Log.w("Carlos", "onRestart ${contador++}")

    }

    override fun onPause() {
        Log.w("Carlos", "onPause ${contador++}")
        super.onPause()
    }

    override fun onStop() {
        Log.w("Carlos", "onStop ${contador++}")
        super.onStop()
    }

    override fun onDestroy() {
        Log.w("Carlos", "onDestroy ${contador++}")
        super.onDestroy()
    }


}
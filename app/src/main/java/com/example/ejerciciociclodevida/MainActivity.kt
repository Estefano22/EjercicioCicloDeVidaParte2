package com.example.ejerciciociclodevida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.ejerciciociclodevida.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Esto hay que crearlo siempre
    lateinit var binding: ActivityMainBinding
    var contador = 1

    var listaPersonas = mutableListOf(
        Persona("Estefano",20,8.3,1.69),
        Persona("Juan",19,7.7,1.74),
        Persona("Alex",22,6.8,1.65),
        Persona("Ricardo",21,6.3,1.63),
        )



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
        binding.tvText.text = "Lista de personas"
        binding.tvText2.text = "Criterio Ordenacion"


        Log.w("Estefano", "onCreate ${contador++}")

        mostrarlistasPersonas()


    }

    override fun onStart() {
        super.onStart()
        Log.w("Estefano", "onStart ${contador++}")

    }

    override fun onResume() {
        super.onResume()
        Log.w("Estefano", "onResume ${contador++}")

    }

    override fun onRestart() {
        super.onRestart()
        Log.w("Estefano", "onRestart ${contador++}")

    }

    override fun onPause() {
        Log.w("Estefano", "onPause ${contador++}")
        super.onPause()
    }

    override fun onStop() {
        Log.w("Estefano", "onStop ${contador++}")
        super.onStop()
    }

    override fun onDestroy() {
        Log.w("Estefano", "onDestroy ${contador++}")
        super.onDestroy()
    }


    fun mostrarlistasPersonas(){

        val opcion1 = Random.nextInt(0, 3)
        when(opcion1){

            0 -> listaPersonas.sortBy { binding.tvText2.text = "Se ha ordenado por nombre de Mayor a Menor"
                it.nombre }
            1 -> listaPersonas.sortBy { it.edad }
            2 -> listaPersonas.sortByDescending { it.nombre }
            3 -> listaPersonas.sortByDescending { binding.tvText2.text = "Se ha ordenado de más alto a más bajo"
                it.altura }

        }

        binding.tvText.text = listaPersonas.toString()

    }




    data class Persona(var nombre : String, var edad : Int, var notamedia : Double, var altura : Double) {
        override fun toString(): String {
            return "\nMe llamo $nombre, tengo $edad, mi nota media es $notamedia, y mido $altura \n"
        }


    }


}
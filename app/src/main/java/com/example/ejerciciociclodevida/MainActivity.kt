package com.example.ejerciciociclodevida

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.ejerciciociclodevida.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Esto hay que crearlo siempre

    companion object{
        const val  TAG_NOMBRE = "1234"
        const val  TAG_EDAD = "4321"
        const val TAG_NOTA = "1256f"
        const val  TAG_ALTURA = "135c4523r"
    }

    lateinit var binding: ActivityMainBinding
    var contador = 1


    var listaPersonas = mutableListOf(
        Persona("Estefano",20,8.3,1.69),
        Persona("Juan",19,7.7,1.74),
        Persona("Alex",22,6.8,1.65),
        Persona("Ricardo",21,6.3,1.63),
    )

    val p = Persona("Tio raro",Random.nextInt(),6.3,1.63)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarPreferencias()?.let {
            binding.tvText.setText(it.nombre + it.altura)
            binding.tvText2.setText("${it.edad} y ${ it.notamedia}") // siempre hay que  definir un string ya que si tienen un int y un float el textview no sabe reconocer int
        }

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
        super.onStop()
        Log.w("Estefano", "onStop ${contador++}")


    }

    override fun onDestroy() {
        Log.w("Estefano", "onDestroy ${contador++}")
        super.onDestroy()
    }


    fun mostrarlistasPersonas(){

        val opcion1 = Random.nextInt(0, 3)
        when(opcion1){

            0 -> {
                binding.tvText2.text = "Se ha ordenado por nombre de Menor a Mayor"
                listaPersonas.sortBy { it.nombre }
            }
            1 -> {
                binding.tvText2.text = "Ordenado de Menor a Mayor por edad"
                listaPersonas.sortBy { it.edad }
            }

            2 -> {
                binding.tvText2.text = "Se ha ordenado por nombre de Mayor a Menor"
                listaPersonas.sortByDescending { it.nombre }
            }

            3 -> {
                binding.tvText2.text = "Se ha ordenado de más alto a más bajo"
                listaPersonas.sortByDescending { it.altura }
            }

        }

        binding.tvText.text = listaPersonas.toString().replace("[", "").replace("]","").replace(",","")

    }

    private fun cargarPreferencias() : Persona {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        var nombre = sharedPref.getString(TAG_NOMBRE, "")
        var edad = sharedPref.getInt(TAG_EDAD, 0)
        var altura = sharedPref.getFloat(TAG_NOTA, 0F)
        var notamedia =  sharedPref.getFloat(TAG_ALTURA, 0F)

        nombre?.let {
            return Persona(it, edad, altura.toDouble(), notamedia.toDouble())
        } ?: run {
            return Persona("No tiene nombre", edad, altura.toDouble(), notamedia.toDouble())
        }
    }
    private fun guardarPreferencias(p:Persona) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString(TAG_NOMBRE, p.nombre)
            putInt(TAG_EDAD, p.edad)
            putFloat(TAG_ALTURA, p.altura.toFloat())
            putFloat(TAG_NOTA, p.notamedia.toFloat())
            commit()
        }
    }

    data class Persona(var nombre : String, var edad : Int, var notamedia : Double, var altura : Double) {
        override fun toString(): String {
            return "\nMe llamo $nombre, tengo $edad, mi nota media es $notamedia, y mido $altura \n"
        }

    }

}
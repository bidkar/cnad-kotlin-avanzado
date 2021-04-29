package com.cetis108.ejemplo01_listviewcars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cetis108.ejemplo01_listviewcars.databinding.ActivityCocheBinding

class CocheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coche)

        val binding = ActivityCocheBinding.inflate(layoutInflater)

        val bundle: Bundle? = intent.extras
        val coche = bundle!!.getSerializable("coche") as Coche

        binding.apply {
            ivCocheImagen.setImageResource(coche.imagen!!)
            tvCocheTitulo.text = coche.titulo
            tvCocheDescripcion.text = coche.descripcion
            tvCochePrecio.text = coche.precio
        }
    }
}
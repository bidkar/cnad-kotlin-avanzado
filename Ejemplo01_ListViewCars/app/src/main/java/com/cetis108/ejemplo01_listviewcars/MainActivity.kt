package com.cetis108.ejemplo01_listviewcars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cetis108.ejemplo01_listviewcars.databinding.ActivityMainBinding

class MainActivity(var adapter: CochesAdapter? = null) : AppCompatActivity() {
    //var listaDeCoches = ArrayList<Coche>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        val listaDeCoches = crearListaDeCoches()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CochesAdapter(this, listaDeCoches)
        binding.lvMainCoches.adapter = adapter
    }

    fun crearListaDeCoches(): ArrayList<Coche> {
        val listaDeCoches = ArrayList<Coche>()
        listaDeCoches.add(
            Coche(
                R.drawable.hw_bowser_sm,
                "Bowser",
                "Coche del malvado Bowser",
                "350.00",
                true
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_buddy_car,
                "Buddy",
                "Coche de Buddy",
                "250.00",
                false
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_camaro_ee_2015,
                "Camaro",
                "Coche Camaro 2015",
                "550.00",
                true
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_charger_2014,
                "Charger",
                "Coche Charger 2014",
                "450.00",
                false
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_fury_shark,
                "Fury Shark",
                "Coche de Tiburón",
                "350.00",
                false
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_mario_sm,
                "Mario",
                "Coche de Mario",
                "200.00",
                false
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_toad_sm,
                "Toad",
                "Coche de Toad",
                "150.00",
                false
            )
        )
        listaDeCoches.add(
            Coche(
                R.drawable.hw_yoshi_sm,
                "Yoshi",
                "Coche de Yoshi",
                "650.00",
                false
            )
        )
        return listaDeCoches
    }
}
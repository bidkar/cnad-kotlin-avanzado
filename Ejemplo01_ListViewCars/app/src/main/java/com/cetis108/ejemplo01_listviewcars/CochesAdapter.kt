package com.cetis108.ejemplo01_listviewcars

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cetis108.ejemplo01_listviewcars.databinding.MoldeCocheBinding

class CochesAdapter(_contexto: Context, var listaDeCoches: ArrayList<Coche>) : BaseAdapter() {
    var contexto : Context? = _contexto

    override fun getCount(): Int {
        return listaDeCoches.size
    }

    override fun getItem(position: Int): Any {
        return listaDeCoches[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val coche = listaDeCoches[position]
        val inflater = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = MoldeCocheBinding.inflate(inflater, parent, false)

        binding.ivMoldeCocheImagen.setImageResource(coche.imagen!!)
        binding.tvMoldeCocheTitulo.text = coche.titulo!!
        binding.tvMoldeCocheDescripcion.text = coche.descripcion!!
        binding.tvMoldeCochePrecio.text = coche.precio!!

        binding.ivMoldeCocheImagen.setOnClickListener {
            val intent = Intent(contexto, CocheActivity::class.java)
            intent.putExtra("coche", coche)
            contexto!!.startActivity(intent)
        }

        return binding.root
    }

}
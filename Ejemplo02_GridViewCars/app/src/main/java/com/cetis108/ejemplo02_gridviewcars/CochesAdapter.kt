package com.cetis108.ejemplo02_gridviewcars

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cetis108.ejemplo02_gridviewcars.databinding.MoldeCocheGridBinding

class CochesAdapter(contexto: Context, var listaDeCoches: ArrayList<Coche>) : BaseAdapter() {

    var contexto: Context? = contexto

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
        val inflater =
            contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miVista = MoldeCocheGridBinding.inflate(inflater, parent, false)
        val view = miVista.root
        miVista.cocheImagen.setImageResource(coche.imagen!!)
        miVista.cocheTitulo.text = coche.titulo!!
        miVista.cocheDescripcion.text = coche.descripcion!!
        miVista.cochePrecio.text = coche.precio!!

        miVista.cocheImagen.setOnClickListener {
            val intent = Intent(
                contexto,
                CocheActivity::class.java
            )
            intent.putExtra("Imagen", coche.imagen!!)
            intent.putExtra("Título", coche.titulo!!)
            intent.putExtra("Descripción", coche.descripcion!!)
            intent.putExtra("Precio", coche.precio!!)
        }

        return view
    }

}
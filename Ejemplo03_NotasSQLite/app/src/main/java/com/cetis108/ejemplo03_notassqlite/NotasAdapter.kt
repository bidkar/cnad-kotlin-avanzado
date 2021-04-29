package com.cetis108.ejemplo03_notassqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cetis108.ejemplo03_notassqlite.databinding.MoldeNotasBinding

class NotasAdapter(_contexto: Context, var listaDeNotas: ArrayList<Nota>) : BaseAdapter() {
    var contexto: Context = _contexto

    override fun getCount(): Int {
        return listaDeNotas.size
    }

    override fun getItem(position: Int): Any {
        return listaDeNotas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nota = listaDeNotas[position]
        val inflater = contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miVista = MoldeNotasBinding.inflate(inflater, parent, false)

        miVista.notaTitulo.text = nota.Titulo
        miVista.notaDescripcion.text = nota.Descripcion

        miVista.btnMoldeNotasEliminar.setOnClickListener {
            val dbManager = DBManager(this.contexto)
            val selectionArgs = arrayOf(nota.Id.toString())

            dbManager.borrar("Id = ?", selectionArgs)
            MainActivity().cargarQuery("%")
        }

        miVista.btnMoldeNotasEditar.setOnClickListener {
            val intent = Intent(contexto, AddActivity::class.java)
//            intent.putExtra("Id", nota.Id)
//            intent.putExtra("Titulo", nota.Titulo)
//            intent.putExtra("Descripcion", nota.Descripcion)
            intent.putExtra("nota", nota)
            contexto!!.startActivity(intent)
        }

        return miVista.root
    }

}
package com.cetis108.ejemplo03_notassqlite

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.cetis108.ejemplo03_notassqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var adapter: NotasAdapter? = null
    var listaDeNotas = ArrayList<Nota>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*listaDeNotas.add(Nota(1,"Titulo","Descripción"))
        listaDeNotas.add(Nota(2,"Titulo","Descripción"))
        listaDeNotas.add(Nota(3,"Titulo","Descripción"))

        adapter = NotasAdapter(this, listaDeNotas)
        binding.lvMainNotas.adapter = adapter*/
        cargarQuery("%")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val buscar = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val manejador = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        buscar.setSearchableInfo(manejador.getSearchableInfo(componentName))
        buscar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                cargarQuery("%$query%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.menuAgregar -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun cargarQuery(titulo: String) {
        var baseDeDatos = DBManager(this)
        val columnas = arrayOf("Id","Titulo","Descripcion")
        val selectionArgs = arrayOf(titulo)
        val cursor = baseDeDatos.query(columnas, "Titulo like ?", selectionArgs, "Titulo")

        listaDeNotas.clear()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val titulo = cursor.getString(cursor.getColumnIndex("Titulo"))
                val descripcion = cursor.getString(cursor.getColumnIndex("Descripcion"))

                listaDeNotas.add(Nota(id, titulo, descripcion))
            } while (cursor.moveToNext())
        }
        adapter = NotasAdapter(this, listaDeNotas)
        val listView: ListView = findViewById(R.id.lvMainNotas)
        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        cargarQuery("%")
    }
}
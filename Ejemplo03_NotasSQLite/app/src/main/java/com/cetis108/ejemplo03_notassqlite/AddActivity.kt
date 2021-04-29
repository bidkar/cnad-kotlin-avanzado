package com.cetis108.ejemplo03_notassqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cetis108.ejemplo03_notassqlite.databinding.ActivityAddBinding
import com.cetis108.ejemplo03_notassqlite.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_add)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nota = intent.getSerializableExtra("nota") as Nota
    }

    fun btnAdd(view: View) {
        val baseDeDatos = DBManager(this)
        val values = ContentValues()
        val et_add_titulo = findViewById<EditText>(R.id.etAddTitulo)
        val et_add_contenido = findViewById<EditText>(R.id.etAddDescripcion)
        values.put("Titulo", et_add_titulo.text.toString())
        values.put("Descripcion", et_add_contenido.text.toString())

        val id = baseDeDatos.insert(values)
        if (id > 0) {
            Toast.makeText(this, "Nota agregada correctamente", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Nota no agregada", Toast.LENGTH_LONG).show()
        }
    }
}
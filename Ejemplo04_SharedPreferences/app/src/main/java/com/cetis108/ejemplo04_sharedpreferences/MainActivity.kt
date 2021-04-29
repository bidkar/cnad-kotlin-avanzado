package com.cetis108.ejemplo04_sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class MainActivity : AppCompatActivity() {
    var preferencias: SharedPreferences? = null
    var toolbarCollapse: CollapsingToolbarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferencias = PreferenceManager.getDefaultSharedPreferences(this)
        val toolbar = find<androidx.appcompat.widget.Toolbar>(R.id.toolbarLoginToolbar)
        setSupportActionBar(toolbar)
        toolbarCollapse = find(R.id.collapsing_toolbar)
        toolbarCollapse!!.title = "Kotlin para móviles"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemMenuSalir -> {
                startActivity(intentFor<LoginActivity>().newTask().clearTask())
                true
            }
            R.id.itemMenuSalirOlvidar -> {
                preferencias!!.edit().clear().apply()
                startActivity(intentFor<LoginActivity>().newTask().clearTask())
                true
            }
            else -> false
        }
        return super.onOptionsItemSelected(item)
    }
}
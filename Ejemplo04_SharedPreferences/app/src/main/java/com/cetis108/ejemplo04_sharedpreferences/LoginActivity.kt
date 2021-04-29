package com.cetis108.ejemplo04_sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    var preferencias: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferencias = PreferenceManager.getDefaultSharedPreferences(this)
        ponerPrefSiExisten()

        buttonLoginSignIn.onClick {
            val email = editTextLoginEmailAddress.text.toString()
            val password = editTextLoginPassword.text.toString()

            if (validateLogin(email, password)) {
                savePreferences(email, password)
                startActivity(intentFor<MainActivity>().newTask().clearTask())
            }
        }
    }

    fun ponerPrefSiExisten() {
        val email = preferencias!!.getString("email", "")
        val password = preferencias!!.getString("password", "")
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            editTextLoginEmailAddress.setText(email)
            editTextLoginPassword.setText(password)
            switchLoginRemember.isChecked = true
        }
    }

    /**
     * Guarda en preferencias compartidas los valores de [email] y [password].
     */
    fun savePreferences(email: String, password: String) {
        if (switchLoginRemember.isChecked) {
            preferencias!!.edit()
                .putString("email", email)
                .putString("password", password)
                .apply()
        }
    }

    /**
     * Valida la cadena de caracteres [email]
     *
     * @return `true` si la cadena de caracteres [email] no es `null` o vacía y contiene
     * el formato correcto de una dirección de correo electrónico.
     */
    fun validateEmail(email: String): Boolean {
        if (!email.isNullOrEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true
            }
        }
        return false
    }

    /**
     * Valida la cadena de caracteres [password]
     *
     * @return `true` si la longitud de [password] es mayor o igual a 5
     */
    fun validatePassword(password: String): Boolean {
        return password.length >= 5
    }

    /**
     * Valida el inicio de sesión
     *
     * @return `true` si tanto [email] como [password] son válidos.
     * @see validateEmail
     * @see validatePassword
     */
    fun validateLogin(email: String, password: String): Boolean {
        if (!validateEmail(email)) {
            toast("Email no válido, intenta nuevamente")
            return false
        } else if (!validatePassword(password)) {
            toast("Contraseña no válida, debe contener al menos 5 caracteres")
            return false
        } else {
            return true
        }
    }
}
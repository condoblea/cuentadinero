package com.example.cuentadinero

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.cuentadinero.databinding.ActivityMainBinding

const val VALORES_GUARDADOS_LLAVE = "valoresguardados"

class MainActivity : AppCompatActivity() {

    // TODO Refactor the entire app where posible, the app doesnt calculate anything when open for the first time, remove unused libraries

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CuentaViewModel
    var cantidadesGuardadas: String? = null
    var isUiBlocked = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(CuentaViewModel::class.java)

        cantidadesGuardadas = obtenerCantidades()

        cantidadesGuardadas.let {
            if(it!!.isEmpty())
                return
            val str = it?.split(" ")
            binding.apply {

                editText1000.setText( if(str?.get(0) == "0" || str?.get(0) == "") "" else str?.get(0))
                editText500.setText( if(str?.get(1) == "0" || str?.get(1) == "") "" else str?.get(1))
                editText200.setText( if(str?.get(2) == "0" || str?.get(2) == "") "" else str?.get(2))
                editText100.setText( if(str?.get(3) == "0" || str?.get(3) == "") "" else str?.get(3))
                editText50.setText( if(str?.get(4) == "0" || str?.get(4) == "") "" else str?.get(4))
                editText20.setText( if(str?.get(5) == "0" || str?.get(5) == "") "" else str?.get(5))
                editText1.setText( if(str?.get(6) == "0" || str?.get(6) == "") "" else str?.get(6))
                inicializarValores(str!!)
            }
        }

        binding.cuentaViewModel = viewModel
        binding.lifecycleOwner = this

        binding.apply {
            // 1000 *********************************************************
            editText1000.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value1000.value = viewModel.calculaCantidad(s!!.toInt(), 1000)
                    Log.i("MainActivity", "viewMode.value100 = ${viewModel.value1000.value}")
                    viewModel.sumaTotal()
                }

            })
            // 500 ***********************************************************
            editText500.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value500.value = viewModel.calculaCantidad(s!!.toInt(), 500)
                    viewModel.sumaTotal()
                }

            })
            // 200 ***********************************************************
            editText200.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value200.value = viewModel.calculaCantidad(s!!.toInt(), 200)
                    viewModel.sumaTotal()
                }

            })

            // 100 ***********************************************************
            editText100.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value100.value = viewModel.calculaCantidad(s!!.toInt(), 100)
                    viewModel.sumaTotal()
                }

            })
            // 50 ************************************************************
            editText50.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value50.value = viewModel.calculaCantidad(s!!.toInt(), 50)
                    viewModel.sumaTotal()
                }

            })
            // 20 *************************************************************
            editText20.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value20.value = viewModel.calculaCantidad(s!!.toInt(), 20)
                    viewModel.sumaTotal()
                }

            })
            // 1 *************************************************************
            editText1.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.value1.value = viewModel.calculaCantidad(s!!.toInt(), 1)
                    viewModel.sumaTotal()
                }

            })
        }


    }

    override fun onStop() {
        super.onStop()
        guardarValores()
    }

    fun CharSequence.toInt(): Int {
        if( this == null)
            return 0
        val cadena = this.toString()
        var result = 0
        if(cadena.isNotEmpty())  {
            result = Integer.parseInt(cadena)
        }
        return result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.borrarTodo -> {
                limpiarEntradasTexto()
                true
            }
            R.id.bloquearUI -> {
                if (isUiBlocked) {
                    desbloquearUI()
                } else {
                    bloquearUI()
                }
                isUiBlocked = !isUiBlocked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun limpiarEntradasTexto() {
        binding.apply {
            editText1000.text.clear()
            editText500.text.clear()
            editText200.text.clear()
            editText100.text.clear()
            editText50.text.clear()
            editText20.text.clear()
            editText1.text.clear()
        }

    }

    private fun guardarValores() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val cadenaCantidades =
            (if(binding.editText1000.text.toString().isNotEmpty()) binding.editText1000.text.toString() else "0") + " " +
                    (if(binding.editText500.text.toString().isNotEmpty()) binding.editText500.text.toString() else "0") + " " +
                            (if(binding.editText200.text.toString().isNotEmpty()) binding.editText200.text.toString() else "0") + " " +
                                    (if(binding.editText100.text.toString().isNotEmpty()) binding.editText100.text.toString() else "0") + " " +
                                            (if(binding.editText50.text.toString().isNotEmpty()) binding.editText50.text.toString() else "0") + " " +
                                                    (if(binding.editText20.text.toString().isNotEmpty()) binding.editText20.text.toString() else "0") + " " +
                                                            (if(binding.editText1.text.toString().isNotEmpty()) binding.editText1.text.toString() else "0")

        with(sharedPref.edit()) {
            putString(VALORES_GUARDADOS_LLAVE, cadenaCantidades)
            commit()
        }
    }

    private fun obtenerCantidades(): String {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val result = sharedPref.getString(VALORES_GUARDADOS_LLAVE, "") ?: ""
        Log.i("MainActivity", result)
        return result
    }

    private fun inicializarValores(lista: List<String>) {
        viewModel.value1000.value = viewModel.calculaCantidad(Integer.parseInt(lista[0]), 1000)
        viewModel.value500.value  = viewModel.calculaCantidad(Integer.parseInt(lista[1]), 500)
        viewModel.value200.value  = viewModel.calculaCantidad(Integer.parseInt(lista[2]), 200)
        viewModel.value100.value  = viewModel.calculaCantidad(Integer.parseInt(lista[3]), 100)
        viewModel.value50.value   = viewModel.calculaCantidad(Integer.parseInt(lista[4]), 50)
        viewModel.value20.value   = viewModel.calculaCantidad(Integer.parseInt(lista[5]), 20)
        viewModel.value1.value    = viewModel.calculaCantidad(Integer.parseInt(lista[6]), 1)
        viewModel.sumaTotal()
    }

    private fun desbloquearUI() {
        binding.apply {

            editText1000.isEnabled = isUiBlocked
            editText500.isEnabled = isUiBlocked
            editText200.isEnabled = isUiBlocked
            editText100.isEnabled = isUiBlocked
            editText50.isEnabled = isUiBlocked
            editText20.isEnabled = isUiBlocked
            editText1.isEnabled = isUiBlocked

        }
    }

    private fun bloquearUI() {
        binding.apply {
            editText1000.isEnabled = isUiBlocked
            editText500.isEnabled = isUiBlocked
            editText200.isEnabled = isUiBlocked
            editText100.isEnabled = isUiBlocked
            editText50.isEnabled = isUiBlocked
            editText20.isEnabled = isUiBlocked
            editText1.isEnabled = isUiBlocked
        }
    }
}

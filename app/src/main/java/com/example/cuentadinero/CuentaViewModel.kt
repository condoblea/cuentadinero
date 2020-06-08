package com.example.cuentadinero

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CuentaViewModel(): ViewModel() {
    var value1000   = MutableLiveData<Int>()
    var value500    = MutableLiveData<Int>()
    var value200    = MutableLiveData<Int>()
    var value100    = MutableLiveData<Int>()
    var value50     = MutableLiveData<Int>()
    var value20     = MutableLiveData<Int>()
    var value1      = MutableLiveData<Int>()
    var result      = MutableLiveData<Int>()

    init {
        value1000.value = 0
        value500.value  = 0
        value200.value  = 0
        value100.value  = 0
        value50.value   = 0
        value20.value   = 0
        value1.value    = 0
        result.value    = 0
    }

    fun calculaCantidad(number: Int, multiplier: Int): Int {
        return number * multiplier
    }

    fun obtenerCadenaNumeros(): String {
        return (value1000.value ?: 0).toString()  + " " +
                (value500.value  ?: 0).toString() + " " +
                (value200.value  ?: 0).toString() + " " +
                (value100.value  ?: 0).toString() + " " +
                (value50.value   ?: 0).toString() + " " +
                (value20.value   ?: 0).toString() + " " +
                (value1.value    ?: 0).toString()
    }

    fun sumaTotal() {

        result.value =
            (value1000.value ?: 0) +
            (value500.value  ?: 0) +
            (value200.value  ?: 0) +
            (value100.value  ?: 0) +
            (value50.value   ?: 0) +
            (value20.value   ?: 0) +
            (value1.value    ?: 0)
        Log.i("CuetaViewModel", "Result value ${result.value}")
    }

}
package com.example.usb

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class AppViewModel: ViewModel (){
    var marca by mutableStateOf("")
    var modelo by mutableStateOf("")
    var nombre by mutableStateOf("")
    var version by mutableStateOf("")
    var vendorId by mutableStateOf(0)
    var productId by mutableStateOf(0)
    var mostrarFuncion by mutableStateOf(false)

    fun updateMostrarFuncion(boolean: Boolean){
        mostrarFuncion=boolean
    }

    fun updateMarca(string: String){
        marca=string
    }

    fun updateNombre(string: String){
        nombre=string
    }

    fun updateModelo(string: String){
        modelo=string
    }

    fun updateVersion(string: String){
        version=string
    }

    fun updateVendorId(int: Int){
        vendorId=int
    }

    fun updateProductId(int: Int){
        productId=int
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                AppViewModel()
            }
        }
    }
}
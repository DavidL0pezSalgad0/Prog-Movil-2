package com.example.usb

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usb.ui.theme.USBTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            USBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewmodel: AppViewModel= viewModel(factory = AppViewModel.Factory)
                    Greeting(viewmodel)
                }
            }
        }
    }
}

@Composable
fun Greeting(
    viewmodel:AppViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row{
            Button(
                onClick = { viewmodel.updateMostrarFuncion(true) }
            ) {
                Text(
                    text = "BUSCAR",
                    textAlign = TextAlign.Center
                )
            }
        }
        Row{
            Text(
                text = "Nombre: " + viewmodel.nombre,
                textAlign = TextAlign.Center
            )
        }
        Row{
            Text(
                text = "Marca: " + viewmodel.marca,
                textAlign = TextAlign.Center
            )
        }
        Row{
            Text(
                text = "Modelo: " + viewmodel.modelo,
                textAlign = TextAlign.Center
            )
        }
        Row{
            Text(
                text = "ID del producto: " + viewmodel.productId,
                textAlign = TextAlign.Center
            )
        }
        Row{
            Text(
                text = "Versión: " + viewmodel.version,
                textAlign = TextAlign.Center
            )
        }
        Row{
            Text(
                text = "ID Vendor: " + viewmodel.vendorId,
                textAlign = TextAlign.Center
            )
        }
    }
    if(viewmodel.mostrarFuncion){
        DetectarMemoriaUSB(viewmodel)
        viewmodel.updateMostrarFuncion(false)
    }
}

@SuppressLint("Range")
@Composable
fun DetectarMemoriaUSB(
    viewmodel:AppViewModel
) {
    val context = LocalContext.current
    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    val dispositivosUSB = usbManager.deviceList

    if(dispositivosUSB.isEmpty()){
        viewmodel.updateNombre("")
        viewmodel.updateMarca("")
        viewmodel.updateModelo("")
        viewmodel.updateProductId(0)
        viewmodel.updateVersion("")
        viewmodel.updateVendorId(0)
    } else {
        dispositivosUSB.values.forEach { dispositivo ->
            val nombre = dispositivo.deviceName
            viewmodel.updateNombre(nombre)

            val marca = dispositivo.manufacturerName ?: "Marca no disponible"
            viewmodel.updateMarca(marca)

            val modelo = dispositivo.productName ?: "Modelo no disponible"
            viewmodel.updateModelo(modelo)

            val productId = dispositivo.productId
            viewmodel.updateProductId(productId)

            val version = dispositivo.version
            viewmodel.updateVersion(version)

            val vendorId = dispositivo.vendorId
            viewmodel.updateVendorId(vendorId)
        }
    }
}
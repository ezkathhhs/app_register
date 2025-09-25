package com.example.registroapp

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registroapp.ui.theme.RegistroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroAppTheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen(){
    // ---------- ESTADOS ----------
    var nombre by remember { mutableStateOf("") } // Estado para el nombre del usuario
    var mail by remember { mutableStateOf("") } // Estado para el correo
    var password by remember { mutableStateOf("") } // Estado para la contraseña
    var cpassword by remember { mutableStateOf("") } // Estado para la confirmacion de contraseña
    var sede by remember { mutableStateOf("") } // Estado para la sede
    var mensaje by remember { mutableStateOf("") } // Resultado del login

    // ---------- LAYOUT PRINCIPAL ----------
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(Color(0xFFBAE3A3)) // Fondo gris claro
            .padding(24.dp), // Margen interno general
        horizontalAlignment = Alignment.CenterHorizontally, // Centrado horizontal
        verticalArrangement = Arrangement.Center // Centrado vertical0
    ) {

        // ---------- LOGO ----------
        Image(
            painter = painterResource(id = R.drawable.gato_alien), // Carga el logo
            contentDescription = "Logo", // Descripción accesible
            modifier = Modifier
                .height(100.dp) // Alto del logo
                .padding(bottom = 32.dp) // Espacio debajo del logo
        )

        // ---------- CAMPO NOMBRE ----------
        TextField(
            value = nombre,
            onValueChange = { nombre = it }, // Actualiza estado
            label = { Text("Nombre Completo") }, // Etiqueta
            singleLine = true // Solo una línea
        )

        Spacer(modifier = Modifier.height(12.dp)) // Espacio entre campos

        // ---------- CAMPO EMAIL ----------
        TextField(
            value = mail,
            onValueChange = { mail = it },
            label = { Text("Correo Electronico") },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- CAMPO CONTRASEÑA ----------
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation() // Oculta caracteres
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- CAMPO CONFIRMACION DE CONTRASEÑA ----------
        TextField(
            value = cpassword,
            onValueChange = { cpassword = it },
            label = { Text("Confirma la Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation() // Oculta caracteres
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- CAMPO SEDE ----------
        TextField(
            value = sede,
            onValueChange = { sede = it },
            label = { Text("Sede") },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- BOTÓN DE LOGIN ----------
        Button(onClick = {
            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cpassword) || TextUtils.isEmpty(sede ) || TextUtils.isEmpty(mail)) {
                mensaje = "Todos los campos son Obligatorios"
            }else if (password != cpassword){
                mensaje = "Las contraseñas no coinciden"
            } else {
                mensaje = "✅ Nombre: $nombre \n ✅ Correo: $mail \n ✅ Contraseña: $cpassword \n ✅ Sede: $sede "
            }

        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color(0XFF000000)
            )
        )

        {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- MENSAJE DE VALIDACIÓN ----------
        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                fontSize = 18.sp,
                color = if (mensaje.contains("✅")) Color(0xFF2E7D32) else Color(0xFFC62828)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegistroAppTheme {
        RegisterScreen()
    }
}
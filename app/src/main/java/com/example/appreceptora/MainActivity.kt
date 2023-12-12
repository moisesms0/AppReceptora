package com.example.appreceptora

import android.app.NotificationChannel
import android.app.NotificationManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textViewMensaje: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewMensaje = findViewById(R.id.mensaje)

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = "Canal de notificacion"
        val descriptionText = "Canal de notificacion de la aplicacion"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("com.example.appreceptora.channel", name, importance).apply {
            description = descriptionText
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

}
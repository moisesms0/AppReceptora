package com.example.appreceptora

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.example.appreceptora.ACTION_MESSAGE") {
            val message = intent.getStringExtra("message")

            showNotification(context, message)
        }
    }

    private fun showNotification(context: Context, message: String?) {
        val builder = NotificationCompat.Builder(context, "com.example.appreceptora.channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Nuevo mensaje")
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent(context, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(Random.nextInt(100000000), builder.build())
        }
    }
}

package com.example.notificationexample.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notificationexample.R
import com.example.notificationexample.ui.Router


class HomeViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is home Fragment"
  }
  val text: LiveData<String> = _text

  fun showNotification(context: Context) {
    val channelId = "123"

    val pendingIntent =
      Router.navControler.createDeepLink().setDestination(R.id.navigation_notifications)
        .createPendingIntent()

    val builder = NotificationCompat.Builder(context, channelId)
      .setSmallIcon(android.R.drawable.ic_dialog_alert)
      .setContentTitle("Notifications Title")
      .setContentText("Your notification content here.")
      .setSubText("Tap to view the website.").setContentIntent(pendingIntent)
      .setAutoCancel(true)

    val notificationManager =
      context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        channelId, "Channel human readable title", NotificationManager.IMPORTANCE_HIGH
      )
      notificationManager.createNotificationChannel(channel)
      builder.setChannelId(channelId)
    }

    notificationManager.notify(0, builder.build())
  }

}
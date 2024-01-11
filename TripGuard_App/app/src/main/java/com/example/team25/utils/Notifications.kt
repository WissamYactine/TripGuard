package com.example.team25.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.team25.R

/**
 * Creates a notification from a given context.
 *
 * @param id: The id for the notification.
 * @param title: The title of the notification.
 * @param body: The content of the notification.
 * @param context: The given context which calls upon the function.
 */
fun createNotification(id: Int, title: String, body: String, context: Context) {
	// An Intent is needed for adding "tap action" functionality to the notification.
	// Here, `MainAcitivy` is opened when the notification is clicked on, but this can be changed.
	val intent = Intent(context, context::class.java).apply {
		flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
	}
	val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

	// ``channelId` as seen below is not really needed, but might be usesful in the future.
	// Currently it is set as an arbitrary string.
	var builder = NotificationCompat.Builder(context, "new notification")
		.setSmallIcon(R.drawable.ic_infologoo)
		.setContentTitle(title)
		.setContentText(body)
		.setVibrate(longArrayOf(1L, 2L, 3L))
		.setPriority(NotificationCompat.PRIORITY_MAX)
		.setContentIntent(pendingIntent)
		.setStyle(NotificationCompat.BigTextStyle()
			.bigText(body))
		.setAutoCancel(true)

	with(NotificationManagerCompat.from(context)) {
		notify(id, builder.build())
	}
}
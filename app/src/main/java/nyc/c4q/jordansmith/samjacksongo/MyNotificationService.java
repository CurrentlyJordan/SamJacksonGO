package nyc.c4q.jordansmith.samjacksongo;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import nyc.c4q.jordansmith.samjacksongo.Model.SamJackson;

/**
 * Created by jordansmith on 12/17/16.
 */

public class MyNotificationService extends IntentService{
    private static final String SERVICE_NAME = "notification-service";


    public MyNotificationService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int NOTIFICATION_ID = 555;

// Define an intent to trigger when notification is selected (in this case to open an activity)
        intent = new Intent(this, MainActivity.class);

// Turn this into a PendingIntent
        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

// Attach the pendingIntent to a new notification using setContentIntent
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_thumb_up_black_24dp)
                .setContentTitle("Oh Snap!")
                .setContentText("You just found a new Sam Jackson!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();

// Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, notification);
        MainActivity.addSamJackson(new SamJackson().randomTransform());


    }
}

package com.example.lecture4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Assignment2 extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);
        Button button = findViewById(R.id.notificationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(v);
            }
        });
    }

    public void sendNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                NOTIFICATION_CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_launcher);

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        builder.setContentIntent(pendingIntent);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher));

        builder.setContentTitle("알려드립니다.");
        builder.setContentText("이것은 시험적인 알림입니다.");
        builder.setSubText("클릭하면 구글의 홈페이지로 이동합니다.");

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            CharSequence charSequence = "노티페케이션 채널";
            String description = "오레오 이상";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    charSequence, importance);
            channel.setDescription(description);

            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        } else {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }

        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

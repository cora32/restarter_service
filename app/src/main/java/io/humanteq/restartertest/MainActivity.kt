package io.humanteq.restartertest

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Settings.Secure.getString(this.contentResolver, "enabled_notification_listeners").contains(
                applicationContext.packageName
            )
        ) {
            //service is enabled do something
        } else {
            //service is not enabled try to enabled by calling...
            startActivity(
                Intent(
                    "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
                )
            )
        }
    }

}
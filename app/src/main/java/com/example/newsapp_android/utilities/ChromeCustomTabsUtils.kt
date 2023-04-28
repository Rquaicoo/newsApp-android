package com.example.newsapp_android.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import android.graphics.Color
import com.example.newsapp_android.R

fun openLink(context: Context, URL: String) {
    val package_name = "com.android.chrome"
    val activity = (context as? Activity)
    val builder = CustomTabsIntent.Builder()
                    .setShowTitle(true)
                    .setInstantAppsEnabled(true)
        .setToolbarColor(Color.BLACK)

    val customBuilder = builder.build()
    if (package_name != null) {
        customBuilder.intent.setPackage(package_name)
        customBuilder.launchUrl(context, Uri.parse(URL))
    } else {
        // Pass URL within intent if chrome is not present
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
        activity?.startActivity(i)
    }

}
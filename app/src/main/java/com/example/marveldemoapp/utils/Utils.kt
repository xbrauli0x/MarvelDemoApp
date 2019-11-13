package com.example.marveldemoapp.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.marveldemoapp.R
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.security.MessageDigest

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

class Utils {
    companion object : KoinComponent {
        private val context: Context by inject()

        fun hasNetwork(): Boolean? {
            var isConnected: Boolean? = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }

        fun getUriImage(path: String?, extension: String?): String? {
            val screenQualifier: String? = getAPIScreenQualifier()
            return "$path/$screenQualifier.$extension"
        }

        private fun getAPIScreenQualifier(): String? {
            val isPortrait: Boolean = checkIfScreenItsOnPortraitMode()
            return if (isPortrait) getPortraitAspectRatio() else getLandscapeAspectRatio()
        }

        private fun checkIfScreenItsOnPortraitMode(): Boolean {
            return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        }

        private fun getPortraitAspectRatio(): String {
            val resources: Resources = context.resources
            return when (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
                Configuration.SCREENLAYOUT_SIZE_XLARGE -> "portrait_incredible"
                Configuration.SCREENLAYOUT_SIZE_LARGE -> "portrait_uncanny"
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> "portrait_fantastic"
                Configuration.SCREENLAYOUT_SIZE_SMALL -> "portrait_xlarge"
                else -> "portrait_fantastic"
            }
        }

        private fun getLandscapeAspectRatio(): String {
            val resources: Resources = context.resources
            return when (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
                Configuration.SCREENLAYOUT_SIZE_XLARGE -> "landscape_incredible"
                Configuration.SCREENLAYOUT_SIZE_LARGE -> "landscape_amazing"
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> "landscape_xlarge"
                Configuration.SCREENLAYOUT_SIZE_SMALL -> "landscape_large"
                else -> "landscape_xlarge"
            }
        }
    }
}
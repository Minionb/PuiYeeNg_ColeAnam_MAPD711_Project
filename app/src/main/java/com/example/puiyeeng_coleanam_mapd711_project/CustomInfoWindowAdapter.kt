package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(private val mContext: Context) : GoogleMap.InfoWindowAdapter {

    private val mWindow: View

    init {
        mWindow = LayoutInflater.from(mContext).inflate(R.layout.store_info_window, null)
    }

    // Info Window
    private fun rendoWindowText(marker: Marker, view: View) {

        // Marker title
        val title = marker.title
        val tvTitle = view.findViewById<TextView>(R.id.title)

        if (title != null && title.isNotEmpty()) {
            tvTitle.text = title
        }

        // Marker snippet
        val snippet = marker.snippet
        val tvSnippet = view.findViewById<TextView>(R.id.snippet)

        if (snippet != null && snippet.isNotEmpty()) {
            tvSnippet.text = snippet
        }

        // Marker image
        var image = R.drawable.fashion_store_logo

        val tvImage = view.findViewById<ImageView>(R.id.storeImage)
        tvImage.setImageResource(image)

    }

    override fun getInfoContents(marker: Marker): View? {
        rendoWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View? {
        rendoWindowText(marker, mWindow)
        return mWindow
    }
}
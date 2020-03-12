package com.example.magiclifecounter.Adapter

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.magiclifecounter.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popup_imagenes.*
import java.util.jar.Manifest
import java.util.logging.Logger

class ImageList : AppCompatActivity() {
 lateinit var img_pick_btn : FloatingActionButton

    var imageIdList = arrayListOf<Int>(
        R.drawable.elves,
        R.drawable.fly,
        R.drawable.goblin,
        R.drawable.merfolk,
        R.drawable.tarmo,
        R.drawable.thunder,
        R.drawable.zombies)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_imagenes)


        val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.layoutManager = sglm

        val imageList = ArrayList<Int>()
        imageList.add(R.drawable.elves)
        imageList.add(R.drawable.fly)
        imageList.add(R.drawable.thunder)
        imageList.add(R.drawable.tarmo)
        imageList.add(R.drawable.zombies)
        imageList.add(R.drawable.goblin)
        imageList.add(R.drawable.merfolk)

        val igka = CustomAdapter(this, imageList)
        rv.adapter = igka
        Log.d("LISTASIZE", "" + imageIdList.size)


    }
}
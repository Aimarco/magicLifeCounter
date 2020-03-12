package com.example.magiclifecounter.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.magiclifecounter.Adapter.ImageList
import com.example.magiclifecounter.R

class MainActivity : AppCompatActivity() {
    private val ACTIVIDAD_PLAYER = 0
    private val ACTIVIDAD_OPONENT = 1
    private val EXTRA_TASK_DESCRYPTION = 1
    lateinit var btnAddLifePlayer:Button
    lateinit var btnRemoveLifePlayer:Button
    lateinit var btnAddLifeOponent:Button
    lateinit var btnRemoveLifeOponent:Button
    lateinit var edtGanadasPlayer:EditText
    lateinit var edtGanadasOponent:EditText
    lateinit var imgBloodPlayer:ImageView
    lateinit var imgBloodOponent:ImageView
    lateinit var txtLifeCounterPlayer:TextView
    lateinit var txtLifeCounterOponent:TextView
    lateinit var oponentView:RelativeLayout
    lateinit var playerView:RelativeLayout
    val playerLifes=20
    val oponentsLifes=20
    val contadorVeneno = 10
    val contadorVidasSt = 20



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        imgBloodPlayer=findViewById(R.id.imgBloodPlayer)
        imgBloodOponent=findViewById(R.id.imgBloodOp)
        oponentView = findViewById(R.id.opponentView)
        playerView = findViewById(R.id.playerView)
        txtLifeCounterPlayer = findViewById(R.id.lifeCounterPl)
        txtLifeCounterOponent = findViewById(R.id.lifeCounterOp)
        btnAddLifePlayer = findViewById(R.id.btnAddLifesPlayer)
        btnAddLifeOponent = findViewById(R.id.btnAddLifesOp)
        btnRemoveLifePlayer = findViewById(R.id.btnRemoveLifesPl)
        btnRemoveLifeOponent = findViewById(R.id.btnRemoveLifesOp)
        oponentView.setOnLongClickListener {
          var  intent: Intent = Intent(this, ImageList::class.java)
            intent.putExtra("selectedView", "oponent")
            startActivityForResult(intent, ACTIVIDAD_OPONENT)
            return@setOnLongClickListener true
        }

        playerView.setOnLongClickListener {
            val  intent: Intent = Intent(this, ImageList::class.java)
            intent.putExtra("selectedView", "player")
            startActivityForResult(intent, ACTIVIDAD_PLAYER)
            return@setOnLongClickListener true

        }

        imgBloodPlayer.setOnClickListener{swapBloodImagePlayer()}
        imgBloodOponent.setOnClickListener{swapBloodImageOponent()}

        btnAddLifePlayer.setOnClickListener {addLife(btnAddLifePlayer.id.toString()) }
        btnAddLifeOponent.setOnClickListener { addLife(btnAddLifeOponent.id.toString()) }
        btnRemoveLifePlayer.setOnClickListener { removeLife(btnAddLifePlayer.id.toString()) }
        btnRemoveLifeOponent.setOnClickListener { removeLife(btnAddLifeOponent.id.toString()) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        val returnString :Int = data!!.getIntExtra("resource",0)
            if (requestCode == ACTIVIDAD_OPONENT)
                oponentView.background = resources.getDrawable(returnString)
            else if (requestCode == ACTIVIDAD_PLAYER)
                playerView.background = resources.getDrawable(returnString)
    }



    public fun swapBloodImagePlayer(){

        if(imgBloodPlayer.drawable.constantState == resources.getDrawable(R.drawable.blood).constantState) {
            imgBloodPlayer.setImageResource(R.drawable.venom)
            txtLifeCounterPlayer.text = contadorVeneno.toString()
        }else {
            imgBloodPlayer.setImageResource(R.drawable.blood)
            txtLifeCounterPlayer.text = contadorVidasSt.toString()
        }
    }
    public fun swapBloodImageOponent() {
        if (imgBloodOponent.drawable.constantState == resources.getDrawable(R.drawable.blood).constantState) {
            imgBloodOponent.setImageResource(R.drawable.venom)
            txtLifeCounterOponent.text = contadorVeneno.toString()
        } else {
            imgBloodOponent.setImageResource(R.drawable.blood)
            txtLifeCounterOponent.text = contadorVidasSt.toString()
        }
    }

    public fun addLife(id:String){
        if(id.equals(btnAddLifePlayer.id.toString())) {
            var vidas = txtLifeCounterPlayer.text.toString().toInt()
            vidas += 1
            txtLifeCounterPlayer.text = vidas.toString()
        }else{
            var vidas = txtLifeCounterOponent.text.toString().toInt()
            vidas += 1
            txtLifeCounterOponent.text = vidas.toString()
        }

    }

    public fun removeLife(id:String){
        if(id.equals(btnAddLifePlayer.id.toString())) {
            var vidas = txtLifeCounterPlayer.text.toString().toInt()
            vidas -= 1
            txtLifeCounterPlayer.text = vidas.toString()
        }else{
            var vidas = txtLifeCounterOponent.text.toString().toInt()
            vidas -= 1
            txtLifeCounterOponent.text = vidas.toString()
        }
    }




}

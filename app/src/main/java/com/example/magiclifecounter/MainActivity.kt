package com.example.magiclifecounter

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

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
          val  intent: Intent = Intent(this, changeImageActivity::class.java)
            startActivity(intent)
            return@setOnLongClickListener true
        }

        imgBloodPlayer.setOnClickListener{swapBloodImagePlayer()}
        imgBloodOponent.setOnClickListener{swapBloodImageOponent()}

        btnAddLifePlayer.setOnClickListener {addLife(btnAddLifePlayer.id.toString()) }
        btnAddLifeOponent.setOnClickListener { addLife(btnAddLifeOponent.id.toString()) }
        btnRemoveLifePlayer.setOnClickListener { removeLife(btnAddLifePlayer.id.toString()) }
        btnRemoveLifeOponent.setOnClickListener { removeLife(btnAddLifeOponent.id.toString()) }
    }

    public fun swapBloodImagePlayer(){
        Toast.makeText(this,"pulsada imagen", Toast.LENGTH_SHORT).show()

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



    public fun onButtonShowPopupWindowClick(view:View) {


        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // Inflate a custom view using layout inflater
        val view = inflater.inflate(R.layout.popup_imagenes,null)

        // Initialize a new instance of popup window
        val popupWindow = PopupWindow(
            view, // Custom view to show in popup window
            LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
            LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )

        // Set an elevation for the popup window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F
        }


        // If API level 23 or higher then execute the code
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Create a new slide animation for popup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for popup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.RIGHT
            popupWindow.exitTransition = slideOut

        }

        // Get the widgets reference from custom view
       // val tv = view.findViewById<TextView>(R.id.text_view)
        //val buttonPopup = view.findViewById<Button>(R.id.button_popup)

        // Set click listener for popup window's text view
        /*tv.setOnClickListener{
            // Change the text color of popup window's text view
            tv.setTextColor(Color.RED)
        }*/

        // Set a click listener for popup's button widget
        /*buttonPopup.setOnClickListener{
            // Dismiss the popup window
            popupWindow.dismiss()
        }*/

        // Set a dismiss listener for popup window
        popupWindow.setOnDismissListener {
            Toast.makeText(applicationContext,"Popup closed",Toast.LENGTH_SHORT).show()
        }


        // Finally, show the popup window on app
        TransitionManager.beginDelayedTransition(oponentView)
        popupWindow.showAtLocation(
            oponentView, // Location to display popup window
            Gravity.CENTER, // Exact position of layout to display popup
            0, // X offset
            0 // Y offset
        )


    }



}

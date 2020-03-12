package com.example.magiclifecounter.Adapter
import android.app.Activity
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.magiclifecounter.Activity.MainActivity
import com.example.magiclifecounter.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_view.view.*

private val EXTRA_TASK_DESCRYPTION = 1

class CustomAdapter(private val c: Context, private val images: ArrayList<Int>) :
    RecyclerView.Adapter<CustomAdapter.ColorViewHolder>() {



    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(LayoutInflater.from(c).inflate(R.layout.image_view, parent, false))
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val path = images[position]
        Log.d("PATH",""+path)
        Picasso.get()
            .load(path)
            .resize(550, 450)
            .centerCrop()
            .into(holder.iv)

        holder.iv.setOnClickListener {
            val intent = Intent()
            intent.putExtra("resource", path)
            (c as ImageList).setResult(Activity.RESULT_OK,intent)

            c.finish()
        }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv = view.image_item as ImageView
    }

}

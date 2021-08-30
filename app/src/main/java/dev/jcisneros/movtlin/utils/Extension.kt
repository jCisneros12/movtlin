package dev.jcisneros.movtlin.utils

import android.app.Activity
import android.widget.Toast

//toast
fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, length).show()
}
package com.example.musicplayer.utils

import java.text.FieldPosition

interface ItemTouchHelperA {
    fun onItemMove(fromItem:Int, toItem:Int)
    fun onItemDismiss(position:Int)
}
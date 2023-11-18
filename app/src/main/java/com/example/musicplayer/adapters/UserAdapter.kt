package com.example.musicplayer.adapters

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.musicplayer.R
import com.example.musicplayer.databinding.ItemRvBinding
import com.example.musicplayer.models.User
import com.example.musicplayer.utils.ItemTouchHelperA
import java.util.Collections

class UserAdapter(val list: ArrayList<User>, var rvAction: RvAction): Adapter<UserAdapter.Vh>(), ItemTouchHelperA {
    inner class Vh(var itemRv:ItemRvBinding):ViewHolder(itemRv.root){
        fun onBind(user: User, position: Int){
            itemRv.tvName.text = user.name
            itemRv.tvAuthors.text = user.age.toString()
            itemRv.root.setOnClickListener {
                rvAction.itemClick(position, user)
            }
            itemRv.root.setOnLongClickListener {
                rvAction.itemLongClick(position, user)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

    override fun onItemMove(fromItem: Int, toItem: Int) {
        if (fromItem<toItem){
            for (i in fromItem until toItem){
                Collections.swap(list, i, i+1)

            }
        }else{
            for (i in fromItem downTo  toItem){
                Collections.swap(list, i, i  )
            }
        }
        notifyItemMoved(fromItem, toItem)

    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)

    }
    interface RvAction{
        fun itemClick(position: Int, user: User)
        fun itemLongClick(position: Int, user: User)
    }
}
package com.example.musicplayer

import android.content.ClipData.Item
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.adapters.UserAdapter
import com.example.musicplayer.databinding.ActivityMainBinding
import com.example.musicplayer.models.User

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<User>
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()
        userAdapter = UserAdapter(list, object : UserAdapter.RvAction {
            override fun itemClick(position: Int, user: User) {
                user.media.start()

            }


            override fun itemLongClick(position: Int, user: User) {
                user.media.pause()
            }
        })
        binding.rv.adapter = userAdapter

        val itemTouch = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragflags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragflags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(itemTouch)
            .attachToRecyclerView(binding.rv)

    }

    private fun loadData() {
        list = ArrayList()
        for (i in 0 until 100) {
            list.add(User("Janob Rasul $i", i, MediaPlayer.create(this, R.raw.remix)))
            binding.rv

        }

    }
}

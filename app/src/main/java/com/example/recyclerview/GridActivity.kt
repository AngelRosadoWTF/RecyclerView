package com.example.recyclerview

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        val items = getItems()
        recyclerView.adapter = Adapter(items)
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun getItems(): List<Item> {
        return listOf(
            Item("París, Francia", R.drawable.img1),
            Item("Madrid, España", R.drawable.img2),
            Item("Berlín, Alemania", R.drawable.img3),
            Item("Londres, Inglaterra", R.drawable.img4),
            Item("Roma, Italia", R.drawable.img5),
            Item("Copenhague, Dinamarca", R.drawable.img6),
            Item("Lisboa, Portugal", R.drawable.img7),
            Item("Dublín, Irlanda", R.drawable.img8),
            Item("Oslo, Noruega", R.drawable.img9),
            Item("Atenas, Grecia", R.drawable.img10),
            Item("Budapest, Hungría", R.drawable.img11),
            Item("Berna, Suiza", R.drawable.img12)
        )
    }
}
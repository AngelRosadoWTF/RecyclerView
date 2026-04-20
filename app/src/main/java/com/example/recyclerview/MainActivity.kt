package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnGrid = findViewById<Button>(R.id.btnGrid)
        val btnStaggered = findViewById<Button>(R.id.btnStaggered)
        btnGrid.setOnClickListener {
            startActivity(Intent(this, GridActivity::class.java))
        }
        btnStaggered.setOnClickListener {
            startActivity(Intent(this, StaggeredActivity::class.java))
        }
    }
}
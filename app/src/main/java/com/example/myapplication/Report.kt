package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.activities.Base
import com.example.myapplication.activities.donations
import com.example.myapplication.contacts.Contacts
import com.example.myapplication.model.Donation
import com.example.myapplication.view.adapter.AdapterData
import com.google.gson.Gson
import java.io.Serializable

class Report : Base() {
    private var recyclerView: RecyclerView?= null
    private var adapter: AdapterData?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        initObserver()
        Log.d("khanh", "da di qua cho nay")
    }

    private fun initObserver() {
        recyclerView = findViewById(R.id.reportView)
        adapter = AdapterData(donations)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
        adapter?.setData(donations)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuDonate -> {
                startActivity(Intent(this, Donate::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
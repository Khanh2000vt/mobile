package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Donation
import com.example.myapplication.view.adapter.AdapterData


class Report : AppCompatActivity() {
    private var recyclerView: RecyclerView?= null
    private var adapter: AdapterData?= null

    private val numbers = listOf<Donation>(
        Donation(1000, "PayPal"),
        Donation(200, "Momo")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        initObserver()
    }

    private fun initObserver() {
        recyclerView = findViewById(R.id.reportView)
        adapter = AdapterData(numbers)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter
        adapter?.setData(numbers)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuDonate -> handleMenuDonate()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleMenuDonate() {
        finish()
    }
}
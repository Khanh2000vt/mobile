package com.example.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Donate
import com.example.myapplication.R
import com.example.myapplication.Report
import com.example.myapplication.model.Donation
import com.example.myapplication.presenter.DonatePresenter
import com.example.myapplication.presenter.IDonatePresenter

    var totalDonated = 0
    var donations : MutableList<Donation> = mutableListOf()

open class Base: AppCompatActivity(), IDonatePresenter {
    var target = 10000
    private var mDonatePresenter: DonatePresenter = DonatePresenter(this)

    fun newDonation(donation: Donation): Boolean {
        val targetAchieved: Boolean = totalDonated > target
        if (!targetAchieved) {
//            totalDonated += donation.amount
            mDonatePresenter.dataDonate(donation, totalDonated)
        }
        else {
            Toast.makeText(this, "Target Exceeded", Toast.LENGTH_SHORT).show()
        }
        return targetAchieved
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        val report: MenuItem = menu!!.findItem(R.id.menuReport)
        val donate: MenuItem = menu.findItem(R.id.menuDonate)

        if (donations.isEmpty())
            report.setEnabled(false)
        else
            report.setEnabled(true)

        if (this is Donate) {
            donate.setVisible(false)
            if (donations.isNotEmpty())
                report.isVisible = true
        }
        else {
            report.setVisible(false)
            donate.setVisible(true)
        }
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun donateSuccess(data: Donation, amount: Int) {
        donations.add(data)
        totalDonated = amount
    }

    override fun donateError() {
        Toast
            .makeText(this, "Target Error 222!",
                Toast.LENGTH_SHORT)
            .show()
    }

    fun setting(item: MenuItem) {
        Toast.makeText(this, "Setting Selected", Toast.LENGTH_SHORT).show()
    }

    fun report(item: MenuItem) {
        startActivity(Intent(this, Report::class.java))
    }

    fun donate (item: MenuItem) {
        startActivity(Intent(this, Donate::class.java))
    }

    fun reset(item: MenuItem) {
        totalDonated = 0
        donations.clear()
        startActivity(Intent(this, Donate::class.java))
    }
}
package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.activities.Base
import com.example.myapplication.activities.totalDonated
import com.example.myapplication.model.Donation
import com.example.myapplication.contacts.Contacts
import com.example.myapplication.presenter.DonatePresenter
import com.example.myapplication.presenter.IDonatePresenter
import com.google.gson.Gson


class Donate : Base(), View.OnClickListener {
    private var button: Button?= null
    private var paymentMethod: RadioGroup?= null
    private var progressBar: ProgressBar?= null
    private var amountPicker: NumberPicker?= null
    private var fab: FloatingActionButton ?= null
    private var amountTotal: TextView ?= null
    private var amountText: EditText?= null
    private var mDonatePresenter: DonatePresenter?= null

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        mDonatePresenter = DonatePresenter(this)
        button = findViewById(R.id.donateButton)
        paymentMethod = findViewById(R.id.radioGroup)
        progressBar = findViewById(R.id.progressBar)
        amountPicker = findViewById(R.id.amountPicker)
        fab = findViewById(R.id.fab)
        amountTotal  = findViewById(R.id.totalSoFar)
        amountText = findViewById(R.id.paymentAmount)

        amountPicker?.minValue = Contacts.DONATE_MIN
        amountPicker?.maxValue = Contacts.NUMBER_TARGET
        progressBar?.max = Contacts.TOTAL_MAX

        amountTotal?.text = totalDonated.toString()
        progressBar!!.progress = totalDonated

        button?.setOnClickListener(this)
        fab?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.donateButton -> {
//                handleDonateButton()
                donateButtonPressed(v)
            }
            R.id.fab -> {
//                handleFab(v)
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

        }
    }

//    private fun handleFab(view: View) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show()
//    }

//    @SuppressLint("SetTextI18n")
//    private fun handleDonateButton() {
//        targetAchieved = totalDonated >= Contacts.TOTAL_MAX
//        var donatedAmount: Int = amountPicker!!.value
//        val method: String = when(paymentMethod!!
//            .checkedRadioButtonId == R.id.payPal) {
//            true -> "PayPal"
//            else -> "Direct"
//        }
//
//        if (donatedAmount == 0) {
//            val text: String = amountText!!.text.toString()
//            if (text != "" ) {
//                donatedAmount = text.toInt()
//            }
//        }
//        if (!targetAchieved) {
//            val donation = Donation(donatedAmount, method)
//            mDonatePresenter?.dataDonate(donation, totalDonated)
//            Toast
//                .makeText(this,
//                    "Donate Pressed! with amount $donatedAmount, method: $method",
//                    Toast.LENGTH_SHORT)
//                .show()
//        }
//        else {
//            Toast
//                .makeText(this, "Target Error!",
//                    Toast.LENGTH_SHORT)
//                .show()
//        }
//
//        // set value return 0
//        amountPicker!!.value = 0
//        amountText?.setText("")
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_donate, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when (item.itemId) {
//           R.id.menuReport -> handleMenuReport()
           R.id.menuReport -> {
               startActivity(Intent(this, Report::class.java))
           }
       }
        return super.onOptionsItemSelected(item)
    }

//    private fun handleMenuReport() {
//        val intent = Intent(this, Report::class.java)
//        startActivity(intent)
//    }

//    @SuppressLint("SetTextI18n")
//    override fun donateSuccess(data: Donation, amount: Int) {
//        listView.add(data)
//        totalDonated = amount
//        progressBar!!.progress = totalDonated
//        amountTotal!!.text = "$$amount"
//    }
//
//    override fun donateError() {
//        Toast
//            .makeText(this, "Target Error 222!",
//                Toast.LENGTH_SHORT)
//            .show()
//    }

//    fun handleDonateButton(item: android.view.MenuItem) {}
    private fun donateButtonPressed(view: View) {
        val method: String = when(paymentMethod!!
            .checkedRadioButtonId == R.id.payPal) {
            true -> "PayPal"
            else -> "Direct"
        }
        var donatedAmount: Int = amountPicker!!.value
        if (donatedAmount == 0) {
            val text: String = amountText!!.text.toString()
            if (!text.equals("")) {
                donatedAmount = Integer.parseInt(text)
            }
        }

        if (donatedAmount > 0) {
            newDonation(Donation(donatedAmount, method))
            progressBar!!.progress = totalDonated
            val totalDonatedStr: String = "$$totalDonated"
            amountTotal!!.text = totalDonatedStr
        }
    }
}
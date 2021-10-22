package com.example.myapplication.presenter

import com.example.myapplication.model.Donation
import com.example.myapplication.contacts.Contacts


class DonatePresenter(private var mIDonatePresenter: IDonatePresenter) {

    fun dataDonate(donation: Donation, totalDonated: Int) {
        var total = 0
        if (donation.amount != 0 && donation.amount <= Contacts.TOTAL_MAX - totalDonated) {
            total = totalDonated + donation.amount
            mIDonatePresenter?.donateSuccess(donation, total)
        }
        else {
            mIDonatePresenter?.donateError()
        }
    }
}
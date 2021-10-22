package com.example.myapplication.presenter

import com.example.myapplication.model.Donation

interface IDonatePresenter {
    fun donateSuccess(data: Donation, amount: Int)
    fun donateError()
}
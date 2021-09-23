package com.celik.gokhun.obilet.thisticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.celik.gokhun.obilet.thisticket.model.BusJourneys
import com.celik.gokhun.obilet.thisticket.service.ObiletAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BusJourneysData : ViewModel(){

    private val obiletAPIService = ObiletAPIService()

    private val busJourneysDisposable = CompositeDisposable()

    val busJourneys = MutableLiveData<BusJourneys>()
    val busJourneysError = MutableLiveData<Boolean>()
    val busJourneysLoading = MutableLiveData<Boolean>()

    fun refreshBusJourneysData(sessionId: String, deviceId: String, originId: Int, destinationId: Int, departureDate: String){
        getBusJourneysDataApi(sessionId,deviceId,originId,destinationId,departureDate)
    }

    private fun getBusJourneysDataApi(sessionId: String, deviceId: String, originId: Int, destinationId: Int, departureDate: String) {
        busJourneysLoading.value = true

        busJourneysDisposable.add(
            obiletAPIService.getBusJourneys(sessionId,deviceId,originId,destinationId,departureDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BusJourneys>()
                {
                    override fun onSuccess(t: BusJourneys)
                    {
                        busJourneys.value = t
                        busJourneysError.value = false
                        busJourneysLoading.value = false

                    }
                    override fun onError(e: Throwable)
                    {
                        println("olmadi amk   :  "+ e.localizedMessage )
                        busJourneysLoading.value = false
                        busJourneysError.value = true
                    }
                }
                )
        )

    }

}
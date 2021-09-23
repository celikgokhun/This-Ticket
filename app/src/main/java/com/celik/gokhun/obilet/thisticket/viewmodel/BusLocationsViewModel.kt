package com.celik.gokhun.obilet.thisticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.celik.gokhun.obilet.thisticket.model.BusLocations
import com.celik.gokhun.obilet.thisticket.service.ObiletAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BusLocationsViewModel : ViewModel() {

    private val obiletAPIService = ObiletAPIService()
    private val busLocationsDisposable = CompositeDisposable()

    val busLocations = MutableLiveData<BusLocations>()
    val busLocationsError = MutableLiveData<Boolean>()
    val busLocationsLoading = MutableLiveData<Boolean>()

    fun refreshSessionData(sessionId: String, deviceId: String){
        getBusLocationsDataAPI(sessionId, deviceId)
    }

    private fun getBusLocationsDataAPI(sessionId: String, deviceId: String){
        busLocationsLoading.value =true

        busLocationsDisposable.add(
            obiletAPIService.getBusLocations(sessionId,deviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BusLocations>()
                {
                    override fun onSuccess(t: BusLocations)
                    {
                        busLocations.value = t
                        busLocationsError.value = false
                        busLocationsLoading.value = false
                        //observeBusLocations()


                    }
                    override fun onError(e: Throwable)
                    {
                        println("olmadi amk   :  "+ e.localizedMessage )
                        busLocationsLoading.value = false
                        busLocationsError.value = true
                    }
                }
                )
        )
    }

}
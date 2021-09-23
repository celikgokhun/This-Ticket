package com.celik.gokhun.obilet.thisticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData

import com.celik.gokhun.obilet.thisticket.R
import com.celik.gokhun.obilet.thisticket.model.BusJourneys
import com.celik.gokhun.obilet.thisticket.model.BusLocations
import com.celik.gokhun.obilet.thisticket.model.BusLocationsData
import com.celik.gokhun.obilet.thisticket.model.Session
import com.celik.gokhun.obilet.thisticket.service.ObiletAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val obiletAPIService = ObiletAPIService()

    private val disposable = CompositeDisposable()

    val session = MutableLiveData<Session>()
    val sessionError = MutableLiveData<Boolean>()
    val sessionLoading = MutableLiveData<Boolean>()

    private val busLocationsDisposable = CompositeDisposable()

    val busLocations = MutableLiveData<BusLocations>()
    val busLocationsError = MutableLiveData<Boolean>()
    val busLocationsLoading = MutableLiveData<Boolean>()

    private val busJourneysDisposable = CompositeDisposable()

    val busJourneys = MutableLiveData<BusJourneys>()
    val busJourneysError = MutableLiveData<Boolean>()
    val busJourneysLoading = MutableLiveData<Boolean>()



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
                        observeBusJourneys()


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

    private fun observeBusJourneys(){
        println("Bus Journeys STATUS  :   "+busJourneys.value?.status)
        println("All  :   "+busJourneys.value?.data?.get(0))
        //println("ALL  :   "+busLocations.value)
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
                        observeBusLocations()


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

    private fun refreshBusLocationsData(sessionId: String, deviceId: String){
        getBusLocationsDataAPI(sessionId,deviceId)
    }

    private fun refreshBusJourneysData(sessionId: String, deviceId: String, originId: Int, destinationId: Int, departureDate: String){
        getBusJourneysDataApi(sessionId,deviceId,originId,destinationId,departureDate)
    }

    private fun observeSessionData(){
        println("Session STATUS  :   "+session.value?.sessionStatus)
        //println("Device  Id  :   "+session.value?.sessionData?.sessionDataDeviceId)
        //println("Session Id  :   "+session.value?.sessionData?.sessionDataSessionId)

        val sessionId = session.value?.sessionData?.sessionDataSessionId
        val deviceId = session.value?.sessionData?.sessionDataDeviceId

        if (sessionId != null && deviceId != null) {
            refreshBusLocationsData(sessionId,deviceId)
            refreshBusJourneysData(sessionId,deviceId,349,356,"2021-10-01")
        }
        else
        {
            println("tam sıçtık kanka")
        }

    }

    private fun refreshSessionData(){
        getSessionDataAPI()
    }

    private fun getSessionDataAPI(){
        sessionLoading.value =true

        disposable.add(
            obiletAPIService.getSession()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Session>()
                {
                    override fun onSuccess(t: Session) {
                        session.value = t
                        sessionError.value = false
                        sessionLoading.value = false
                        observeSessionData()

                    }

                    override fun onError(e: Throwable) {
                        println("olmadi amk   :  "+ e.localizedMessage )
                        sessionLoading.value = false
                        sessionError.value = true
                    }
                })
        )

    }

    private fun observeBusLocations() {
        println("Bus Locations STATUS  :   "+busLocations.value?.status)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshSessionData()
    }

}
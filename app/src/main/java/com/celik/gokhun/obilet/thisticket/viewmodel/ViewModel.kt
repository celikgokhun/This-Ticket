package com.celik.gokhun.obilet.thisticket.viewmodel

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.celik.gokhun.obilet.thisticket.R
import com.celik.gokhun.obilet.thisticket.model.BusJourneys
import com.celik.gokhun.obilet.thisticket.model.BusLocations
import com.celik.gokhun.obilet.thisticket.model.Session
import com.celik.gokhun.obilet.thisticket.service.ObiletAPIService
import com.celik.gokhun.obilet.thisticket.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ViewModel : ViewModel() {

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

    var idleArray = arrayOf<Int?>()
    lateinit var idleSessionId : String
    lateinit var idleDeviceId: String

    private fun observeSessionData(){
        println("Session STATUS  :   "+session.value?.sessionStatus)
        //println("Device  Id  :   "+session.value?.sessionData?.sessionDataDeviceId)
        //println("Session Id  :   "+session.value?.sessionData?.sessionDataSessionId)

        val sessionId = session.value?.sessionData?.sessionDataSessionId
        val deviceId = session.value?.sessionData?.sessionDataDeviceId

        if (sessionId != null && deviceId != null) {

            idleSessionId = sessionId
            idleDeviceId = deviceId

            refreshBusLocationsData(sessionId,deviceId)
            //refreshBusJourneysData(sessionId,deviceId,349,356,"2021-10-01")
        }
        else
        {
            println("Fatal Error")
        }

    }

    fun refreshSessionData(){
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
                        println("Error   :  "+ e.localizedMessage )
                        sessionLoading.value = false
                        sessionError.value = true
                    }
                })
        )

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
                        observeBusJourneys()


                    }
                    override fun onError(e: Throwable)
                    {
                        println("Error   :  "+ e.localizedMessage )
                        busJourneysLoading.value = false
                        busJourneysError.value = true
                    }
                }
                )
        )

    }

    private fun observeBusJourneys(){
        println("Bus Journeys STATUS  :   "+busJourneys.value?.status)
        //println("All  :   "+busJourneys.value?.data?.get(0))
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
                        println("Error   :  "+ e.localizedMessage )
                        busLocationsLoading.value = false
                        busLocationsError.value = true
                    }
                }
                )
        )
    }

    fun refreshBusJourneysData(sessionId: String, deviceId: String, originId: Int, destinationId: Int, departureDate: String){
        getBusJourneysDataApi(sessionId,deviceId,originId,destinationId,departureDate)
    }

    private fun observeBusLocations() {
        println("Bus Locations STATUS  :   "+busLocations.value?.status)

        val locationSize = busLocations.value?.data?.size.toString().toInt()


        val locationsName = arrayOfNulls<String?>(locationSize)
        val locationsId = arrayOfNulls<Int?>(locationSize)

        for (i in 0..locationSize-1){ ////////////////////// lan keko
            //locationsNameId[i] = busLocations.value?.data?.get(i)?.name.toString() +"%"+ busLocations.value?.data?.get(i)?.id.toString()
            locationsName[i] = busLocations.value?.data?.get(i)?.name.toString()
            locationsId[i] = busLocations.value?.data?.get(i)?.id.toString().toInt()

        }

        idleArray = locationsId

        //fillSpinners()
    }

    private fun refreshBusLocationsData(sessionId: String, deviceId: String){
        getBusLocationsDataAPI(sessionId,deviceId)
    }









}
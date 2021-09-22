package com.celik.gokhun.obilet.thisticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData

import com.celik.gokhun.obilet.thisticket.R
import com.celik.gokhun.obilet.thisticket.model.BusLocations
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshSessionData()

    }

    private fun refreshBusLocationsData(sessionId: String, deviceId: String){
        getBusLocationsDataAPI(sessionId,deviceId)
    }

    private fun getBusLocationsDataAPI(sessionId: String, deviceId: String){
        busLocationsLoading.value =true


        busLocationsDisposable.add(
            obiletAPIService.getBusLocations(sessionId,deviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<BusLocations>()
                {
                    override fun onSuccess(t: BusLocations) {
                        busLocations.value = t
                        busLocationsError.value = false
                        busLocationsLoading.value = false
                        observeBusLocationsData()


                    }

                    override fun onError(e: Throwable) {
                        println("olmadi amk   :  "+ e.localizedMessage )
                        busLocationsLoading.value = false
                        busLocationsError.value = true
                    }
                })
        )
    }

    private fun observeBusLocationsData() {
        println("Bus Locations STATUS  :   "+busLocations.value?.status)
        println("ALL  :   "+busLocations.value)
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

    private fun observeSessionData(){
        println("Session STATUS  :   "+session.value?.sessionStatus)
        //println("Device  Id  :   "+session.value?.sessionData?.sessionDataDeviceId)
        //println("Session Id  :   "+session.value?.sessionData?.sessionDataSessionId)

        val sessionId = session.value?.sessionData?.sessionDataDeviceId
        val deviceId = session.value?.sessionData?.sessionDataSessionId

        if (sessionId != null && deviceId != null) {
            refreshBusLocationsData(sessionId,deviceId)
        }
        else
        {
            println("tam sıçtık kanka")
        }

    }

}
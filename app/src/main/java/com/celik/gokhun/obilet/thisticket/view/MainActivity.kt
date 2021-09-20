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


private val obiletAPIService = ObiletAPIService()

private val disposable = CompositeDisposable()
private val disposableBusLocations = CompositeDisposable()

val session = MutableLiveData<Session>()
val sessionError = MutableLiveData<Boolean>()
val sessionLoading = MutableLiveData<Boolean>()

val busLocations = MutableLiveData<List<BusLocations>>()
val busLocationsError = MutableLiveData<Boolean>()
val busLocationsLoading = MutableLiveData<Boolean>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeSession()

        /*
        disposableBusLocations.add(

            obiletAPIService.getBusLocations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<BusLocations>>(){
                    override fun onSuccess(t: List<BusLocations>) {
                        busLocations.value = t
                        busLocationsError.value = false
                        busLocationsLoading.value = false

                        println("başardık kanka bus")
                    }

                    override fun onError(e: Throwable) {
                        busLocationsLoading.value = false
                        busLocationsError.value = true
                        println("yarra yedik kanka   "+e.localizedMessage)
                    }

                })

        )

         */

    }



    private fun initializeSession(){

        sessionLoading.value =true

        obiletAPIService.getSession()


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

                        println("halloldu")
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
        println("STATUS:  "+session.value?.sessionStatus)
        //println("MESSAGES:   "+session.value?.message)
        println("MESSAGES:   "+session.value)
        //println(session.value?.sessionData?.sessionId)
        //println(session.value?.sessionData?.deviceId)

    }


}
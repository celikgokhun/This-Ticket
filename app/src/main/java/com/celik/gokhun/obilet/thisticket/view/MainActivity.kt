package com.celik.gokhun.obilet.thisticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.celik.gokhun.obilet.thisticket.R
import com.celik.gokhun.obilet.thisticket.model.Session
import com.celik.gokhun.obilet.thisticket.service.ObiletAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private val obiletAPIService = ObiletAPIService()
private val disposable = CompositeDisposable()

val session = MutableLiveData<Session>()
val sessionError = MutableLiveData<Boolean>()
val sessionLoading = MutableLiveData<Boolean>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        disposable.add(
            obiletAPIService.getSession()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Session>(){
                    override fun onSuccess(t: Session) {
                        session.value = t
                        sessionError.value = false
                        sessionLoading.value = false
                        println("başardık kanka")
                    }

                    override fun onError(e: Throwable) {
                        sessionLoading.value = false
                        sessionError.value = true
                        println("yarra yedik kanka   "+e.localizedMessage)
                    }

                })
        )
        observeLiveData()

    }




    private fun observeLiveData(){

    }


}
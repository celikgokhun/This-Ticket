package com.celik.gokhun.obilet.thisticket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import com.celik.gokhun.obilet.thisticket.R
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.celik.gokhun.obilet.thisticket.util.getCurrentDateWithFineFormat
import com.celik.gokhun.obilet.thisticket.viewmodel.ViewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel : ViewModel

    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner

    private lateinit var dateTextView: TextView

    private lateinit var  swipeRefresh : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        dateTextView = findViewById(R.id.dateTextView)

        dateTextView.text = getCurrentDateWithFineFormat()

        viewModel.refreshSessionData()

        swipeRefresh = findViewById(R.id.swipeToRefresh)


        swipeRefresh.setOnRefreshListener{
            fillSpinners()
        }

        Handler().postDelayed({
            fillSpinners()},
            2000)

    }


    private fun fillSpinners(){

        if (viewModel.busLocations.value?.status.equals("Success"))
        {
            if (swipeRefresh.isRefreshing)
            {
                swipeRefresh.isRefreshing = false
            }


            val locationSize = viewModel.busLocations.value?.data?.size.toString().toInt()


            val locationsName = arrayOfNulls<String?>(locationSize)
            val locationsId = arrayOfNulls<Int?>(locationSize)

            for (i in 0..locationSize-1){ ////////////////////// lan keko
                //locationsNameId[i] = busLocations.value?.data?.get(i)?.name.toString() +"%"+ busLocations.value?.data?.get(i)?.id.toString()
                locationsName[i] = viewModel.busLocations.value?.data?.get(i)?.name.toString()
                locationsId[i] = viewModel.busLocations.value?.data?.get(i)?.id.toString().toInt()

            }

            viewModel.idleArray = locationsId

            fromSpinner = findViewById(R.id.fromSpinner)
            this.also { it.also { fromSpinner.onItemSelectedListener = it } }

            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fromSpinner.adapter = aa

            toSpinner = findViewById(R.id.toSpinner)
            this.also { it.also { toSpinner.onItemSelectedListener = it } }

            val bb = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            toSpinner.adapter = bb
        }
        else{
            Toast.makeText(this,"Failed session",Toast.LENGTH_LONG).show()
        }



    }

    fun findTicket(view: android.view.View) {

        /*

        val originId =  idleArray[fromSpinner.selectedItemPosition]
        val destinationId =  idleArray[toSpinner.selectedItemPosition]
        originId?.let {
            destinationId?.let { it1 ->

                refreshBusJourneysData(idleSessionId, idleDeviceId, it, it1, getCurrentDate() )

            }
        }

         */


        val intent = Intent(this, JourneyActivity::class.java)
        //intent.putExtra("key", value)
        startActivity(intent)

    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }



}
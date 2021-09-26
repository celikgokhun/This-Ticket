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
import com.celik.gokhun.obilet.thisticket.util.getCurrentDate
import com.celik.gokhun.obilet.thisticket.util.getCurrentDateTomorrow
import com.celik.gokhun.obilet.thisticket.util.getCurrentDateWithFineFormat
import com.celik.gokhun.obilet.thisticket.util.getCurrentDateWithFineFormatTomorrow
import com.celik.gokhun.obilet.thisticket.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel : ViewModel

    private lateinit var fromSpinner: Spinner
    private lateinit var flightFromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var flightToSpinner: Spinner

    private lateinit var dateTextView: TextView

    private lateinit var  swipeRefresh : SwipeRefreshLayout
    private lateinit var  swipeRefreshFlight : SwipeRefreshLayout

    private lateinit var dateFor: String

    private lateinit var returnSection: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateFor = getCurrentDate()

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        dateTextView = findViewById(R.id.dateTextView)

        dateTextView.text = getCurrentDateWithFineFormat()

        viewModel.refreshSessionData()

        swipeRefresh = findViewById(R.id.swipeToRefresh)
        swipeRefreshFlight = findViewById(R.id.swipeRefreshFlight)
        returnSection = findViewById(R.id.returnLayout)


        swipeRefresh.setOnRefreshListener{
            fillSpinners()
        }

        Handler().postDelayed({
            fillSpinners()},2000)



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


            flightFromSpinner = findViewById(R.id.flightFromSpinner)
            this.also { it.also { flightFromSpinner.onItemSelectedListener = it } }

            val aaf = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            flightFromSpinner.adapter = aaf



            flightToSpinner = findViewById(R.id.flightToSpinner)
            this.also { it.also { flightToSpinner.onItemSelectedListener = it } }

            val bbf = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            flightToSpinner.adapter = bbf

        }
        else{
            Toast.makeText(this,"Failed session",Toast.LENGTH_LONG).show()
        }


    }

    fun findTicket(view: View) {
        val originId =  viewModel.idleArray[fromSpinner.selectedItemPosition]
        val destinationId =  viewModel.idleArray[toSpinner.selectedItemPosition]

        if(originId?.equals(destinationId) == false)
        {
            originId?.let {
                destinationId?.let { it1 ->

                    val intent = Intent(this, JourneyActivity::class.java)
                    intent.putExtra("sessionId", viewModel.idleSessionId)
                    intent.putExtra("deviceId", viewModel.idleDeviceId)
                    intent.putExtra("originId", it)
                    intent.putExtra("destinationId", it1)
                    intent.putExtra("date", dateFor)
                    startActivity(intent)

                }
            }
        }
        else{
            Toast.makeText(this,"Aynı yerden aynı yere gidemezsiniz!", Toast.LENGTH_LONG).show()
        }

    }

    fun setToday(view: View){
        dateTextView.text = getCurrentDateWithFineFormat()
        dateFor = getCurrentDate()
    }

    fun setTomorrow(view: View){
        dateTextView.text = getCurrentDateWithFineFormatTomorrow()
        dateFor = getCurrentDateTomorrow()
    }

    fun changeDirection(view: View){
        val idle :Int = fromSpinner.selectedItemPosition
        val idle2 :Int = toSpinner.selectedItemPosition
        fromSpinner.setSelection(idle2)
        toSpinner.setSelection(idle)

        flightFromSpinner.setSelection(idle2)
        flightToSpinner.setSelection(idle)
    }

    fun removeReturn(view: View)
    {
        returnSection.visibility =View.GONE
    }

    fun busSection(view: View) {
        swipeRefresh.visibility = View.VISIBLE
        swipeRefreshFlight.visibility = View.GONE
    }

    fun flightSection(view: View) {
        println("plane kanka")
        swipeRefresh.visibility = View.GONE
        swipeRefreshFlight.visibility = View.VISIBLE
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}
package com.celik.gokhun.obilet.thisticket.view

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel : ViewModel

    private lateinit var fromSpinner: Spinner
    private lateinit var flightFromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var flightToSpinner: Spinner

    private lateinit var dateTextView: TextView

    private lateinit var  swipeRefresh : SwipeRefreshLayout
    private lateinit var  swipeRefreshFlight : SwipeRefreshLayout
    private lateinit var returnSection: LinearLayout

    private lateinit var dateFor: String
    private lateinit var preference : SharedPreferences



    var calendarBus = Calendar.getInstance()
    var calendarFlight = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromSpinner = findViewById(R.id.fromSpinner)
        toSpinner = findViewById(R.id.toSpinner)
        flightFromSpinner = findViewById(R.id.flightFromSpinner)
        flightToSpinner = findViewById(R.id.flightToSpinner)

        preference=getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)


        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        dateTextView = findViewById(R.id.dateTextView)

        dateTextView.text = getCurrentDateWithFineFormatTomorrow()
        dateFor = getCurrentDateTomorrow()

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

    fun setDateBus(view : View){
        DatePickerDialog(this@MainActivity,
            dateSetListener,
            // set DatePickerDialog to point to today's date when it loads up
            calendarBus.get(Calendar.YEAR),
            calendarBus.get(Calendar.MONTH),
            calendarBus.get(Calendar.DAY_OF_MONTH),

        ).show()

    }

    fun setDateFlight(view: View){
        DatePickerDialog(this@MainActivity,
            dateSetListenerFlight,
            // set DatePickerDialog to point to today's date when it loads up
            calendarFlight.get(Calendar.YEAR),
            calendarFlight.get(Calendar.MONTH),
            calendarFlight.get(Calendar.DAY_OF_MONTH),
            ).show()
    }

    private val dateSetListenerFlight = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
            calendarFlight.set(Calendar.YEAR, year)
            calendarFlight.set(Calendar.MONTH, monthOfYear)
            calendarFlight.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInFlightView()
        }
    }

    private fun updateDateInFlightView() {
        dayNumberFlight.text = calendarFlight.get(Calendar.DAY_OF_MONTH).toString()
        //mounthFlight.text = calendarFlight.get(Calendar.MONTH).toString()
        //dayFlight.text = calendarFlight.get(Calendar.DAY_OF_WEEK).toString()
    }

    private val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
            calendarBus.set(Calendar.YEAR, year)
            calendarBus.set(Calendar.MONTH, monthOfYear)
            calendarBus.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInBusView()
        }
    }

    private fun updateDateInBusView() {
        val sdf = SimpleDateFormat("dd MM yyyy EE")
        dateTextView.text = sdf.format(calendarBus.getTime())
    }

    private fun fillSpinners(){

        if (viewModel.busLocations.value?.status.equals("Success"))
        {
            if (swipeRefresh.isRefreshing)
            {
                swipeRefresh.isRefreshing = false
            }

            if (swipeRefreshFlight.isRefreshing)
            {
                swipeRefreshFlight.isRefreshing = false
            }


            val locationSize = viewModel.busLocations.value?.data?.size.toString().toInt()


            val locationsName = arrayOfNulls<String?>(locationSize)
            val locationsId = arrayOfNulls<Int?>(locationSize)

            for (i in 0..locationSize-1){
                locationsName[i] = viewModel.busLocations.value?.data?.get(i)?.name.toString()
                locationsId[i] = viewModel.busLocations.value?.data?.get(i)?.id.toString().toInt()

            }

            viewModel.idleArray = locationsId


            this.also { it.also { fromSpinner.onItemSelectedListener = it } }

            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fromSpinner.adapter = aa


            this.also { it.also { toSpinner.onItemSelectedListener = it } }

            val bb = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            toSpinner.adapter = bb



            this.also { it.also { flightFromSpinner.onItemSelectedListener = it } }

            val aaf = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            flightFromSpinner.adapter = aaf



            this.also { it.also { flightToSpinner.onItemSelectedListener = it } }

            val bbf = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationsName)
            bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            flightToSpinner.adapter = bbf




            fromSpinner.setSelection(preference.getInt("from",0))
            toSpinner.setSelection(preference.getInt("to",3))
            dateTextView.text=preference.getString("date","Tarihi Seçimi Yapınız")

        }
        else{
            //Toast.makeText(this,"Failed session",Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                fillSpinners()},1000)
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
                    intent.putExtra("stops", fromSpinner.selectedItem.toString()+" - "+ toSpinner.selectedItem.toString())
                    intent.putExtra("showDate", dateTextView.text.toString())



                    val editor=preference.edit()
                    editor.putString("date",dateTextView.text.toString())
                    editor.putInt("from",fromSpinner.selectedItemPosition)
                    editor.putInt("to",toSpinner.selectedItemPosition)
                    editor.commit()

                    startActivity(intent)

                }
            }
        }
        else{
            Toast.makeText(this,"Aynı yerler arası seyahat mümkün değildir!", Toast.LENGTH_LONG).show()
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

    fun addPassenger(view: View){
        Toast.makeText(this, "Yolcu ekleme fonksiyonu pek yakında sizlerle", Toast.LENGTH_LONG).show()
    }

    fun changeDirection(view: View){
        val idle :Int = fromSpinner.selectedItemPosition
        val idle2 :Int = toSpinner.selectedItemPosition
        fromSpinner.setSelection(idle2)
        toSpinner.setSelection(idle)

        flightFromSpinner.setSelection(idle2)
        flightToSpinner.setSelection(idle)
    }

    fun removeReturn(view: View) {
        returnSection.visibility =View.GONE
    }

    fun busSection(view: View) {
        swipeRefresh.visibility = View.VISIBLE
        swipeRefreshFlight.visibility = View.GONE
    }

    fun flightSection(view: View) {
        swipeRefresh.visibility = View.GONE
        swipeRefreshFlight.visibility = View.VISIBLE
        returnSection.visibility = View.VISIBLE

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}
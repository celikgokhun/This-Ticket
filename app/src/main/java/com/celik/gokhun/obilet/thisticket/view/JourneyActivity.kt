package com.celik.gokhun.obilet.thisticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.celik.gokhun.obilet.thisticket.R
import com.celik.gokhun.obilet.thisticket.model.BusJourneysData
import com.celik.gokhun.obilet.thisticket.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_journey.*
import kotlinx.android.synthetic.main.journey_item.view.*

class JourneyActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel
    var journeyList : MutableList<BusJourneysData> = ArrayList()

    lateinit var adapter: TicketAdapter
    lateinit var  layoutManager: LinearLayoutManager

    private lateinit var  swipeRefresh : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)

        swipeRefresh = findViewById(R.id.swipeRefreshTickets)

        layoutManager = LinearLayoutManager(this)
        recyclerViewTicket.layoutManager =layoutManager

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        viewModel.refreshBusJourneysData("PqtdftjloK3Kpka97+ILDzMa6D9740nggLiTzXiLlzA=","PqtdftjloK3Kpka97+ILDzMa6D9740nggLiTzXiLlzA=",349,356,"2021-10-01")

        Handler().postDelayed(
            {
                getPage()
            },
            4000)


        swipeRefresh.setOnRefreshListener {
            getPage()
        }

    }

    private fun getPage() {
        journeyList.clear()
        recyclerViewTicket.visibility= View.VISIBLE
        ticketLoading.visibility=View.GONE

        if (viewModel.busJourneys.value?.status.equals("Success"))
        {
            for (element in viewModel.busJourneys.value?.data!!)
            {
                if (element != null) {
                    journeyList.add(element)
                }
            }

            Handler().postDelayed({
                if (::adapter.isInitialized)
                {
                    adapter.notifyDataSetChanged()
                }

                else
                {
                    adapter = TicketAdapter(this)
                    recyclerViewTicket.adapter = adapter
                }

                if (swipeRefresh.isRefreshing)
                {
                    swipeRefresh.isRefreshing = false
                }

            },2000)
        }

    }

    class TicketAdapter(private val activity: JourneyActivity) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>(){

        class TicketViewHolder(v : View) : RecyclerView.ViewHolder(v) {
            val journeyAdapter =  v.findViewById<LinearLayout>(R.id.journeyLayout)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
            return TicketViewHolder(LayoutInflater.from(activity).inflate(R.layout.journey_item,parent,false) )
        }

        override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
            holder.journeyAdapter.departureTimeText.text = activity.journeyList[position].journey?.departure!!.split("T")[1].split(":")[0]+":"+activity.journeyList[position].journey?.departure!!.split("T")[1].split(":")[1]
            holder.journeyAdapter.arrivalTimeText.text = activity.journeyList[position].journey?.arrival!!.split("T")[1].split(":")[0]+":"+activity.journeyList[position].journey?.arrival!!.split("T")[1].split(":")[1]
            holder.journeyAdapter.originDestinationText.text = activity.journeyList[position].journey?.origin+" - "+activity.journeyList[position].journey?.destination
            holder.journeyAdapter.priceText.text = activity.journeyList[position].journey?.internetPrice.toString()+" "+ activity.journeyList[position].journey?.currency
        }

        override fun getItemCount(): Int {
            return activity.journeyList.size
        }
    }

}



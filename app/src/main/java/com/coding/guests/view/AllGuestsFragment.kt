package com.coding.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.guests.R
import com.coding.guests.service.constants.GuestConstants
import com.coding.guests.view.adapter.GuestAdapter
import com.coding.guests.view.listener.GuestListener
import com.coding.guests.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        // RecyclerView
        // 1 - Obter recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        // 2 - Definir layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adapter: junta os dados do repositório com o layout (fragment_all.xml)
        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun OnClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        mAdapter.attachListener(mListener)
        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}
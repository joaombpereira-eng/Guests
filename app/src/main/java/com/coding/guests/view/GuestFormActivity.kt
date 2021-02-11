package com.coding.guests.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coding.guests.viewmodel.GuestFormViewModel
import com.coding.guests.R
import com.coding.guests.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityGuestFormBinding.inflate(layoutInflater) }
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            mViewModel.save(name, presence)
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it)
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
        })
    }
}
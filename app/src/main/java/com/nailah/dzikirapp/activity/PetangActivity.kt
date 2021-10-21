package com.nailah.dzikirapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.nailah.dzikirapp.R
import com.nailah.dzikirapp.adapter.DzikirDoaAdapter
import com.nailah.dzikirapp.databinding.ActivityDzikirPagiPetangBinding
import com.nailah.dzikirapp.databinding.ActivityPetangBinding
import com.nailah.dzikirapp.model.DataDzikirDoa
import com.nailah.dzikirapp.model.DzikirDoa

class PetangActivity : AppCompatActivity() {
    private  lateinit var petangBinding: ActivityPetangBinding
    private var listDzikirDoa : ArrayList<DzikirDoa> = arrayListOf()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         petangBinding = ActivityPetangBinding.inflate(layoutInflater)
        setContentView(petangBinding.root)
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
         showRecyclerList()
    }

    private fun showRecyclerList() {
        listDzikirDoa.clear()
        listDzikirDoa.addAll(DataDzikirDoa.listDzikirPetang)
        petangBinding.rvPetang.layoutManager = LinearLayoutManager(this)
        petangBinding.rvPetang.adapter = DzikirDoaAdapter(listDzikirDoa)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        fun getLaunchService(from : Context) = Intent(from, PetangActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}

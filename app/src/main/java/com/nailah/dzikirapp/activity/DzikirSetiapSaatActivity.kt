package com.nailah.dzikirapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nailah.dzikirapp.MainActivity
import com.nailah.dzikirapp.R
import com.nailah.dzikirapp.adapter.DzikirDoaAdapter
import com.nailah.dzikirapp.databinding.ActivityDzikirSetiapSaatBinding
import com.nailah.dzikirapp.model.DataDzikirDoa
import com.nailah.dzikirapp.model.DzikirDoa

class DzikirSetiapSaatActivity : AppCompatActivity() {
    private lateinit var dzikirSetiapSaatBinding: ActivityDzikirSetiapSaatBinding
    private var listDzikirSetiapSaat : ArrayList<DzikirDoa> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dzikirSetiapSaatBinding = ActivityDzikirSetiapSaatBinding.inflate(layoutInflater)
        setContentView(dzikirSetiapSaatBinding.root)
        supportActionBar?.hide()
        showRecyclerList()
        back()
    }

    private fun back() {
        dzikirSetiapSaatBinding.ivBackSetiapSaat.setOnClickListener{
            startActivity(MainActivity.getLaunchService(this))
        }
    }

    private fun showRecyclerList() {
        dzikirSetiapSaatBinding.rvDzikirSetiapSaat.layoutManager = LinearLayoutManager(this)

        listDzikirSetiapSaat.clear()
        listDzikirSetiapSaat.addAll(DataDzikirDoa.listDzikir)

        dzikirSetiapSaatBinding
            .rvDzikirSetiapSaat.adapter = DzikirDoaAdapter(listDzikirSetiapSaat)

        dzikirSetiapSaatBinding.rvDzikirSetiapSaat.setHasFixedSize(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object{
        fun getLaunchService(from : Context) = Intent(from, DzikirSetiapSaatActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
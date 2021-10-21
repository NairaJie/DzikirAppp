package com.nailah.dzikirapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nailah.dzikirapp.R
import com.nailah.dzikirapp.databinding.ActivityDetailArtikelBinding
import com.nailah.dzikirapp.model.Artikel

class DetailArtikelActivity : AppCompatActivity() {
    private lateinit var detailArtikelBinding: ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailArtikelBinding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(detailArtikelBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Artikel Islami"

        val  dataArtikel = intent.getParcelableExtra<Artikel>("data")
        detailArtikelBinding.ivDetail.setImageResource(dataArtikel!!.imageArtikel)
        detailArtikelBinding.tvTitleDetail.text = dataArtikel.tittleArtikel
        detailArtikelBinding.tvDescDetail.text = dataArtikel.descArtikel
    }
}
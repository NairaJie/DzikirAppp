package com.nailah.dzikirapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.nailah.dzikirapp.activity.DetailArtikelActivity
import com.nailah.dzikirapp.activity.DzikirPagiPetangActivity
import com.nailah.dzikirapp.activity.DzikirSetiapSaatActivity
import com.nailah.dzikirapp.activity.QauliyahActivity
import com.nailah.dzikirapp.adapter.ArtikelAdapter
import com.nailah.dzikirapp.adapter.OnItemClickCallback
import com.nailah.dzikirapp.databinding.ActivityMainBinding
import com.nailah.dzikirapp.model.Artikel

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var artikelArray : ArrayList<Artikel> = arrayListOf()
    private var dotsCount = 0
    private lateinit var dotSlider : Array<ImageView?>

    private val slidingCall = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            for (dot in 0 until dotsCount){
                dotSlider[dot]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.non_active_dot
                    )
                )
            }
            dotSlider[position]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.active_dot
                )
            )
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        supportActionBar?.hide()
        initData()
        initView()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val artikelAdapter = ArtikelAdapter(artikelArray)
        artikelAdapter.setOnItemclickCallback(object : OnItemClickCallback{
            override fun onItemClicked(data: Artikel) {
                val intent = Intent(applicationContext, DetailArtikelActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)

            }
        })
        mainBinding.vpArtikel.apply {
            adapter = artikelAdapter
            registerOnPageChangeCallback(slidingCall)
        }
        dotsCount = artikelArray.size
        dotSlider = arrayOfNulls(dotsCount)
        for (i in 0 until dotsCount){
            dotSlider[i] = ImageView(this)
            dotSlider[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.non_active_dot)

            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8,0,8,0)
            mainBinding.llsider.addView(dotSlider[i], params)
        }
        dotSlider[0]?.setImageDrawable(
            ContextCompat.getDrawable(applicationContext,
            R.drawable.active_dot)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding.vpArtikel.unregisterOnPageChangeCallback(slidingCall)
    }

    private fun initView() {
        mainBinding.llDzikirDoaSholat.setOnClickListener {
            startActivity(QauliyahActivity.getLaunchService(this))
        }
        mainBinding.llDzikirDoaHarian.setOnClickListener {
            startActivity(DzikirSetiapSaatActivity.getLaunchService(this))
        }
        mainBinding.llDzikirPagiPetang.setOnClickListener {
            startActivity(DzikirPagiPetangActivity.getLaunchService(this))
        }
        mainBinding.llSetiapSaat.setOnClickListener {
            startActivity(DzikirSetiapSaatActivity.getLaunchService(this))
        }

    }

    private fun initData() {
        var image = resources.obtainTypedArray(R.array.img_artikel)
        var title = resources.getStringArray(R.array.title_artikel)
        var desc = resources.getStringArray(R.array.desc_artikel)
        artikelArray.clear()
        for (data in title.indices){
            artikelArray.add(
                Artikel(image.getResourceId(data,0),
                title[data],
                desc[data])
            )
        }
        image.recycle()
    }


    companion object{
        fun getLaunchService(from : Context)= Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }

    }
}
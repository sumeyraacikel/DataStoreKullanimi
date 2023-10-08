package com.example.datastorekullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.datastorekullanimi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ap = AppPref(this)

        CoroutineScope(Dispatchers.Main).launch {
           // ap.kayitAd("Ahmet")

            ap.silAd()

            val gelenAd = ap.okuAd()
            Log.e("Gelen Ad", gelenAd)

            val liste = HashSet<String>()
            liste.add("Ali")
            liste.add("Ece")

            ap.kayitListe(liste)

            val gelenListe = ap.okuListe()

            if (gelenListe != null){
                for(a in gelenListe){
                    Log.e("Gelen Arkadaş", a)
                }
            }

            var gelenSayac = ap.okuSayac()
            ap.kayitSayac(++gelenSayac)
            binding.textViewSayac.text = "Açılış Sayısı : $gelenSayac"
        }
    }
}
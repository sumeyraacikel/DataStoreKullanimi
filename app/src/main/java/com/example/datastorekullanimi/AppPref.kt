package com.example.datastorekullanimi

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var context: Context) {
    val Context.ds: DataStore<Preferences> by preferencesDataStore("bilgiler")

    companion object {
        val AD_KEY = stringPreferencesKey("AD")
        val LISTE_KEY = stringSetPreferencesKey("LISTE")
        val SAYAC_KEY = intPreferencesKey("SAYAC")
    }

    suspend fun kayitSayac(sayac: Int) {
        context.ds.edit {
            it[SAYAC_KEY] = sayac
        }
    }

    suspend fun okuSayac(): Int {
        val p = context.ds.data.first()
        return p[SAYAC_KEY] ?: 0
    }

    suspend fun kayitAd(ad: String) {
        context.ds.edit {
            it[AD_KEY] = ad
        }
    }

    suspend fun okuAd(): String {
        val p = context.ds.data.first()
        return p[AD_KEY] ?: "isim yok"
    }


    suspend fun silAd() {
        context.ds.edit {
            it.remove(AD_KEY)
        }
    }

    suspend fun kayitListe(liste: Set<String>) {
        context.ds.edit {
            it[LISTE_KEY] = liste
        }
    }

    suspend fun okuListe(): Set<String>? {
        val p = context.ds.data.first()
        return p[LISTE_KEY]
    }
}
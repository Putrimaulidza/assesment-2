package org.d3if3117.rumuspersegi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if3117.rumuspersegi.model.HasilPersegi

class MainViewModel : ViewModel() {
    private val hasilPersegi = MutableLiveData<HasilPersegi?>()
    fun hitungPersegi(sisi: Float) {
        val keliling = 4 * sisi
        val luas = sisi * sisi
        hasilPersegi.value = HasilPersegi(keliling,luas)
    }
    fun getHasilPersegi(): LiveData<HasilPersegi?> = hasilPersegi

}
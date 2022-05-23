package org.d3if3117.rumuspersegi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import org.d3if3117.rumuspersegi.databinding.ActivityMainBinding
import org.d3if3117.rumuspersegi.model.HasilPersegi


class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnKeliling.setOnClickListener { hitung() }
        viewModel.getHasilPersegi().observe(this, { showResult(it) })
}
    private fun hitung() {
        val sisi = binding.etSisi.text.toString()
        if (TextUtils.isEmpty(sisi)) {
            Toast.makeText(this, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
            return
        }
       viewModel.hitungPersegi(
            sisi.toFloat()
        )

    }

    private fun hitungPersegi(sisi: Float): HasilPersegi{
        val keliling = 4 * sisi
        val luas = sisi * sisi
        return HasilPersegi(keliling, luas)

    }
    private fun showResult(result: HasilPersegi?) {
        if (result == null) return
        binding.tvKeliling.text = getString(R.string.keliling_x, result.keliling)
        binding.tvLuas.text = getString(R.string.luas_x, result.luas)
    }

}
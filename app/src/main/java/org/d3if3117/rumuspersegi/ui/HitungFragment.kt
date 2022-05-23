package org.d3if3117.rumuspersegi.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if3117.rumuspersegi.R
import org.d3if3117.rumuspersegi.model.HasilPersegi
import org.d3if3117.rumuspersegi.databinding.FragmentHitungBinding


class HitungFragment : Fragment(){
    lateinit var binding: FragmentHitungBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnKeliling.setOnClickListener { hitung() }
        viewModel.getHasilPersegi().observe(requireActivity(), { showResult(it) })

    }
    private fun hitung() {
        val sisi = binding.etSisi.text.toString()
        if (TextUtils.isEmpty(sisi)) {
            Toast.makeText(context, R.string.sisi_invalid, Toast.LENGTH_LONG).show()
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
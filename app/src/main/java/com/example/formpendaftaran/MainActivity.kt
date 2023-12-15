package com.example.formpendaftaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.formpendaftaran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            binding.spinnerTipeSekolah.adapter = ArrayAdapter.createFromResource(
                this@MainActivity,
                R.array.tipe_sekolah,
                android.R.layout.simple_spinner_item
            )
            binding.spinnerProvinsi.adapter = ArrayAdapter.createFromResource(
                this@MainActivity,
                R.array.provinsi,
                android.R.layout.simple_spinner_item
            )
            spinnerProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    updateKabupatenKotaSpinner(parent?.getItemAtPosition(position).toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            edtKodePos.filters = arrayOf(android.text.InputFilter.LengthFilter(5))
            edtJumlahSiswa.filters = arrayOf(android.text.InputFilter.LengthFilter(3))

            btnSave.setOnClickListener {
                val namaSekolah = edtNamaSekolah.text.toString()
                val tipeSekolah = spinnerTipeSekolah.selectedItem.toString()
                val provinsi = spinnerProvinsi.selectedItem.toString()
                val kabupatenKota = spinnerKotaKabupaten.selectedItem.toString()
                val email = edtEmail.text.toString()
                val alamat = edtAlamat.text.toString()
                val kodePos = edtKodePos.text.toString()
                val jumlahSiswa = edtJumlahSiswa.text.toString()

                if (namaSekolah.isEmpty()) {
                    edtNamaSekolah.error = "Nama sekolah tidak boleh kosong"
                    edtNamaSekolah.requestFocus()
                    return@setOnClickListener
                }
                if (alamat.isEmpty()) {
                    edtAlamat.error = "Alamat tidak boleh kosong"
                    edtAlamat.requestFocus()
                    return@setOnClickListener
                }
                if (kodePos.isEmpty()) {
                    edtKodePos.error = "Kode pos tidak boleh kosong"
                    edtKodePos.requestFocus()
                    return@setOnClickListener
                }
                if (jumlahSiswa.isEmpty()) {
                    edtJumlahSiswa.error = "Jumlah siswa tidak boleh kosong"
                    edtJumlahSiswa.requestFocus()
                    return@setOnClickListener
                }
                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtEmail.error = "Email tidak boleh kosong dan harus valid"
                    edtEmail.requestFocus()
                    return@setOnClickListener
                }
            }
        }
    }
    private fun updateKabupatenKotaSpinner(selectedProvinsi: String) {
        val arrayResId = when (selectedProvinsi) {
            "Jakarta" -> R.array.KabupatenKota_Jakarta
            "Jawa Barat" -> R.array.KabupatenKota_Jawa_Barat
            "Jawa Tengah" -> R.array.KabupatenKota_Jawa_Tengah
            else -> R.array.KabupatenKota_Jakarta
        }

        binding.spinnerKotaKabupaten.adapter = ArrayAdapter.createFromResource(
            this@MainActivity,
            arrayResId,
            android.R.layout.simple_spinner_item
        )
    }
}
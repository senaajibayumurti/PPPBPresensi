package com.example.pppbpresensi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.pppbpresensi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val izinList = arrayOf(
            "Hadir Tepat Waktu",
            "Sakit",
            "Terlambat",
            "Izin"
        )
        val hidingEditText = findViewById<EditText>(R.id.keterangan_editText)
        hidingEditText.visibility = View.GONE

        with(binding){
            // Ngatur konten izin_spinner
            val adapterIzin = ArrayAdapter<String>(this@MainActivity,
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                izinList)
            izinSpinner.adapter =adapterIzin

            izinSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, p3: Long)
                    {
                        val itemSelected = izinSpinner.selectedItem.toString()
                        if(itemSelected == izinList[1] || itemSelected == izinList[2] || itemSelected == izinList[3]){
                            hidingEditText.visibility = View.VISIBLE
                        }
                        else {
                            hidingEditText.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            presensiButton.setOnClickListener {
                val itemSelected = izinSpinner.selectedItem.toString()
                if (itemSelected == izinList[0]){
                    val selectedTime = "${timePickerPresensi.hour} : ${timePickerPresensi.minute}"
                    val selectedDate = "${datePickerPresensi.dayOfMonth} ${getMonthName(datePickerPresensi.month)} ${datePickerPresensi.year}"
                    Toast.makeText(
                        this@MainActivity,
                        "Presensi berhasil $selectedDate jam $selectedTime",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else{
                    if (hidingEditText.text.toString() == ""){
                        Toast.makeText(
                            this@MainActivity,
                            "Keterangan tidak boleh kosong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else{
                        val selectedTime = "${timePickerPresensi.hour} : ${timePickerPresensi.minute}"
                        val selectedDate = "${datePickerPresensi.dayOfMonth} ${getMonthName(datePickerPresensi.month)} ${datePickerPresensi.year}"
                        Toast.makeText(
                            this@MainActivity,
                            "Presensi berhasil $selectedDate jam $selectedTime",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getMonthName(month: Int): String {
        val monthNames = arrayOf(
            "Januari", "Februari", "Maret", "April",
            "Mei", "Juni", "Juli", "Agustus",
            "September", "Oktober", "November", "Desember"
        )
        return monthNames[month]
    }
}
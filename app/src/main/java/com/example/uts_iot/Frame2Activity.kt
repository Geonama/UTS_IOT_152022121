package com.example.uts_iot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uts_iot.api.ApiClient
import com.example.uts_iot.model.SuhuResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Frame2Activity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var btnViewSuhu: Button
    private lateinit var btnClose: Button
    private lateinit var tvSuhuData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame2)

        // Menginisialisasi tombol dan TextView
        btnBack = findViewById(R.id.btnBack)
        btnViewSuhu = findViewById(R.id.btnViewSuhu)
        btnClose = findViewById(R.id.btnClose)
        tvSuhuData = findViewById(R.id.tvSuhuData)

        // Button untuk kembali ke MainActivity (Frame 1)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup Frame2Activity setelah kembali ke MainActivity
        }

        // Button untuk menampilkan data suhu
        btnViewSuhu.setOnClickListener {
            fetchSuhuData()
        }

        // Button untuk menutup data suhu
        btnClose.setOnClickListener {
            // Reset text suhu
            tvSuhuData.text = "Suhu: Loading..."
        }
    }

    private fun fetchSuhuData() {
        // Mengambil data suhu dari API
        val apiService = ApiClient.getApiService()
        val call = apiService.getSuhuData()

        call.enqueue(object : Callback<SuhuResponse> {
            override fun onResponse(call: Call<SuhuResponse>, response: Response<SuhuResponse>) {
                if (response.isSuccessful) {
                    val suhuData = response.body()
                    suhuData?.let {
                        // Menampilkan data suhu di TextView
                        tvSuhuData.text = "Suhu Max: ${it.suhu_max}\nSuhu Min: ${it.suhu_min}\nSuhu Avg: ${it.suhu_avg}"
                    }
                } else {
                    // Menampilkan pesan error jika gagal
                    Toast.makeText(this@Frame2Activity, "Gagal mengambil data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SuhuResponse>, t: Throwable) {
                // Menampilkan pesan error jika gagal koneksi
                Toast.makeText(this@Frame2Activity, "Koneksi gagal", Toast.LENGTH_LONG).show()
            }
        })
    }
}

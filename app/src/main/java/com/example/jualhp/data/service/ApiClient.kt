package com.example.jualhp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Object untuk mengelola instance Retrofit dan ApiService
object ApiClient {
    // Base URL untuk API
    private const val BASE_URL = "https://dummyjson.com/"

    // Inisialisasi Retrofit secara lazy (hanya dibuat ketika dipanggil)
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            // Mengatur base URL untuk API
            .baseUrl(BASE_URL)
            // Menambahkan konverter GSON untuk parsing data JSON ke dalam objek Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Inisialisasi ApiService secara lazy (hanya dibuat ketika dipanggil)
    val apiService: ApiService by lazy {
        // Membuat instance dari ApiService menggunakan Retrofit
        retrofit.create(ApiService::class.java)
    }
}

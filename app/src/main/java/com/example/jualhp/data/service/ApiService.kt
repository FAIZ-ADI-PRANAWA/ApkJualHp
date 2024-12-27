package com.example.jualhp.data.service

import com.example.jualhp.data.model.CategoryProductResponse
import retrofit2.http.GET

// Interface ApiService untuk mendefinisikan endpoint API yang digunakan aplikasi
interface ApiService {
    // Mendefinisikan endpoint GET untuk mendapatkan daftar kategori produk
    // URL lengkap akan menjadi: BASE_URL + "c/2483-f5be-4a82-8b30"
    @GET("c/2483-f5be-4a82-8b30")
    // Fungsi ini menggunakan coroutine (suspend function) untuk mendapatkan response dari API
    suspend fun getCategoryProducts(): CategoryProductResponse
}

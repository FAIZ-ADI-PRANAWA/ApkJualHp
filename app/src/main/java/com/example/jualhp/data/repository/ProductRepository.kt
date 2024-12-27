package com.example.jualhp.data.repository

import com.example.jualhp.data.model.Product
import com.example.jualhp.data.service.ApiClient

// Repository ini bertanggung jawab untuk mengelola data terkait produk.
// Termasuk pengambilan data dari API dan manipulasi data sebelum diberikan ke layer lain.
class ProductRepository {
    // Inisialisasi layanan API melalui ApiClient
    private val apiService = ApiClient.apiService

    /**
     * Fungsi untuk mendapatkan daftar semua produk.
     * Fungsi ini menjalankan permintaan API untuk mengambil semua produk dalam kategori.
     * @return Daftar produk yang diperoleh dari respons API.
     */
    suspend fun getProducts(): List<Product> {
        // Mengambil respons kategori produk dari API
        val response = apiService.getCategoryProducts()
        // Mengembalikan daftar produk dari respons
        return response.products
    }

    /**
     * Fungsi untuk mendapatkan detail produk berdasarkan ID.
     * Fungsi ini mencari produk di dalam daftar berdasarkan ID tertentu.
     * @param productId ID produk yang dicari
     * @return Produk yang cocok dengan ID yang diberikan
     */
    suspend fun getProductById(productId: Int): Product {
        // Mengambil daftar produk dari respons API
        val products = apiService.getCategoryProducts().products
        // Mencari produk yang sesuai dengan ID dan mengembalikan hasil
        return products.first { it.id == productId }
    }
}

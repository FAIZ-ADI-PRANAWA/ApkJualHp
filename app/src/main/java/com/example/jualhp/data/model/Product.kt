package com.example.jualhp.data.model

// Data class untuk response dari API terkait kategori produk.
// Setiap kategori memiliki ID, nama kategori, dan daftar produk di dalamnya.
data class CategoryProductResponse(
    val categoryId: String, // ID dari kategori
    val categoryName: String, // Nama kategori
    val products: List<Product> // Daftar produk dalam kategori
)

// Data class untuk merepresentasikan data produk.
// Setiap produk memiliki atribut-atribut seperti ID, judul, deskripsi, harga, rating, dan URL gambar.
data class Product(
    val id: Int, // ID unik dari produk
    val title: String, // Judul atau nama produk
    val description: String, // Deskripsi produk
    val price: Int, // Harga produk dalam bentuk integer
    val rating: Float, // Rating produk dalam skala float
    val image: String // URL atau path untuk gambar produk
)

package com.example.jualhp.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jualhp.data.model.Product
import com.example.jualhp.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel untuk menangani logika dan data terkait daftar produk
class ProductListViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {

    // MutableStateFlow untuk menyimpan daftar produk dan memungkinkan pengamatan perubahan
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products // StateFlow untuk expose _products agar hanya bisa dibaca di luar

    // Blok init dipanggil saat ViewModel pertama kali dibuat
    init {
        fetchProducts() // Memulai proses pengambilan daftar produk
    }

    // Fungsi untuk mengambil data produk dari repository
    private fun fetchProducts() {
        viewModelScope.launch { // Menjalankan coroutine dalam cakupan ViewModel
            try {
                // Mengambil data dari repository dan memperbarui nilai _products
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                // Menangkap dan mencetak kesalahan jika terjadi error
                e.printStackTrace()
            }
        }
    }
}

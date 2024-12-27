package com.example.jualhp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jualhp.data.model.Product
import com.example.jualhp.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel untuk layar detail produk
class ProductDetailsViewModel(
    private val repository: ProductRepository = ProductRepository() // Inisialisasi repository untuk mendapatkan data
) : ViewModel() {

    // StateFlow untuk memantau perubahan data produk
    private val _product = MutableStateFlow<Product?>(null) // Nilai awal produk adalah null
    val product: StateFlow<Product?> = _product // StateFlow bersifat read-only untuk kelas eksternal

    // Fungsi untuk mengambil detail produk berdasarkan ID
    fun fetchProductDetails(productId: Int) {
        // Menggunakan coroutine pada viewModelScope untuk mengelola pekerjaan asinkron
        viewModelScope.launch {
            try {
                // Mendapatkan data produk dari repository berdasarkan productId
                _product.value = repository.getProductById(productId)
            } catch (e: Exception) {
                // Menangkap kesalahan yang mungkin terjadi dan mencetak pesan error
                e.printStackTrace()
            }
        }
    }
}

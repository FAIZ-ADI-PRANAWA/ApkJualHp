import 'package:flutter/material.dart'; // Mengimpor pustaka Flutter untuk menggunakan ChangeNotifier dan widget lainnya
import '../models/product.dart'; // Mengimpor model Product
import '../services/api_service.dart'; // Mengimpor ApiService yang digunakan untuk mengambil data produk

// Kelas ProductListProvider bertugas untuk mengelola status produk dan menyediakan data produk
class ProductListProvider extends ChangeNotifier {
  // Daftar produk yang dimiliki
  List<Product> _products = [];

  // Status loading saat data sedang diambil
  bool _isLoading = false;

  // Getter untuk mengambil daftar produk
  List<Product> get products => _products;

  // Getter untuk mengambil status loading
  bool get isLoading => _isLoading;

  // Fungsi untuk mengambil produk dari API
  Future<void> fetchProducts() async {
    // Set status loading menjadi true ketika permintaan data sedang dilakukan
    _isLoading = true;
    // Memberitahu listener bahwa status berubah
    notifyListeners();

    try {
      // Mengambil data produk dari ApiService dan menyimpannya dalam _products
      _products = await ApiService().getProducts();
    } catch (e) {
      // Jika ada error saat mengambil data, tampilkan error ke console
      debugPrint('Error fetching products: $e');
    } finally {
      // Setelah data selesai diambil (atau error), set status loading menjadi false
      _isLoading = false;
      // Memberitahu listener bahwa status berubah
      notifyListeners();
    }
  }
}

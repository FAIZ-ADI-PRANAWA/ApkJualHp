import 'dart:convert'; // Mengimpor pustaka untuk mengkonversi data JSON
import 'package:http/http.dart' as http; // Mengimpor pustaka HTTP untuk melakukan permintaan HTTP
import '../models/product.dart'; // Mengimpor model Product yang sudah didefinisikan sebelumnya

// Kelas ApiService untuk mengelola interaksi dengan API
class ApiService {
  // Base URL untuk API
  static const String baseUrl = 'https://dummyjson.com/';

  // Endpoint untuk kategori produk
  static const String categoryEndpoint = 'c/2483-f5be-4a82-8b30';

  // Fungsi untuk mendapatkan daftar produk dari API
  Future<List<Product>> getProducts() async {
    // Melakukan permintaan GET ke API
    final response = await http.get(Uri.parse(baseUrl + categoryEndpoint));

    // Jika status kode respon 200 (sukses)
    if (response.statusCode == 200) {
      final data = json.decode(response.body);

      // Mengambil daftar produk dari respons JSON
      final List list = data['products'];

      // Mengonversi setiap elemen dalam list menjadi objek Product dan mengembalikannya dalam bentuk List<Product>
      return list.map((json) => Product.fromJson(json)).toList();
    } else {
      // Jika terjadi error saat mengambil data, lemparkan exception
      throw Exception('Failed to load products');
    }
  }

  // Fungsi untuk mendapatkan produk berdasarkan ID
  Future<Product> getProductById(int id) async {
    // Mendapatkan semua produk terlebih dahulu
    final products = await getProducts();
    return products.firstWhere((product) => product.id == id);
  }
}

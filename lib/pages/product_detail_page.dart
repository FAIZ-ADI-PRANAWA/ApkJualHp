import 'package:flutter/material.dart';
import '../models/product.dart'; // Mengimpor model produk
import '../services/api_service.dart'; // Mengimpor layanan API untuk mendapatkan data produk

// Halaman untuk menampilkan detail produk
class ProductDetailPage extends StatefulWidget {
  const ProductDetailPage({super.key});

  @override
  State<ProductDetailPage> createState() => _ProductDetailPageState();
}

class _ProductDetailPageState extends State<ProductDetailPage> {
  Product? product; // Menyimpan data produk yang akan ditampilkan
  bool isLoading = true; // Menyimpan status loading

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    // Mengambil argumen ID produk yang diteruskan dari halaman sebelumnya
    final int productId = ModalRoute.of(context)?.settings.arguments as int;
    _fetchProduct(productId); // Mengambil detail produk berdasarkan ID
  }

  // Fungsi untuk mengambil data produk berdasarkan ID
  Future<void> _fetchProduct(int id) async {
    try {
      final fetched = await ApiService().getProductById(id); // Memanggil API untuk mendapatkan detail produk
      setState(() {
        product = fetched; // Menyimpan data produk yang berhasil diambil
        isLoading = false; // Mengubah status loading menjadi false setelah data diterima
      });
    } catch (e) {
      debugPrint("Error fetching product: $e"); // Menampilkan error jika terjadi kesalahan
      setState(() {
        isLoading = false; // Mengubah status loading menjadi false jika terjadi kesalahan
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Product Detail"), // Judul halaman detail produk
      ),
      body: isLoading
      // Menampilkan indikator loading saat data produk sedang diambil
          ? const Center(child: CircularProgressIndicator())
          : product == null
      // Menampilkan pesan jika produk tidak ditemukan
          ? const Center(child: Text("Product not found"))
          : SingleChildScrollView(
        // Menggunakan SingleChildScrollView agar konten dapat digulir
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Image.network(
              product!.image, // Menampilkan gambar produk
              height: 250,
              fit: BoxFit.cover, // Menyesuaikan gambar dengan ukuran
            ),
            const SizedBox(height: 16),
            Text(
              product!.title, // Menampilkan judul produk
              style: const TextStyle(
                  fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center, // Menyelaraskan teks ke tengah
            ),
            const SizedBox(height: 8),
            Text(
              "\$${product!.price}", // Menampilkan harga produk
              style: const TextStyle(fontSize: 20, color: Colors.green),
            ),
            const SizedBox(height: 16),
            Text(
              product!.description, // Menampilkan deskripsi produk
              style: const TextStyle(fontSize: 16),
              textAlign: TextAlign.justify, // Menyelaraskan teks
            ),
          ],
        ),
      ),
    );
  }
}

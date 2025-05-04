import 'package:flutter/material.dart';
import 'package:provider/provider.dart'; // Mengimpor provider untuk mengakses provider di widget tree
import '../providers/product_list_provider.dart'; // Mengimpor provider untuk daftar produk
import '../widgets/product_item.dart'; // Mengimpor widget untuk menampilkan tiap item produk

// Halaman untuk menampilkan daftar produk
class ProductListPage extends StatelessWidget {
  const ProductListPage({super.key});

  @override
  Widget build(BuildContext context) {
    // Mengakses ProductListProvider untuk mendapatkan status dan data produk
    final provider = Provider.of<ProductListProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text("Product List"), // Judul halaman
      ),
      body: provider.isLoading
      // Menampilkan indikator loading jika sedang memuat data
          ? const Center(child: CircularProgressIndicator())
          : RefreshIndicator(
        // Menambahkan fitur pull-to-refresh untuk memperbarui produk
        onRefresh: provider.fetchProducts,
        child: ListView.builder(
          padding: const EdgeInsets.all(16),
          itemCount: provider.products.length, // Jumlah item produk
          itemBuilder: (context, index) {
            final product = provider.products[index]; // Mengambil produk berdasarkan index
            return ProductItem(
              product: product, // Mengirim produk ke widget ProductItem
              onTap: () {
                // Aksi ketika item produk diketuk
                Navigator.pushNamed(
                  context,
                  '/details', // Rute halaman detail produk
                  arguments: product.id, // Mengirim ID produk sebagai argument
                );
              },
            );
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: provider.fetchProducts, // Menyegarkan daftar produk saat tombol dipencet
        child: const Icon(Icons.refresh), // Ikon tombol refresh
      ),
    );
  }
}

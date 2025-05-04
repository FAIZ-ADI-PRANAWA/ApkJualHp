import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart'; // Mengimpor paket untuk memuat gambar dengan cache
import '../models/product.dart'; // Mengimpor model data produk

// Widget untuk menampilkan item produk dalam bentuk kartu
class ProductItem extends StatelessWidget {
  final Product product; // Menerima objek Product untuk ditampilkan
  final VoidCallback onTap; // Fungsi yang dipanggil saat item di-tap

  const ProductItem({
    super.key,
    required this.product, // Produk yang akan ditampilkan
    required this.onTap, // Fungsi onTap untuk aksi saat item diklik
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 4,
      margin: const EdgeInsets.symmetric(vertical: 8),
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      child: InkWell(
        onTap: onTap,
        borderRadius: BorderRadius.circular(12),
        child: Row(
          children: [
            ClipRRect(
              // Memotong gambar agar berbentuk melengkung di sisi kiri
              borderRadius: const BorderRadius.only(
                topLeft: Radius.circular(12),
                bottomLeft: Radius.circular(12),
              ),
              child: CachedNetworkImage(
                imageUrl: product.image, // Mengambil URL gambar produk
                height: 100, // Mengatur tinggi gambar
                width: 100, // Mengatur lebar gambar
                fit: BoxFit.cover, // Gambar akan menyesuaikan ukuran tanpa mengubah proporsinya
                placeholder: (context, url) => const Center(child: CircularProgressIndicator()), // Menampilkan indikator loading saat gambar dimuat
                errorWidget: (context, url, error) => const Icon(Icons.broken_image), // Menampilkan ikon jika gambar gagal dimuat
              ),
            ),
            const SizedBox(width: 12), // Memberikan ruang antara gambar dan teks
            Expanded(
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 8),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start, // Menyusun teks ke kiri
                  children: [
                    Text(
                      product.title, // Menampilkan judul produk
                      style: const TextStyle(
                        fontSize: 18, // Ukuran font untuk judul
                        fontWeight: FontWeight.bold, // Membuat teks tebal
                      ),
                    ),
                    const SizedBox(height: 4), // Ruang antara judul dan harga
                    Text(
                      '\$${product.price}', // Menampilkan harga produk dengan format dolar
                      style: const TextStyle(
                        fontSize: 16, // Ukuran font untuk harga
                        color: Colors.green, // Warna teks untuk harga
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jualhp.ui.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jualhp.data.model.Product

// Komposisi layar utama daftar produk
@Composable
fun ProductListScreen(viewModel: ProductListViewModel, onNavigateToDetails: (Int) -> Unit) {
    // Memantau perubahan data produk melalui StateFlow dari ViewModel
    val products by viewModel.products.collectAsState()

    // Scaffold menyediakan struktur dasar dengan TopBar dan konten utama
    Scaffold(
        topBar = {
            // Menampilkan TopAppBar yang berpusat dengan judul
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Product Iphone",
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )
                },

                // Warna latar dan teks untuk TopAppBar
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->

        // LazyColumn untuk menampilkan daftar produk secara efisien
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Mengatur padding agar tidak tumpang tindih dengan TopBar
        ) {
            // Menggunakan fungsi items untuk mengiterasi daftar produk
            items(products) { product ->
                ProductItem(product) {
                    onNavigateToDetails(product.id) // Aksi navigasi ke detail produk berdasarkan id
                }
            }
        }
    }
}

// Komposisi untuk setiap item produk dalam daftar
@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    // Card untuk menampilkan informasi produk dengan desain elegan
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Jarak antar item produk
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick // Navigasi saat produk diklik
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Gambar produk
            Image(
                painter = rememberAsyncImagePainter(model = product.image), // Mengambil gambar dari URL
                contentDescription = product.title, // Keterangan untuk gambar (aksesibilitas)
                modifier = Modifier
                    .size(100.dp) // Ukuran gambar
                    .padding(end = 16.dp), // Memberikan ruang di sebelah gambar
                contentScale = ContentScale.Crop // Skalakan gambar agar tetap proporsional
            )

            // Kolom untuk detail produk
            Column(
                modifier = Modifier
                    .weight(1f) // Mengatur kolom untuk menyesuaikan ruang
                    .align(Alignment.CenterVertically) // Menyejajarkan vertikal
            ) {
                // Judul produk
                Text(
                    text = product.title,
                    fontSize = 19.sp, // Ukuran font
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp)) // Spasi antara elemen

                // Harga produk
                Text(
                    text = "Price: $${product.price}", // Menampilkan harga
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(2.dp)) // Spasi antara elemen

                // Rating produk
                Text(
                    text = "Rating: ${product.rating}", // Menampilkan rating
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

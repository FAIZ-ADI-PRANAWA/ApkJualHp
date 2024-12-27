@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jualhp.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductDetailsScreen(productId: String, navController: NavController) {
    // Membuat instance ViewModel untuk menangani data
    val viewModel: ProductDetailsViewModel = remember { ProductDetailsViewModel() }
    // Mengambil state terkini dari produk
    val product = viewModel.product.collectAsState().value

    // Mendapatkan detail produk berdasarkan ID ketika composable diaktifkan
    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId.toInt())
    }

    // Scaffold digunakan untuk menyiapkan kerangka utama halaman (seperti top bar dan konten)
    Scaffold(
        topBar = {
            // CenterAlignedTopAppBar untuk menyusun top bar dengan judul di tengah
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Product Details", // Judul halaman
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center // Menyesuaikan teks di tengah
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        // Ikon navigasi kembali ke layar sebelumnya
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->

        // Jika produk sudah ada, tampilkan datanya
        if (product != null) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp)) {

                // Menampilkan judul produk
                Text(
                    text = product.title,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Menampilkan gambar produk
                Image(
                    painter = rememberAsyncImagePainter(model = product.image),
                    contentDescription = product.title,
                    modifier = Modifier
                        .height(250.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop // Menyesuaikan gambar dalam frame
                )

                // Menampilkan rating produk
                RatingDisplay(rating = product.rating)

                Spacer(modifier = Modifier.height(16.dp))

                // Menampilkan harga produk
                Text(
                    text = "Price: ${product.price} USD",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Menampilkan deskripsi produk
                Text(
                    text = product.description,
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else {
            // Menampilkan indikator loading jika data belum diambil
            CircularProgressIndicator(modifier = Modifier.padding(padding))
        }
    }
}

// Komponen untuk menampilkan rating dalam bentuk ikon bintang
@Composable
fun RatingDisplay(rating: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Mengulang jumlah bintang sesuai dengan rating
        repeat(rating.toInt()) {
            Icon(Icons.Filled.Star, contentDescription = "Star", modifier = Modifier.size(30.dp))
        }
    }
}

package com.example.jualhp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.jualhp.ui.productlist.ProductListScreen
import com.example.jualhp.ui.details.ProductDetailsScreen
import com.example.jualhp.ui.productlist.ProductListViewModel

// MainActivity adalah kelas utama yang menangani aktiviti aplikasi Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Memanggil fungsi AppNavigation untuk menampilkan navigasi di dalam UI aplikasi
            AppNavigation()
        }
    }
}

// Fungsi untuk mendefinisikan navigasi aplikasi menggunakan NavHost
@Composable
fun AppNavigation() {
    // Menginisialisasi NavController untuk menangani navigasi antar screen
    val navController = rememberNavController()

    // Membuat instance viewModel untuk digunakan pada screen produk
    val viewModel = ProductListViewModel()

    // Mendefinisikan rute-rute navigasi aplikasi
    NavHost(navController = navController, startDestination = "productList") {
        // Screen pertama untuk menampilkan daftar produk
        composable("productList") {
            // Menampilkan ProductListScreen dan memberikan callback untuk navigasi ke detail produk
            ProductListScreen(viewModel) { productId ->
                // Navigasi ke screen detail produk dengan membawa productId
                navController.navigate("productDetails/$productId")
            }
        }

        // Screen kedua untuk menampilkan detail produk berdasarkan productId yang diterima dari navigasi
        composable(
            "productDetails/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType }) // Menerima productId sebagai parameter
        ) { backStackEntry ->
            // Mengambil productId dari argumen dan menampilkan screen ProductDetailsScreen
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailsScreen(productId, navController)  // Menambahkan navController untuk navigasi
        }
    }
}

// Fungsi untuk menampilkan preview dari navigasi aplikasi
@Preview
@Composable
fun AppNavigationPreview() {
    // Menampilkan navigasi saat pratinjau
    AppNavigation()
}

class Product {

  final int id;
  final String title;
  final String description;
  final int price;
  final double rating;
  final String image;

  // untuk membuat objek Product dengan data yang diperlukan
  Product({
    required this.id,
    required this.title,
    required this.description,
    required this.price,
    required this.rating,
    required this.image,
  });

  // mengubah data JSON menjadi objek Product
  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'],  // ID produk diambil dari JSON
      title: json['title'],  // Judul produk diambil dari JSON
      description: json['description'],  // Deskripsi produk diambil dari JSON
      price: json['price'],  // Harga produk diambil dari JSON
      rating: json['rating'].toDouble(),  // Rating produk diambil dari JSON, diubah menjadi double
      image: json['image'],  // URL gambar produk diambil dari JSON
    );
  }
}

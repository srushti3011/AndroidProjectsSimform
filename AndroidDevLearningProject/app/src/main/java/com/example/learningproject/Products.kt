package com.example.learningproject

data class ProductItem(
    val name: String,
    val price: Int,
    val star: Double,
    val liked: Int
)

val productInfo = listOf(
    ProductItem(
        name = "Tshirt",
        price = 500,
        star = 4.5,
        liked = 200
    ),
    ProductItem(
        name = "Trousers",
        price = 2000,
        star = 4.8,
        liked = 100
    ),
    ProductItem(
        name = "Chair",
        price = 800,
        star = 3.0,
        liked = 20
    ),
    ProductItem(
        name = "Table",
        price = 1500,
        star = 3.5,
        liked = 1500
    ),
    ProductItem(
        name = "Bottle",
        price = 1200,
        star = 3.8,
        liked = 500
    ),
    ProductItem(
        name = "Earphones",
        price = 2000,
        star = 4.8,
        liked = 2000
    ),
    ProductItem(
        name = "Laptop",
        price = 75000,
        star = 4.7,
        liked = 1200
    ),
    ProductItem(
        name = "Smartphone",
        price = 65000,
        star = 4.6,
        liked = 2200
    ),
    ProductItem(
        name = "Backpack",
        price = 1500,
        star = 4.2,
        liked = 350
    ),
    ProductItem(
        name = "Sneakers",
        price = 4000,
        star = 4.0,
        liked = 800
    ),
    ProductItem(
        name = "Washing Machine",
        price = 25000,
        star = 4.3,
        liked = 600
    ),
    ProductItem(
        name = "Headphones",
        price = 3000,
        star = 4.5,
        liked = 1700
    ),
    ProductItem(
        name = "Photo Frame",
        price = 1500,
        star = 4.9,
        liked = 600
    ),
    ProductItem(
        name = "Bedsheet",
        price = 1200,
        star = 4.0,
        liked = 100
    ),
    ProductItem(
        name = "Hall Carpet",
        price = 2000,
        star = 3.5,
        liked = 300
    ),
    ProductItem(
        name = "Curtains",
        price = 2000,
        star = 4.5,
        liked = 1500
    ),
    ProductItem(
        name = "Dish Set",
        price = 5000,
        star = 4.9,
        liked = 2000
    )
)
package com.example.learningproject

import java.io.Serializable

data class CoffeeItem(
    val imgName: Int,
    val rating: Double,
    val coffeeName: String,
    val descriptionSmall: String,
    val descriptionDetail: String,
    val price: Double,
    val availableVariants: String,
    var isLiked: Boolean
): Serializable

var coffeeDetails = arrayOf(
    mutableMapOf(
        "All Coffee" to arrayOf(
        CoffeeItem(
            imgName = R.drawable.coffee_one,
            rating = 4.8,
            coffeeName = "Caffe Mocha",
            descriptionSmall = "Deep Foam",
            descriptionDetail = "A cappuccino is an approximately 150 ml (5 oz) " +
                    "beverage, with 25 ml of espresso coffee and 85ml of fresh milk " +
                    "the fo.. Read More",
            price = 4.53,
            availableVariants = "Ice/Hot",
            isLiked = false
        ),
        CoffeeItem(
            imgName = R.drawable.coffee_two,
            rating = 4.5,
            coffeeName = "Flat White",
            descriptionSmall = "Espresso",
            descriptionDetail = "A Flat white is an approximately 200 ml (5 oz) beverage, with " +
                    "40 ml of espresso coffee and 85ml of fresh milk the fo.. Read More",
            price = 3.53,
            availableVariants = "Ice",
            isLiked = false
        ),
        CoffeeItem(
            imgName = R.drawable.coffee_three,
            rating = 4.5,
            coffeeName = "Flat White",
            descriptionSmall = "Espresso",
            descriptionDetail = "A Flat white is an approximately 200 ml (5 oz) beverage, with " +
                    "40 ml of espresso coffee and 85ml of fresh milk the fo.. Read More",
            price = 3.53,
            availableVariants = "Ice",
            isLiked = false
        ),
        CoffeeItem(
            imgName = R.drawable.coffee_four,
            rating = 4.1,
            coffeeName = "Caffe Panna",
            descriptionSmall = "Espresso",
            descriptionDetail = "A Caffee Panna is refreshing coffee with...",
            price = 5.53,
            availableVariants = "Ice/Hot",
            isLiked = false
        ),
        CoffeeItem(
            imgName = R.drawable.coffee_five,
            rating = 4.9,
            coffeeName = "Caffe Mocha",
            descriptionSmall = "Deep Foam",
            descriptionDetail = "A cappuccino is an approximately 150 ml (5 oz) beverage, with " +
                    "25 ml of espresso coffee and 85ml of fresh milk the fo.. Read More",
            price = 4.53,
            availableVariants = "Ice/Hot",
            isLiked = false
        )
        )
    ),
    mutableMapOf(
        "Machiato" to arrayOf(
            CoffeeItem(
                imgName = R.drawable.coffee_one,
                rating = 4.8,
                coffeeName = "Caffe Mocha",
                descriptionSmall = "Deep Foam",
                descriptionDetail = "A cappuccino is an approximately 150 ml (5 oz) " +
                        "beverage, with 25 ml of espresso coffee and 85ml of fresh milk " +
                        "the fo.. Read More",
                price = 4.53,
                availableVariants = "Ice/Hot",
                isLiked = false
            )
        )
    ),
    mutableMapOf(
        "Latte" to arrayOf(
            CoffeeItem(
                imgName = R.drawable.coffee_two,
                rating = 4.1,
                coffeeName = "Caffe Panna",
                descriptionSmall = "Espresso",
                descriptionDetail = "A Caffee Panna is refreshing coffee with...",
                price = 5.53,
                availableVariants = "Ice/Hot",
                isLiked = false
            ),
            CoffeeItem(
                imgName = R.drawable.coffee_five,
                rating = 4.9,
                coffeeName = "Mocha Fusi",
                descriptionSmall = "Deep Foam",
                descriptionDetail = "A Mocha Fusi is highest rated coffee which gives you a " +
                        "fresh feeling with great taste",
                price = 7.53,
                availableVariants = "Ice",
                isLiked = false
            ),
        )
    ),
    mutableMapOf(
        "Americano" to arrayOf(
            CoffeeItem(
                imgName = R.drawable.coffee_five,
                rating = 4.8,
                coffeeName = "Caffe Mocha",
                descriptionSmall = "Deep Foam",
                descriptionDetail = "A cappuccino is an approximately 150 ml (5 oz) " +
                        "beverage, with 25 ml of espresso coffee and 85ml of fresh milk " +
                        "the fo.. Read More",
                price = 4.53,
                availableVariants = "Ice/Hot",
                isLiked = false
            ),
            CoffeeItem(
                imgName = R.drawable.coffee_three,
                rating = 4.5,
                coffeeName = "Flat White",
                descriptionSmall = "Espresso",
                descriptionDetail = "A Flat white is an approximately 200 ml (5 oz) beverage, with " +
                        "40 ml of espresso coffee and 85ml of fresh milk the fo.. Read More",
                price = 3.53,
                availableVariants = "Ice",
                isLiked = false
            )
        )
    )
)

val coffeeCategories = arrayOf(
    "All Coffee",
    "Machiato",
    "Latte",
    "Americano"
)
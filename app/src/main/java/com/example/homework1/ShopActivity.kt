package com.example.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework1.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // BMW-ს არჩევა და მომდევნო გვერდზე გადასვლა
            cardBmw.setOnClickListener {
                navigateTo("BMW M3 (F80 generation)", 38000.0, "bmw")
            }
            // Mercedes-ის არჩევა და მომდევნო გვერდზე გადასვლა
            cardMercedes.setOnClickListener {
                navigateTo("Mercedes-Benz CLA-Class", 46400.0, "mercedes")
            }
            // Porsche-ის არჩევა და მომდევნო გვერდზე გადასვლა
            cardPorsche.setOnClickListener {
                navigateTo("Porsche 911 GT3 RS", 189000.0, "porsche")
            }
            // Ferrari-ს არჩევა და მომდევნო გვერდზე გადასვლა
            cardFerrari.setOnClickListener {
                navigateTo("Ferrari 488 Spider", 260000.0, "ferrari")
            }
        }
    }

    // მანქანის სახელი, ფასი და სურათი გადაეცემა PaymentActivity-ს
    private fun navigateTo(name: String, price: Double, image: String) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("CAR_NAME", name)
        intent.putExtra("CAR_PRICE", price)
        intent.putExtra("CAR_IMAGE", image)
        startActivity(intent)
    }
}
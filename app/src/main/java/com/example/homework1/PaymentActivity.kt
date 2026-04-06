package com.example.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework1.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        // წინა გვერდიდან მიღებული მონაცემები
        val carName = intent.getStringExtra("CAR_NAME") ?: ""
        val originalPrice = intent.getDoubleExtra("CAR_PRICE", 0.0)
        val imageName = intent.getStringExtra("CAR_IMAGE") ?: ""

        // 5% ფასდაკლების გამოთვლა
        val discountedPrice = originalPrice * 0.95

        // მანქანის სახელი და ფასი ეკრანზე
        textCarName.text = carName
        textCarPrice.text = "$${String.format("%,.0f", discountedPrice)}"

        // მანქანის სურათის ჩვენება drawable-დან სახელის მიხედვით
        val imageRes = resources.getIdentifier(imageName, "drawable", packageName)
        imageCarPayment.setImageResource(imageRes)

        // საწყისი ჯამის გამოთვლა
        updateTotal(discountedPrice)

        // მიწოდების ტიპის შეცვლისას ჯამის განახლება
        radioGroup.setOnCheckedChangeListener { _, _ ->
            updateTotal(discountedPrice)
        }

        // Pay ღილაკზე დაჭერისას საბოლოო ფასის გამოთვლა და გადასვლა
        buttonPay.setOnClickListener {
            // Express მიწოდების შემთხვევაში $1,700 დამატება
            val finalPrice = if (radioExpress.isChecked) discountedPrice + 1700.0 else discountedPrice
            val intent = Intent(this@PaymentActivity, OrderConfirmationActivity::class.java)
            intent.putExtra("FINAL_PRICE", finalPrice)
            startActivity(intent)
        }
    }

    // საბოლოო ჯამის განახლება მიწოდების ტიპის მიხედვით
    private fun updateTotal(discountedPrice: Double) = with(binding) {
        val total = if (radioExpress.isChecked) discountedPrice + 1700.0 else discountedPrice
        textTotal.text = "Total $${String.format("%,.0f", total)}"
    }
}
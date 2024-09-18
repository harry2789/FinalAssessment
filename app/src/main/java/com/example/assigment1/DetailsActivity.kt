// DetailsActivity.kt
package com.example.assigment1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var property1TextView: TextView
    private lateinit var property2TextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Initialize UI components
        property1TextView = findViewById(R.id.property1TextView)
        property2TextView = findViewById(R.id.property2TextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        // Get the Entity object from the Intent
        val entity = intent.getSerializableExtra("ENTITY") as Entity?

        // Display the entity details
        entity?.let {
            property1TextView.text = it.property1
            property2TextView.text = it.property2
            descriptionTextView.text = it.description
        }
    }
}

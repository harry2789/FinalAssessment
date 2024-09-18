// DashboardActivity.kt
package com.example.assigment1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var entitiesAdapter: EntitiesAdapter
    private var entitiesList = mutableListOf<Entity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.entitiesRecyclerView)
        entitiesAdapter = EntitiesAdapter(entitiesList) { entity ->
            navigateToDetails(entity)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = entitiesAdapter

        // Get the keypass from the Intent (passed from the LoginActivity)
        val keypass = intent.getStringExtra("KEYPASS")
        keypass?.let {
            fetchDashboardData(it)
        }
    }

    // Fetch data from /dashboard/{keypass} endpoint
    private fun fetchDashboardData(keypass: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vu-nit3213-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(DashboardService::class.java)

        apiService.getDashboardData(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val entities = response.body()!!.entities
                    entitiesList.clear()
                    entitiesList.addAll(entities)

                    // Notify the adapter that the data has changed
                    entitiesAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@DashboardActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, "API call failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Navigate to the Details screen
    private fun navigateToDetails(entity: Entity) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("ENTITY", entity)
        startActivity(intent)
    }
}

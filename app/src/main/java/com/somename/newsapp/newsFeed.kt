package com.somename.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.somename.newsapp.databinding.ActivityNewsFeedBinding
import com.somename.newsapp.network.ApiClient
import com.somename.newsapp.network.NewsResponse
import retrofit2.Call
import retrofit2.Response


class NewsFeed : AppCompatActivity() {

    private lateinit var binding: ActivityNewsFeedBinding
    lateinit var drawerLayout:DrawerLayout
    lateinit var actionBarDrawerToggle:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityNewsFeedBinding = ActivityNewsFeedBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.myDrawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout, R.string.nav_open,R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val client = ApiClient.apiService.fetchNews()

        client.enqueue(object:retrofit2.Callback<NewsResponse>{
          override fun onResponse(
              call: Call<NewsResponse>,
              response: Response<NewsResponse>
              ){
              if(response.isSuccessful){
                  Log.d("News",""+response.body())

                  val result = response.body()?.result
                  result?.let{
                      val adapter = MainAdapter(result)
                      val recyclerView = findViewById<RecyclerView>(R.id.MyRecyclerView)
                      val layoutManager = LinearLayoutManager(this@NewsFeed)
                      layoutManager.orientation = LinearLayoutManager.VERTICAL
                      recyclerView.layoutManager = layoutManager
                  }
                }
             }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("failed", ""+t.message)
            }


        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        } else return super.onOptionsItemSelected(item)
    }
}
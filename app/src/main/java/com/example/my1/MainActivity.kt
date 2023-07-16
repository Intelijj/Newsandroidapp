package com.example.my1

import android.annotation.SuppressLint
import android.app.DownloadManager.Request
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class MainActivity : AppCompatActivity(),NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recycleview)
        val fitness = findViewById<TextView>(R.id.fixed)
        val set5=intent.getStringExtra("message_key2")
        val swipe=findViewById<SwipeRefreshLayout>(R.id.swipe)
        val layoutManager=LinearLayoutManager(this)
        fitness.setText(set5)
        recyclerview.layoutManager = layoutManager
        fetchData()
        recyclerview.addItemDecoration(DividerItemDecoration(baseContext,layoutManager.orientation))
        mAdapter = NewsListAdapter(this)
        recyclerview.adapter = mAdapter
        swipe.setOnRefreshListener {
            fitness.setText(set5)
            recyclerview.layoutManager = LinearLayoutManager(this)
            fetchData()
            mAdapter = NewsListAdapter(this)
            recyclerview.adapter = mAdapter
            swipe.isRefreshing=false
        }
    }
    private fun fetchData() {
        val url = intent.getStringExtra("message_key")
        //val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/us.json"
        //val url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            com.android.volley.Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("publishedAt")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    override fun onItemClicked(item: News) {
        val builder=CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}
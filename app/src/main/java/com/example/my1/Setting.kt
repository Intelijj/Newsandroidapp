package com.example.my1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class Setting : AppCompatActivity(),NewsItemClicked  {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val usa = findViewById<LinearLayout>(R.id.usa)
        val england = findViewById<LinearLayout>(R.id.england)
        val france = findViewById<LinearLayout>(R.id.france)
        val aus = findViewById<LinearLayout>(R.id.aus)
        val russia = findViewById<LinearLayout>(R.id.russia)
        val recyclerview = findViewById<RecyclerView>(R.id.recycleview)
        val layoutManager= LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        fetchData()
        recyclerview.addItemDecoration(DividerItemDecoration(baseContext,layoutManager.orientation))
        mAdapter = NewsListAdapter(this)
        recyclerview.adapter = mAdapter
        usa.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/usa.json"
            val message2="United States of America"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        england.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/gb.json"
            val message2="Great Britain"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        france.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/fr.json"
            val message2="France"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        aus.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/au.json"
            val message2="Australia"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        russia.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/ru.json"
            val message2="Russia"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
    }
    private fun fetchData() {
        val url="https://saurav.tech/NewsAPI/everything/cnn.json"
        //val url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
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
        val builder= CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}
package com.example.my1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class Artcles : AppCompatActivity(),NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artcles)
        val recyclerview = findViewById<RecyclerView>(R.id.recycleview)
        val layoutManager=LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        fetchData()
        recyclerview.addItemDecoration(DividerItemDecoration(baseContext,layoutManager.orientation))
        mAdapter = NewsListAdapter(this)
        recyclerview.adapter = mAdapter
//        val buttonClick = findViewById<Button>(R.id.CNN)
//        val fox = findViewById<Button>(R.id.Fox)
//        val google = findViewById<Button>(R.id.google)
//        val bbc = findViewById<Button>(R.id.BBC)
        val world=findViewById<LinearLayout>(R.id.world)
        val home=findViewById<LinearLayout>(R.id.home)
        val sports=findViewById<LinearLayout>(R.id.sports)
        val trending=findViewById<LinearLayout>(R.id.trending)
        val buisness=findViewById<LinearLayout>(R.id.business)
        val buttonclick6=findViewById<ImageButton>(R.id.savebutton)
//        buttonClick.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            val message1="https://saurav.tech/NewsAPI/everything/cnn.json"
//            val message2="Covid-19"
//            intent.putExtra("message_key2",message2)
//            intent.putExtra("message_key", message1)
//            startActivity(intent)
//        }
//        fox.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            val message1="https://saurav.tech/NewsAPI/everything/fox-news.json"
//            val message2="Fox News"
//            intent.putExtra("message_key2",message2)
//            intent.putExtra("message_key", message1)
//            startActivity(intent)
//        }
//        google.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            val message1="https://saurav.tech/NewsAPI/everything/google-news.json"
//            val message2="Google News"
//            intent.putExtra("message_key2",message2)
//            intent.putExtra("message_key", message1)
//            startActivity(intent)
//        }
//        bbc.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            val message1="https://saurav.tech/NewsAPI/everything/bbc-news.json"
//            val message2="BBC News"
//            intent.putExtra("message_key2",message2)
//            intent.putExtra("message_key", message1)
//            startActivity(intent)
//        }
        home.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        sports.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/sports/us.json"
            val message2="Sports"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        buisness.setOnClickListener {
            val intent = Intent(this, Setting::class.java)
            startActivity(intent)
        }
        world.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/us.json"
            val message2="World"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        trending.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val message1="https://saurav.tech/NewsAPI/top-headlines/category/health/us.json"
            val message2="Trending"
            intent.putExtra("message_key2",message2)
            intent.putExtra("message_key", message1)
            startActivity(intent)
        }
        buttonclick6.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,buttonclick6)
            popupMenu.menu.add("World")
            popupMenu.menu.add("Exit")
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { menuItem ->
                if (menuItem.title === "Exit") {
                    finish()
                    return@OnMenuItemClickListener true
                }else if(menuItem.title=="World"){
                    val intent = Intent(this, MainActivity::class.java)
                    val message1="https://saurav.tech/NewsAPI/top-headlines/category/general/us.json"
                    val message2="World"
                    intent.putExtra("message_key2",message2)
                    intent.putExtra("message_key", message1)
                    startActivity(intent)
                    return@OnMenuItemClickListener true
                }
                false
            })
        }
    }
    private fun fetchData() {
        //6xUX94RFKLLFj_DLX6jl1_zBWqS-V0a6enc5SJxVv6k
        //val url="https://newsapi.org/v2/top-headlines/sources?category=sports&apiKey=55888d3d22294f5b99d0e4d851ae0505"
        val url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
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
/*<TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Top Sources"
        android:layout_below="@+id/indexArticles"
        android:layout_marginTop="19dp"
        android:padding="12dp"
        android:textSize="21dp"
        android:textColor="#1D212D"
        android:textStyle="bold"
        android:id="@+id/popularArticles"/>
    <HorizontalScrollView
        android:layout_width="match_parent"
        android:layout_marginHorizontal="12dp"
        android:layout_below="@+id/popularArticles"
        android:id="@+id/scrollbarpopular"
        android:layout_marginTop="19dp"
        android:layout_height="69dp"
        >
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <Button
                android:layout_width="150dp"
                android:layout_height="59dp"
                android:id="@+id/CNN"
                android:background="@drawable/articlesrounded"
                android:backgroundTint="#1D212D"
                android:text="CNN"
                android:textColor="@color/white"
                android:textAllCaps="false"
                android:textSize="19dp" />

            <Button
                android:layout_width="150dp"
                android:layout_height="59dp"
                android:background="@drawable/articlesrounded"
                android:backgroundTint="#1D212D"
                android:textAllCaps="false"
                android:text="Google"
                android:layout_marginLeft="20dp"
                android:textColor="@color/white"
                android:textSize="19dp"
                android:id="@+id/google"/>
            <Button
                android:layout_width="150dp"
                android:layout_height="59dp"
                android:layout_marginLeft="20dp"
                android:background="@drawable/articlesrounded"
                android:textAllCaps="false"
                android:backgroundTint="#1D212D"
                android:id="@+id/Fox"
                android:text="Fox News"
                android:textColor="@color/white"
                android:textSize="19dp" />
            <Button
                android:layout_width="150dp"
                android:layout_height="59dp"
                android:background="@drawable/articlesrounded"
                android:backgroundTint="#1D212D"
                android:text="BBC"
                android:layout_marginLeft="20dp"
                android:textAllCaps="false"
                android:textColor="@color/white"
                android:textSize="19dp"
                android:id="@+id/BBC"/>

        </LinearLayout>
    </HorizontalScrollView>
 */
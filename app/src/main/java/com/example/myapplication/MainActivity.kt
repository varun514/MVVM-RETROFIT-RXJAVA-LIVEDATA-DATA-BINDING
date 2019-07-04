package com.example.myapplication

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import android.widget.Toast
import com.android.databinding.library.baseAdapters.BR
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //val textviewres=findViewById<TextView>(R.id.text_view_result)

        var errorhap="Hello"

        var retrofit= Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        var jsonPlace:JsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi::class.java)

        var call = jsonPlace.posts as Call<List<Post>>
        call.enqueue(object:Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>)
            {
                if(!response.isSuccessful())
                {
                    //textviewres.setText("code: "+ response.code());
                }
                var posts = response.body() as List<Post>
                for (po in posts)
                {
                    var content=""
                    content += po.title+"/n" +po.text+"/n";
                    //textviewres.append(content);
                    mBinding.user=po
                }
             }
            override fun onFailure(call: Call<List<Post>>, t: Throwable)
            {
                //TODO("not implemented")
                //textviewres.append(errorhap)

            }
        })
    }
}

package com.wonmirzo.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.wonmirzo.retrofit.databinding.ActivityMainBinding
import com.wonmirzo.retrofit.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        binding.button.setOnClickListener {
            val number = binding.editTextNumber.text.toString()
            viewModel.getCustomPost2(Integer.parseInt(number), options)

            viewModel.myCustomPosts2.observe(this) { response ->
                if (response.isSuccessful) {
                    binding.textView.text = response.body()?.toString()
                    response.body()?.forEach {
                        Log.d("Response", it.userId.toString())
                        Log.d("Response", it.id.toString())
                        Log.d("Response", it.title)
                        Log.d("Response", it.body)
                        Log.d("Response", "-----------")
                    }
                } else {
                    binding.textView.text = response.code().toString()
                }
            }
        }
    }
}
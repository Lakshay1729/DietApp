package com.example.dietary.TabsFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.dietary.R
import com.example.dietary.databinding.FragmentSearchRecipesByNutrientsBinding
import com.example.dietary.retrofit.Repository.SearchRepository
import com.example.dietary.retrofit.Service.SpoonacularService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchRecipesByNutrients : Fragment() {


    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding=FragmentSearchRecipesByNutrientsBinding.inflate(inflater,container,false)
        //val view:View=inflater.inflate(R.layout.fragment_search_recipes_by_nutrients, container, false)


        val repository = SearchRepository(SpoonacularService.create())
        repository.SearchByNutrients(25, 45, 8,10,100,400,20,40,6)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ result ->
                  Log.d( "Result",result?.list?.get(0)?.title.toString())
                    Toast.makeText(context,result?.list?.get(0)?.calories.toString(),Toast.LENGTH_LONG).show()
                }, { error ->
                    Log.d("Error", error.printStackTrace().toString())
                    error.printStackTrace()
                })
        return view
    }
    }

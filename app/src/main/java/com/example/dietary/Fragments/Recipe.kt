package com.example.dietary.Fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

import com.example.dietary.R
import com.example.dietary.TabsFragments.GetRecipeNutrition
import com.example.dietary.TabsFragments.SearchRecipesByIngredients
import com.example.dietary.TabsFragments.SearchRecipesByNutrients
import com.example.dietary.databinding.TabviewBinding
import com.example.dietary.retrofit.Repository.SearchRepository
import com.example.dietary.retrofit.Service.SpoonacularService
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
//import com.example.dietary.retrofit.data.Recipes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.tabview.*
import com.example.dietary.TabsFragments.SearchRecipes as SearchRecipes1

/**
 * A simple [Fragment] subclass.
 */
class Recipe : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_recipe, container, false)
        tabLayout = view.findViewById(R.id.tabs) as TabLayout
        viewPager = view.findViewById(R.id.viewpager) as ViewPager2
        viewPager?.setAdapter(MyAdapter(childFragmentManager, lifecycle))


        TabLayoutMediator(tabLayout!!, viewPager!!, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val vie= View.inflate(context,R.layout.tabview,null)
            val binding=TabviewBinding.bind(vie)
            when (position) {
                0 -> {
//                    tab.setText("search recipes")
                    binding.actionChip.setText("Search Recipes")
                    tab.setCustomView(vie)
                              }
                1 -> {
                    //tab.setText("Search By Nutrition")
                    vie.findViewById<Chip>(R.id.action_chip).setText("Search By Nutrition")

//                    vie.findViewById<Chip>(R.id.action_chip).setOnClickListener(View.OnClickListener {  })
                    tab.setCustomView(vie)
                }
                2 -> {
                   // tab.setText("Search By Ingredients")
                    vie.findViewById<Chip>(R.id.action_chip).setText("Search By Ingredients")
                    tab.setCustomView(vie)
                }
                3 -> {
                   // tab.setText("Get Recipe Nutrition")
                    vie.findViewById<Chip>(R.id.action_chip).setText("Get Recipe Nutrition")
                    tab.setCustomView(vie)
                }
            }
            binding.actionChip.setOnClickListener(View.OnClickListener { viewPager?.currentItem=position
               // this.action_chip.chipBackgroundColor= ColorStateList.createFromXml(R.color.colorstates,,null)
            })


        }).attach()
        return view
    }

    private inner class MyAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {
        private val int_items = 4

        override fun createFragment(position: Int): Fragment {

            when (position) {
                0 -> return SearchRecipes1()
                1 -> return SearchRecipesByNutrients()
                2 -> return  SearchRecipesByIngredients()
                3 ->return GetRecipeNutrition()
                //4 -> fragment = ViewPagerFragment()
            }
            return SearchRecipes1()

        }
        override fun getItemCount(): Int {
            return int_items
        }
    }
}



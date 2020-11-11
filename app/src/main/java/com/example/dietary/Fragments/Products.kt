package com.example.dietary.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

import com.example.dietary.R
import com.example.dietary.TabsFragments.GetRecipeNutrition
import com.example.dietary.TabsFragments.SearchRecipes
import com.example.dietary.TabsFragments.SearchRecipesByIngredients
import com.example.dietary.TabsFragments.SearchRecipesByNutrients
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class Products : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View=inflater.inflate(R.layout.fragment_products, container, false)
        tabLayout = view.findViewById(R.id.tabs) as TabLayout
        viewPager = view.findViewById(R.id.viewpager) as ViewPager2
        viewPager.setAdapter(ProductsAdapter(childFragmentManager, lifecycle))
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> {
                    tab.setText("search recipes")
//                    val vie= View.inflate(context,R.layout.tabview,null)
//                    vie.findViewById<Chip>(R.id.action_chip).setText("Search Recipes")
//                    tab.setCustomView(vie)
                }
                1 -> {
                    tab.setText("Search By Nutrition")
//                    val vie=View.inflate(context,R.layout.tabview,null)
//                    vie.findViewById<Chip>(R.id.action_chip).setText("Search By Nutrition")
////                    vie.findViewById<Chip>(R.id.action_chip).setOnClickListener(View.OnClickListener {  })
//                    tab.setCustomView(vie)
                }
                2 -> {
                     tab.setText("Search By Ingredients")
//                    val vie=View.inflate(context,R.layout.tabview,null)
//                    vie.findViewById<Chip>(R.id.action_chip).setText("Search By Ingredients")
//                    tab.setCustomView(vie)
                }
                3 -> {
                    tab.setText("Get Recipe Nutrition")
//                    val vie=View.inflate(context,R.layout.tabview,null)
//                    vie.findViewById<Chip>(R.id.action_chip).setText("Get Recipe Nutrition")
//                    tab.setCustomView(vie)
                }
            }


        }).attach()
        return view
    }

//   @Suppress("UNREACHABLE_CODE")
   private inner class ProductsAdapter(childFragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(childFragmentManager, lifecycle) {


       override fun createFragment(position: Int): Fragment {

           when (position) {
               0 -> return SearchRecipes()
               1 -> return SearchRecipesByNutrients()
               2 -> return  SearchRecipesByIngredients()
               3 ->return GetRecipeNutrition()
               //4 -> fragment = ViewPagerFragment()
               else->return SearchRecipes()
           }


       }
       override fun getItemCount(): Int {
           return 4

       }

   }

}

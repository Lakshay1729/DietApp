package com.example.dietary


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.dietary.Fragments.*
import com.example.dietary.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class BaseActivity : AppCompatActivity() {
    var fragment1: Fragment = Fragment()
    var fragment2: Fragment= Fragment()
    var fragment3: Fragment = Fragment()
    var fragment4:Fragment= Fragment()
    var fragment5:Fragment= Fragment()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
         fragment1  = Recipe()
         fragment2  = Ingredients()
         fragment3  = Products()
         fragment4=   MenuItems()
         fragment5= MealPlanning()
        loadFragment(fragment1)
       // var active = fragment1
        //val fm: FragmentManager = supportFragmentManager
        var bottomnavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

//        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
//        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
//        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();

bottomnavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
    var mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener=BottomNavigationView.OnNavigationItemSelectedListener() {
        when (it.itemId){
            R.id.recipe -> {
                loadFragment(fragment1)
                Toast.makeText(applicationContext,"fragment1",Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true;
            }
            R.id.ingredients -> {
                loadFragment(fragment2)
                Toast.makeText(applicationContext,"fragment2",Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true;
            }
            R.id.product -> {
                loadFragment(fragment3)
                Toast.makeText(applicationContext,"fragment3",Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true;
            }
            R.id.menu_items ->{
                loadFragment(fragment4)
                Toast.makeText(applicationContext,"fragment4",Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true

            }
            R.id.meal_planning ->{
                loadFragment(fragment5)
                Toast.makeText(applicationContext,"fragment5",Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true
            }

            else -> {
                return@OnNavigationItemSelectedListener false
            }
        }
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, fragment).addToBackStack("fragments")
                    .commit()
            supportFragmentManager.executePendingTransactions()

            return true
        }
        return false
    }
}

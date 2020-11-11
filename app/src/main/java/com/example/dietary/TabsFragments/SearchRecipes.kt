package com.example.dietary.TabsFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.bumptech.glide.Glide
import com.example.dietary.R
import com.example.dietary.ViewModels.RecipeViewModel
import com.example.dietary.databinding.FragmentBlankBinding
import com.example.dietary.retrofit.Repository.SearchRepository
import com.example.dietary.retrofit.Service.SpoonacularService
import com.example.dietary.retrofit.data.Recipes.results
import com.google.android.material.textview.MaterialTextView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
//import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_blank.view.*
import kotlinx.android.synthetic.main.search_recipe_item.view.*
import java.util.concurrent.TimeUnit


class SearchRecipes : Fragment() {
    private lateinit var viewModel: RecipeViewModel
    private var layoutManager: LinearLayoutManager? = null
    private var paginationAdapter: PaginationAdapter? = null
    private val progressBar: ProgressBar? = null
    private  var pageNumber:Int = 0
    private val VISIBLE_THRESHOLD = 1
    private var lastVisibleItem = 0
    private  var totalItemCount:Int = 0
    private var loading: Boolean=false
    private val paginator: PublishProcessor<results> = PublishProcessor.create()
    private var _binding: FragmentBlankBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
//    val TAG: String? = PaginationActivity::class.java.getSimpleName()
    private val compositeDisposable = CompositeDisposable()

    lateinit var recyclerView:RecyclerView
    private val binding get() = _binding!!
    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding=FragmentBlankBinding.inflate(inflater, container, false)
        val view:View=binding.root
        val repository = SearchRepository(SpoonacularService.create())
        recyclerView=binding.recyclerViewSearch
        layoutManager = LinearLayoutManager(context);
        layoutManager?.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
      viewModel=ViewModelProviders.of(this).get("recipeviewmodel",RecipeViewModel::class.java)

        view.searchButton.setOnClickListener(View.OnClickListener {
            viewModel.getAllRecipes(view.query.text.toString(), Integer.parseInt(view.maxFat.text.toString()), Integer.parseInt(view.number.text.toString()))?.observe(this, Observer(Funct) )
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeOn(Schedulers.io())
                    ?.subscribe({ result ->
                        paginationAdapter = PaginationAdapter()
                        paginationAdapter!!.addItems(result?.results)
                        recyclerView.adapter = paginationAdapter
//                        setUpLoadMoreListener()
//                        subscribeForData()
                        Log.d("Result1", "There are ${result?.toString()} ")
//                        Toast.makeText(context, result?.results?.get(0)?.title, Toast.LENGTH_LONG).show()
//                        Glide.with(view.context).load(result?.results?.get(0)?.image).into(view.image)
//                        view.recipe_name.setText(result?.results?.get(0)?.title)
//                        view.recipe_nutrition.setText(result?.results?.get(0)?.nutrition?.nutrients?.get(0)?.title + ": " + "${result?.results?.get(0)?.nutrition?.nutrients?.get(0)?.amount}g ")

                    }, { error ->
                        Log.d("Error", error.printStackTrace().toString())
                        error.printStackTrace()
                    })
        })
        return view
    }

//    private fun setUpLoadMoreListener() {
//        recyclerView.addOnScrollListener(object : OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                totalItemCount = layoutManager!!.getItemCount()
//                lastVisibleItem = layoutManager!!.findLastVisibleItemPosition()
//
//                if (!loading && totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD) {
//
//                    paginator.onNext()
//
//                    loading = true
//                }
//            }
//        })
//    }
//    private fun subscribeForData() {
//        val disposable: Disposable = paginator
//                .onBackpressureDrop()
//                .doOnNext { page ->
//                    loading = true
//                    progressBar?.setVisibility(View.VISIBLE)
//                }
//                .concatMapSingle { page ->
//                    dataFromNetwork(page)?.subscribeOn(Schedulers.io())?.doOnError { throwable -> } !!.onErrorReturn { throwable -> ArrayList() }
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { items ->
//                    paginationAdapter?.notifyDataSetChanged();
//                    loading = false;
//                    progressBar?.setVisibility(View.INVISIBLE);
//                }
//        val add = compositeDisposable.add(disposable)
//      //  paginator.onNext(pageNumber)
//    }


    class PaginationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var items: MutableList<results> = ArrayList()
        fun addItems(items: List<results>?) {
            this.items.addAll(items!!)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ItemViewHolder.create(parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as ItemViewHolder).bind(items.get(position))
        }

        override fun getItemCount(): Int {
            return items.size
        }

        private class ItemViewHolder
        constructor(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

            fun bind(content: results?) {

                Glide.with(itemView.context).load(content?.image).into(itemView.findViewById<AppCompatImageView>(R.id.image))
                itemView.findViewById<MaterialTextView>(R.id.recipe_name).setText(content?.title)
                itemView.findViewById<MaterialTextView>(R.id.recipe_nutrition).setText(content?.nutrition?.nutrients?.toString())
               // itemView.findViewById<AppCompatImageView>(R.id.image).setImageResource(content.image)
                //(itemView.).text = content
            }

            companion object {
                fun create(parent: ViewGroup): ItemViewHolder {
                    return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_recipe_item, parent, false))
                }
            }
        }
    }

    private fun dataFromNetwork(page: Int): Single<List<results?>?>? {
        return Single.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map { value ->
                    val items: List<results> = ArrayList()
                    for (i in 1..10) {
                        items.plus("Item " + (page * 10 + i))
                    }
                    items
                }
    }

}




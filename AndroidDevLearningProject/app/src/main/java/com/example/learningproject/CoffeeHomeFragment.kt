package com.example.learningproject

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.FragmentCoffeeHomeBinding


class CoffeeHomeFragment : Fragment() {

    private lateinit var binding: FragmentCoffeeHomeBinding
    val viewModel: CategoryCoffeeListCommunicationVM by activityViewModels()
    val categoryDecoration = object: RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = 0
            outRect.bottom = 0
            outRect.left = 16
            outRect.right = 16
        }
    }
    val coffeeItemDecoration = object: RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = 15
            outRect.bottom = 15
            outRect.left = 15
            outRect.right = 15
        }
    }
    private lateinit var adapterList: CoffeeListingAdapter
    private lateinit var adapterCat: CoffeeCategoryAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoffeeHomeBinding.inflate(inflater, container, false)
        adapterCat = CoffeeCategoryAdapter(
            coffeeCategories,
            viewModel::updateSelectedCategory,
            viewModel::getSelectedCategory,
            requireActivity()
        )
        binding.rvCoffeeCategories.apply {
            adapter = adapterCat
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            addItemDecoration(categoryDecoration)
        }

        adapterList = CoffeeListingAdapter(
            coffeeDetails,
            viewModel::getSelectedCategory,
            viewModel::updateSelectedCoffee,
            requireActivity()
        )
        binding.rvCoffeeList.apply {
            adapter = adapterList
            layoutManager = GridLayoutManager(activity,2)
            addItemDecoration(coffeeItemDecoration)
        }

        viewModel.selectedCategory.observe(requireActivity(), {
            adapterCat.notifyDataSetChanged()
            adapterList.notifyDataSetChanged()
        })

        setSpinner()
        binding.main.setOnTouchListener { v, event ->
            val view: View? = requireActivity().currentFocus
            if (view != null) {
                view.clearFocus()
                val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            true
        }
        return binding.root
    }
    private fun setSpinner() {
        val listLocations = listOf("Ahmedabad", "Surat", "Rajkot")
        val adapter = ArrayAdapter(
            requireActivity(),
            R.layout.coffee_location_dropdown,
            listLocations
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLocation.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapterList.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("categoryChanged", "onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("categoryChanged", "onDestroy")
    }
}
package com.example.android.lazyengineer.syllabusPolytechnic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.lazyengineer.R
import com.example.android.lazyengineer.databinding.FragmentPolytechnicSyllabusBinding

class PolytechnicSyllabusFragment : Fragment() {

    private lateinit var viewModel: PolytechnicSyllabusViewModel
    private lateinit var binding: FragmentPolytechnicSyllabusBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPolytechnicSyllabusBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(PolytechnicSyllabusViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

}
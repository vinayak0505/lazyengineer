package com.example.android.lazyengineer.ui.syllabus

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.lazyengineer.R
import com.example.android.lazyengineer.databinding.FragmentSyllabusBTechBinding

class SyllabusBTechFragment : Fragment() {


    private lateinit var viewModel: SyllabusBTechViewModel
    private lateinit var binding: FragmentSyllabusBTechBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSyllabusBTechBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(SyllabusBTechViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.syllabusRv.adapter = SyllabusListAdapter()


        return binding.root
    }

}
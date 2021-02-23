package com.example.android.lazyengineer.ui.startingPage

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android.lazyengineer.BoxClickListener
import com.example.android.lazyengineer.BoxComponents
import com.example.android.lazyengineer.ContextBoxAdapter
import com.example.android.lazyengineer.jobsAlert.JobsAlertActivity
import com.example.android.lazyengineer.Notes.Notes
import com.example.android.lazyengineer.R
import com.example.android.lazyengineer.databinding.FragmentStartingBinding

class StartingFragment : Fragment(), BoxClickListener {

    private lateinit var viewModel: StartingViewModel
    private lateinit var binding: FragmentStartingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentStartingBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(StartingViewModel::class.java)
        binding.viewModel = viewModel
        recyclerView()

        return binding.root
    }

    override fun onBoxClickListener(title: String) {
        when (title) {
            "Jobs" -> {
                startActivity(Intent(context, JobsAlertActivity::class.java))
            }
            "Notes" -> {
                startActivity(Intent(context, Notes::class.java))
            }
            "Notice" -> {
                this.findNavController().navigate(R.id.action_startingFragment_to_newsFragment)
            }
            "Internship" -> {
                Toast.makeText(context, "under maintenance", Toast.LENGTH_SHORT).show()
            }
            "Scholarship" -> {
                Toast.makeText(context, "under maintenance", Toast.LENGTH_SHORT).show()
            }
            "SyllabusBTech" -> {
                this.findNavController().navigate(R.id.action_startingFragment_to_syllabusBTechFragment)
            }
        }
    }


    private fun recyclerView() {
        val boxes: ArrayList<BoxComponents> = ArrayList()
        boxes.add(BoxComponents(R.color.white, R.color.blue, "Jobs", null, R.drawable.jobs_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Notice", null, R.drawable.notices_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Internship", null, R.drawable.internship_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Scholarship", null, R.drawable.scholarship))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Notes", null, R.drawable.notes_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "SyllabusBTech", null, R.drawable.notes_image))
        val adapter = ContextBoxAdapter(boxes, this)
        binding.mainRv.setHasFixedSize(true)
        binding.mainRv.adapter = adapter
    }
}


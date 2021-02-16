package com.example.android.lazyengineer

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.lazyengineer.JobsAlert.JobsAlertActivity
import com.example.android.lazyengineer.Notes.Notes
import com.example.android.lazyengineer.databinding.ActivityMainBinding
import com.example.android.lazyengineer.news.NewsActivity

class MainActivity : AppCompatActivity(), BoxClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //recyclerView
        recyclerView()

    }

    fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.main_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_logout -> {
                    logout(); true
                }
                R.id.menu_feedback -> {
                    feedback(); true
                }
                R.id.menu_website -> {
                    website(); true
                }
                else -> false
            }
        }
        popup.show()
    }

    @SuppressLint("ApplySharedPref")
    private fun logout() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPref.edit().putString(getString(R.string.logout), getString(R.string.logout)).commit()
        startActivity(Intent(this, LoadingScreen::class.java))
        finish()
    }

    private fun recyclerView() {
        val boxes: ArrayList<BoxComponents> = ArrayList()
        boxes.add(BoxComponents(R.color.white, R.color.blue, "Jobs", null, R.drawable.jobs_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Notice", null, R.drawable.notices_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Internship", null, R.drawable.internship_image))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Scholarship", null, R.drawable.scholarship))
        boxes.add(BoxComponents(R.color.black, R.color.dark_gray, "Notes", null, R.drawable.notes_image))
        val adapter = ContextBoxAdapter(boxes, this)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, 1)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    override fun onBoxClickListener(title: String) {
        when (title) {
            "Jobs" -> {
                startActivity(Intent(baseContext, JobsAlertActivity::class.java))
            }
            "Notes" -> {
                startActivity(Intent(baseContext, Notes::class.java))
            }
            "Notice" -> {
                startActivity(Intent(baseContext, NewsActivity::class.java))
            }
            "Internship" -> {
                Toast.makeText(this, "under maintenance", Toast.LENGTH_SHORT).show()
            }
            "Scholarship" -> {
                Toast.makeText(this, "under maintenance", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun feedback() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            data = Uri.parse("mailto:${getString(R.string.email_link)}")
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun website() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(getString(R.string.website_link))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun changeAvatar(view: View) {
        binding.profile.setImageResource(R.drawable.profile_male)
    }

}
package com.deonolarewaju.mobile_ui.bookmarked

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.deonolarewaju.mobile_ui.R
import com.deonolarewaju.mobile_ui.injection.ViewModelFactory
import com.deonolarewaju.mobile_ui.mapper.ProjectViewMapper
import com.deonolarewaju.mobile_ui.model.Project
import com.deonolarewaju.presentation.BrowseBookmarkedViewModel
import com.deonolarewaju.presentation.model.ProjectView
import com.deonolarewaju.presentation.state.Resource
import com.deonolarewaju.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_bookmarked.*
import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BookmarkedAdapter

    @Inject
    lateinit var mapper: ProjectViewMapper

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var browseViewModel: BrowseBookmarkedViewModel

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseBookmarkedViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this, Observer<Resource<List<ProjectView>>> {
            it?.let {
                handleDataState(it)
            }

        })
        browseViewModel.fetchProjects()
    }

    private fun setupBrowseRecycler() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
        recycler_projects.adapter = adapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
//                setupScreenForSuccess(resource.data?.map {
//                    mapper.mapToView(it)
//                })
                progress.visibility = View.GONE
                recycler_projects.visibility = View.VISIBLE

                resource.data?.let {
                    adapter.projects = it.map {
                        mapper.mapToView(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<Project>?) {
        projects?.let {
            adapter.projects = it
            adapter.notifyDataSetChanged()
        } ?: run {

        }

    }
}
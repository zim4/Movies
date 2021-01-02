package com.adzmf.thelistofmovies.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adzmf.thelistofmovies.core.ui.MovieAdapter
import com.adzmf.thelistofmovies.detail.DetailActivity
import com.adzmf.thelistofmovies.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        binding.btnClose.setOnClickListener { finish() }

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.pbFavorite.visibility = View.VISIBLE
        favoriteViewModel.favoriteMovies.observe(this, { favoriteMovies ->
            if (favoriteMovies.isNotEmpty() && favoriteMovies != null) {
                movieAdapter.setData(favoriteMovies)
                binding.rvMovies.visibility = View.VISIBLE
                binding.viewEmpty.visibility = View.GONE
            } else {
                binding.rvMovies.visibility = View.GONE
                binding.viewEmpty.visibility = View.VISIBLE
            }
            binding.pbFavorite.visibility = View.GONE
        })

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }


    }

}
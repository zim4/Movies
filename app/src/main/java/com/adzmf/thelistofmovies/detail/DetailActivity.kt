package com.adzmf.thelistofmovies.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adzmf.thelistofmovies.R
import com.adzmf.thelistofmovies.core.data.Resource
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.model.MovieDetail
import com.adzmf.thelistofmovies.core.utils.Utils
import com.adzmf.thelistofmovies.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent?.getParcelableExtra<Movie>(EXTRA_DATA)
        if (data != null) {
            showDetail(data)
            detailViewModel.favoriteMoviesById(data.id).observe(this, { favoriteMovies ->
                val isFavorite = favoriteMovies != null && favoriteMovies.isNotEmpty()
                setStatusFavorite(isFavorite)
                binding.btnFavorite.setOnClickListener {
                    detailViewModel.setFavoriteMovie(data, !isFavorite)
                }
            })
        }

    }

    private fun showDetail(data: Movie) {
        detailViewModel.movieDetails(data.id).observe(this, { movieDetails ->
            if (movieDetails.data != null && movieDetails.data?.first() != null) {
                val movieDetail = movieDetails.data?.first() as MovieDetail
                when (movieDetails) {
                    is Resource.Loading -> {
                        binding.root.visibility = View.GONE
                        binding.pbDetail.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {

                        with(binding) {
                            tvTitle.text = movieDetail.title
                            tvOverview.text = movieDetail.overview
                            tvGenre.text = movieDetail.genres
                            rating.rating = (movieDetail.voteAverage / 2.0).toFloat()

                            tvLanguage.text = Utils.codeToCountry(movieDetail.originalLanguage)
                            tvReleaseDate.text = Utils.dateFormatter(this@DetailActivity, movieDetail.releaseDate)
                            tvRuntime.text = Utils.minuteToHM(movieDetail.runtime)

                            imgBroken.visibility = View.GONE
                            pbPoster.visibility = View.VISIBLE
                            if (movieDetail.posterPath == "-") {
                                imgBroken.visibility = View.VISIBLE
                                pbPoster.visibility = View.GONE
                            }
                            Glide.with(this@DetailActivity)
                                .load("https://image.tmdb.org/t/p/w500${movieDetail.posterPath}")
                                .into(imgPoster)

                            Glide.with(this@DetailActivity)
                                .load("https://image.tmdb.org/t/p/w500${movieDetail.backdropPath}")
                                .into(imgBackdrop)

                        }
                        binding.root.visibility = View.VISIBLE
                        binding.pbDetail.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.pbDetail.visibility = View.GONE
                        binding.viewError.visibility = View.VISIBLE
                        binding.viewError.text = getString(R.string.something_wrong)
                    }
                }
            }
        })

    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        with(binding.btnFavorite) {
            text = if (statusFavorite) {
                setBackgroundColor(ContextCompat.getColor(this@DetailActivity, R.color.gold_2))
                setTextColor(ContextCompat.getColor(this@DetailActivity, R.color.blue_1))
                resources.getString(R.string.remove_to_favorites)
            } else {
                setBackgroundColor(ContextCompat.getColor(this@DetailActivity, R.color.blue_1))
                setTextColor(ContextCompat.getColor(this@DetailActivity, R.color.gold_2))
                resources.getString(R.string.add_to_favorites)
            }
        }
    }

}
package com.adzmf.thelistofmovies.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.adzmf.thelistofmovies.R
import com.adzmf.thelistofmovies.core.data.Resource
import com.adzmf.thelistofmovies.core.ui.MovieAdapter
import com.adzmf.thelistofmovies.databinding.FragmentHomeBinding
import com.adzmf.thelistofmovies.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            binding.btnToSearch.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_homeFragment_to_searchFragment)
            }
            binding.btnToFavorite.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_homeFragment_to_favoriteActivity)
            }

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData) //selectedData.id)
                startActivity(intent)
            }

            homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> binding.pbHome.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbHome.visibility = View.GONE
                            movieAdapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            binding.pbHome.visibility = View.GONE
                            binding.viewError.visibility = View.VISIBLE
                            binding.viewError.text = getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
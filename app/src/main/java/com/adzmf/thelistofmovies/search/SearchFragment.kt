package com.adzmf.thelistofmovies.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adzmf.thelistofmovies.core.ui.MovieAdapter
import com.adzmf.thelistofmovies.databinding.FragmentSearchBinding
import com.adzmf.thelistofmovies.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            setClearBtn(binding.btnClear, binding.edtSearch.text.isEmpty())
            binding.edtSearch.requestFocus()

            binding.edtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val query = p0?.toString() ?: ""
                    setClearBtn(binding.btnClear, query.isEmpty())
                    lifecycleScope.launch { searchViewModel.queryChannel.send(query) }
                }

            })

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            searchViewModel.searchResult.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    if (movies.isNotEmpty()) {
                        movieAdapter.setData(movies)
                        binding.rvMovies.visibility = View.VISIBLE
                        binding.viewEmpty.visibility = View.GONE
                    } else {
                        binding.rvMovies.visibility = View.GONE
                        binding.viewEmpty.visibility = View.VISIBLE
                    }
                }
                binding.pbSearch.visibility = View.GONE
            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            binding.btnClear.setOnClickListener {
                binding.edtSearch.setText("")
            }

        }

    }

    private fun setClearBtn(imgBtn: ImageButton, isEmpty: Boolean) {
        imgBtn.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
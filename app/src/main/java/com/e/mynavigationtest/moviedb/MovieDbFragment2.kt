package com.e.mymovieskotlin.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.mymovieskotlin.domain.Movie
import com.e.mymovieskotlin.repository.MoviesRepository
import com.e.mynavigationtest.R

/**
 * A simple [Fragment] subclass.
 */
class MovieDbFragment2 : Fragment() {

    private var refresh: View? = null
    private var recycler: RecyclerView? = null




    private val viewModel: MovieDbViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, MovieDbViewModelFactory(activity.application))
            .get(MovieDbViewModel::class.java)
    }


/*
    val viewModelFactory = MovieDbViewModelFactory(activity!!.application)
    private val viewModel: MovieDbViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MovieDbViewModel::class.java)

    }
*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_db2, container, false)

        val save = view.findViewById<View>(R.id.fragment_movie_db_save)
        val cancel = view.findViewById<View>(R.id.fragment_movie_db_cancel)
        refresh = view.findViewById<View>(R.id.fragment_movie_db_refresh)

        val adapter = MovieDbAdapter(object: MovieDbAdapter.ItemClickListener{
            override fun onClicked(movie: Movie) {
                viewModel.movieClicked(movie)
            }

        })
        recycler = view.findViewById<RecyclerView>(R.id.fragment_movie_db_recycler)
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(context)

        viewModel.movies.observe(viewLifecycleOwner, Observer { lst ->
            lst?.let {
                adapter.data = lst
            }
        })

        cancel.setOnClickListener(View.OnClickListener {
            // getFragmentManager().popBackStack()
            view.findNavController().popBackStack()
        })

        refresh?.setOnClickListener(View.OnClickListener {
            viewModel.refreshMovieDbData()
        })

        return view
    }

}

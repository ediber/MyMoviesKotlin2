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
import com.e.mymovieskotlin.repository.TOP_RATED
import com.e.mymovieskotlin.repository.UPCOMING
import com.e.mynavigationtest.R
import kotlinx.android.synthetic.main.fragment_movie_db2.*

/**
 * A simple [Fragment] subclass.
 */
class MovieDbFragment2 : Fragment() {


/*    private var show_upcoming: View? = null
    private var show_top_rated: View? = null
    private var refresh_top_rated: View? = null
    private var refresh_upcoming: View? = null
    private var recycler: RecyclerView? = null*/




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

/*        val save = view.findViewById<View>(R.id.fragment_movie_db_save)
        val cancel = view.findViewById<View>(R.id.fragment_movie_db_cancel)
        refresh_top_rated = view.findViewById<View>(R.id.fragment_movie_db_refresh_top_rated)
        refresh_upcoming = view.findViewById<View>(R.id.fragment_movie_db_refresh_upcoming)
        show_top_rated = view.findViewById<View>(R.id.fragment_movie_db_show_top_rated)
        show_upcoming = view.findViewById<View>(R.id.fragment_movie_db_show_upcoming)*/



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val adapter = MovieDbAdapter(object: MovieDbAdapter.ItemClickListener{
            override fun onClicked(movie: Movie) {
                viewModel.movieClicked(movie)
            }

        })
        fragment_movie_db_recycler?.adapter = adapter
        fragment_movie_db_recycler?.layoutManager = LinearLayoutManager(context)

        viewModel.movies.observe(viewLifecycleOwner, Observer { lst ->
            lst?.let {
                adapter.data = lst
            }
        })

        fragment_movie_db_cancel.setOnClickListener(View.OnClickListener {
            // getFragmentManager().popBackStack()
            view.findNavController().popBackStack()
        })

        fragment_movie_db_show_top_rated?.setOnClickListener(View.OnClickListener {
                viewModel.switchToTopRated()
        })

        fragment_movie_db_show_upcoming?.setOnClickListener(View.OnClickListener {
            viewModel.switchToUpcoming()
        })

        fragment_movie_db_show_all.setOnClickListener(View.OnClickListener {
            viewModel.switchToAll()
        })

        fragment_movie_db_refresh_top_rated.setOnClickListener(View.OnClickListener {
            viewModel.refreshMovieDbData(TOP_RATED)
        })

        fragment_movie_db_refresh_upcoming?.setOnClickListener(View.OnClickListener {
            viewModel.refreshMovieDbData(UPCOMING)
        })

    }

}

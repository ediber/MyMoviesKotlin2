package com.e.mymovieskotlin.selected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.mymovieskotlin.domain.Movie
import com.e.mynavigationtest.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectedFragment : Fragment() {


    private val viewModel: SelectedViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, SelectedViewModelFactory(activity.application))
            .get(SelectedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_selected_temp, container, false)

        view.findViewById<View>(R.id.selected_open_moviedb).setOnClickListener(View.OnClickListener {
            view.findNavController().navigate(SelectedFragmentDirections.actionSelectedFragmentToMovieDbFragment2())
        })

        val adapter = SelectedAdapter(object : SelectedAdapter.ItemClickListener{
            override fun onClicked(movie: Movie) {
                view.findNavController().navigate(SelectedFragmentDirections.actionSelectedFragmentToDetailsFragment(movie.posterPath))

//                val action = SelectedFragmentDirections.actionSelectedFragmentToDetailsFragment(2)
//                NavHostFragment.findNavController(parentFragment!!).navigate(action)

            }
        })

        val recycler = view.findViewById<RecyclerView>(R.id.selected_recycler)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        viewModel.selectedMovies.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })

        return view
    }

}

package com.e.mymovieskotlin.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.e.mynavigationtest.R


class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var image: ImageView
    private var posterPath: String = "-1"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)

        // Get args using by navArgs property delegate
        val scoreFragmentArgs by navArgs<DetailsFragmentArgs>()
        posterPath = scoreFragmentArgs.posterPath


        title = view.findViewById<TextView>(R.id.details_title)
        overview = view.findViewById<TextView>(R.id.details_overview)
        image = view.findViewById<ImageView>(R.id.details_image)

        return view
    }

    // happens after onCreateView
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        viewModel.posterPath = posterPath
        viewModel.getMovieById(posterPath)

      //  Thread.sleep(1000)

        viewModel.title.observe(viewLifecycleOwner, Observer {
            title.text = it
        })

      /*  viewModel.overview.observe(viewLifecycleOwner, Observer {
            overview.text = it
        })

        viewModel.posterPath.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it)
                .into(image)
        })*/
    }

}

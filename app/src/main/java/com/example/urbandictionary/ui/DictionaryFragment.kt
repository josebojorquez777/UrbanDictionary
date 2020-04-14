package com.example.urbandictionary.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.urbandictionary.DictionaryApplication
import com.example.urbandictionary.R
import com.example.urbandictionary.databinding.FragmentDictionaryBinding
import com.example.urbandictionary.viewmodel.DictionaryViewModel
import com.example.urbandictionary.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * This Dictionary Fragment is used to display all of the relevant ui for the user.
 */
class DictionaryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: DictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * Set the view model for data binding and lifecycle observation.
         */
        (context?.applicationContext as DictionaryApplication).appComponent.inject(this)
        val binding = FragmentDictionaryBinding.inflate(inflater)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DictionaryViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    /**
     * Set up the action bar to display icons.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Set up the action bar click actions.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.sortThumbsUp -> viewModel.sortByThumbsUp()
            R.id.sortThumbsDown -> viewModel.sortByThumbsDown()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * DictionaryFragment.
         */
        @JvmStatic
        fun newInstance() =
            DictionaryFragment()
    }
}

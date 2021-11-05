package ca.tetervak.flowerdata.ui.list.search

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.flowerdata.R
import ca.tetervak.flowerdata.databinding.SearchFragmentBinding
import ca.tetervak.flowerdata.ui.list.FlowerListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment()  {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchFragmentBinding.inflate(inflater)

        navController = findNavController()

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.adapter = FlowerListAdapter { flower ->
            val action = SearchFragmentDirections.actionSearchToDetails(flower.id)
            navController.navigate(action)
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.priceMarginSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val margins = resources.getIntArray(R.array.price_margin_values)
                viewModel.setPriceMargin(margins[position].toFloat())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.refresh()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
package ca.tetervak.flowerdata.ui.list.catalog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.flowerdata.R
import ca.tetervak.flowerdata.databinding.CatalogFragmentBinding
import ca.tetervak.flowerdata.ui.dialogs.isErrorDialogShown
import ca.tetervak.flowerdata.ui.dialogs.showErrorDialog
import ca.tetervak.flowerdata.ui.list.FlowerListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CatalogFragmentBinding.inflate(inflater)

        navController = findNavController()

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.adapter = FlowerListAdapter { flower ->
            val action = CatalogFragmentDirections.actionCatalogFragmentToDetailsFragment(flower.id)
            navController.navigate(action)
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        viewModel.status.observe(viewLifecycleOwner){ status ->

            binding.swipeRefresh.isRefreshing =
                status == CatalogViewModel.Status.REFRESHING

            if(status == CatalogViewModel.Status.ERROR){
                if(!isErrorDialogShown()){
                    showErrorDialog(
                        title = getString(R.string.app_name),
                        message = getString(R.string.cannot_load_the_data)
                    )
                    viewModel.reset()
                }
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.catalog, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.refresh()
                true
            }
            R.id.action_clear ->{
                viewModel.clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
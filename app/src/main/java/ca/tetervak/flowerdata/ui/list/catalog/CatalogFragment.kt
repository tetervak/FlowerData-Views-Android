package ca.tetervak.flowerdata.ui.list.catalog

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.flowerdata.R
import ca.tetervak.flowerdata.databinding.CatalogFragmentBinding
import ca.tetervak.flowerdata.ui.dialogs.showInfoDialog
import ca.tetervak.flowerdata.ui.list.FlowerListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment(), MenuProvider {

    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CatalogFragmentBinding.inflate(inflater)

        navController = findNavController()

        // setup fragment menu
        requireActivity().addMenuProvider(
            this, viewLifecycleOwner, Lifecycle.State.RESUMED
        )

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
        viewModel.liveStatus.observe(viewLifecycleOwner) { status ->

            binding.swipeRefresh.isRefreshing =
                status == CatalogViewModel.Status.LOADING

            if (status == CatalogViewModel.Status.ERROR) {
                showInfoDialog(
                    title = getString(R.string.app_name),
                    message = getString(R.string.cannot_load_the_data)
                )
                viewModel.clearError()
            }
        }

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.catalog, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_refresh -> {
                viewModel.refresh()
                true
            }
            R.id.action_clear -> {
                viewModel.clearLocalData()
                true
            }
            else -> false
        }
    }

}
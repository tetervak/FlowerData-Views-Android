package ca.tetervak.flowerdata.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration

import ca.tetervak.flowerdata.databinding.FlowerListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowerListFragment : Fragment() {

    private val viewModel: FlowerListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FlowerListFragmentBinding.inflate(inflater)

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.adapter = FlowerListAdapter()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }


}
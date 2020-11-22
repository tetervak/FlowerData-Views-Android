package ca.tetervak.flowerdata.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ca.tetervak.flowerdata.databinding.FlowerDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowerDetailsFragment : Fragment() {

    private val viewModel: FlowerDetailsViewModel by viewModels()
    private val safeArgs: FlowerDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FlowerDetailsFragmentBinding.inflate(inflater)

        viewModel.loadData(safeArgs.flowerId)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }


}
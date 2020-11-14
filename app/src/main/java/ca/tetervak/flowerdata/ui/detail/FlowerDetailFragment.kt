package ca.tetervak.flowerdata.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.tetervak.flowerdata.R

class FlowerDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FlowerDetailFragment()
    }

    private lateinit var viewModel: FlowerDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flower_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FlowerDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
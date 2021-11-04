package ca.tetervak.flowerdata.ui.wikipedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import ca.tetervak.flowerdata.databinding.WikiFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WikiFragment : Fragment() {

    private val viewModel: WikiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = WikiFragmentBinding.inflate(layoutInflater)

        binding.webview.webViewClient = WebViewClient()
        viewModel.flower.observe(viewLifecycleOwner){ flower ->
            binding.webview.loadUrl(flower.wikiUrl)
        }

        return binding.root
    }

}
package ca.tetervak.flowerdata.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.flowerdata.domain.Flower
import ca.tetervak.flowerdata.ui.list.FlowerListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<Flower>?) {
    val adapter = recyclerView.adapter as FlowerListAdapter
    adapter.submitList(list)
}

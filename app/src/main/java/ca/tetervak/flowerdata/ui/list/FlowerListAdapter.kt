package ca.tetervak.flowerdata.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.flowerdata.databinding.FlowerListItemBinding
import ca.tetervak.flowerdata.domain.Flower

class FlowerListAdapter (
    private val onClick: (Flower) -> Unit
): ListAdapter<Flower, FlowerListAdapter.ViewHolder>(FlowerDiffCallback()){

    inner class ViewHolder(
        private val binding: FlowerListItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(flower: Flower){
            binding.flower = flower
            binding.root.setOnClickListener{ onClick(flower) }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FlowerListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FlowerDiffCallback : DiffUtil.ItemCallback<Flower>() {
        override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem == newItem
        }
    }
}
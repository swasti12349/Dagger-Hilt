package com.sro.daggerhilt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sro.daggerhilt.databinding.EachRowBinding

class PostAdapter(private var list: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        binding.tasks.text = list[position].body
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemCount(): Int {
        return list.size
    }


     fun setList(list: List<Post>) {
        this.list = list
        notifyDataSetChanged()
    }
}
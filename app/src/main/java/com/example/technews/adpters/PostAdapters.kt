package com.example.technews.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technews.R
import com.example.technews.pojo.PostData
import com.example.technews.ui.DetailActivity

class PostAdapters : RecyclerView.Adapter<PostAdapters.PostViewHolder>() {

    private val list = mutableListOf<PostData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount() = list.size

    fun setUpObserver(response: MutableLiveData<List<PostData>>) {
        response.observeForever {
            list.clear()
            list.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val imageView = itemView.findViewById(R.id.imageView) as ImageView?
        private val titleTv = itemView.findViewById(R.id.titleTv) as TextView?
        private val descTv = itemView.findViewById(R.id.descTv) as TextView?

        init {
            itemView.findViewById<View?>(R.id.mainLayout)?.setOnClickListener(this)
        }

        fun bindView(postData: PostData) {
            imageView?.let {
                Glide.with(it.context)
                    .load(postData.imageUrl)
                    .into(it)
            }

            titleTv?.text = postData.title
            descTv?.text = postData.content
        }

        override fun onClick(v: View?) {
            v?.context?.let { DetailActivity.start(it, list[adapterPosition]) }
        }

    }
}
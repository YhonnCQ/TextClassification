package com.example.textclassification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.textclassification.databinding.ItemMessageBinding

class MessageAdapter(
    private val messages: MutableList<Message>,
    private val itemClickListener: (Message) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<Message>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Message> {
        val itemBinding =  ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Message>, position: Int) {
        when (holder) {
            is MessageViewHolder -> holder.bind(messages[position])
        }
    }

    override fun getItemCount(): Int = messages.size

    fun addMessages(messages: List<Message>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }

    inner class MessageViewHolder(private val binding: ItemMessageBinding) : BaseViewHolder<Message>(binding.root) {
        override fun bind(item: Message) {
            binding.textViewUsername.text = item.username
            binding.textViewMessage.text = item.text
            binding.cardViewMessage.setOnClickListener {
                itemClickListener(item)
            }
        }
    }
}
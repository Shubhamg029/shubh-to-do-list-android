package com.shubham.todolist.recyclerView


interface RecyclerItemClickListener {
  fun onItemClick(position: Int, item: BaseRecyclerViewItem?, actionType: Int)
}
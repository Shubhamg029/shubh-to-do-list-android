package com.shubham.todolist.constant

import androidx.annotation.LayoutRes
import com.shubham.todolist.R

enum class RecyclerViewItemType {
  PAGINATION_LOADER,
  TASK_LIST_ITEM;


  @LayoutRes
  fun getLayout(): Int {
    return when (this) {
      PAGINATION_LOADER -> R.layout.pagination_loader
      TASK_LIST_ITEM -> R.layout.item_task
    }
  }
}

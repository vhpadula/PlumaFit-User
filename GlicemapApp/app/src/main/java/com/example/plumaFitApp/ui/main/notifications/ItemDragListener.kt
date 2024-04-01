package com.example.contadormtg.extracountersrv

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener {
   fun onItemDrag(viewHolder: RecyclerView.ViewHolder)
   fun onItemSwipe(viewHolder: RecyclerView.ViewHolder)
}
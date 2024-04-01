package com.example.plumaFitApp.ui.main.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contadormtg.extracountersrv.ItemDragListener
import com.example.contadormtg.extracountersrv.ItemTouchHelperCallback
import com.example.plumaFitApp.ui.base.ToolbarFragment
import com.example.plumaFitApp.databinding.FragmentNotificationsBinding

class NotificationsFragment : ToolbarFragment(), ItemDragListener {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var itemTouchHelper: ItemTouchHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NotificationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(requireActivity()).get(NotificationsViewModel::class.java)
        notificationsViewModel.activity=requireActivity()
        notificationsViewModel.setValues()

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setAdapter()
        setupItemTouchHelper()

        binding.newExerciseButton.setOnClickListener {
            findNavController().navigate(com.example.plumaFitApp.ui.main.notifications.NotificationsFragmentDirections.toNewNotification())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemSwipe(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startSwipe(viewHolder)
    }

    private fun setAdapter(){
        adapter = NotificationsAdapter(context = requireContext(), this)
        adapter.items = notificationsViewModel.items
        binding.run {
            notificationsRv.adapter = adapter
            notificationsRv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupItemTouchHelper() {
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.notificationsRv)
    }
}
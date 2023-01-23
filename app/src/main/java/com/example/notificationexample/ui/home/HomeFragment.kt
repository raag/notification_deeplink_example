package com.example.notificationexample.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notificationexample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

  private var _binding: FragmentHomeBinding? = null
  private lateinit var homeViewModel: HomeViewModel
  private val binding get() = _binding!!

  private val pushNotificationPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { _ ->
    homeViewModel.showNotification(requireActivity().applicationContext)
  }

  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textHome
    homeViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

    val myButton: Button = binding.button
    myButton.setOnClickListener {
      pushNotificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }
    return root
  }


  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
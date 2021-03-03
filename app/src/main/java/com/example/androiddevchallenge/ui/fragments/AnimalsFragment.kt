package com.example.androiddevchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.screen.AnimalsScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.AnimalsViewModel
import com.example.androiddevchallenge.viewmodel.AnimalsViewModelFactory

class AnimalsFragment: Fragment() {

    private val viewModel: AnimalsViewModel by viewModels {AnimalsViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.animalsFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                MyTheme {
                    Surface {
                        viewModel.animals.observeAsState().value?.let {animals ->
                            AnimalsScreen(animals, false, {}) {id ->
                                val bundle = bundleOf(AnimalDetailsFragment.BUNDLE_KEY_ID to id)
                                findNavController().navigate(R.id.animalDetailsFragment, bundle)
                            }
                        }
                        viewModel.refresh()
                    }
                }
            }
        }
    }
}
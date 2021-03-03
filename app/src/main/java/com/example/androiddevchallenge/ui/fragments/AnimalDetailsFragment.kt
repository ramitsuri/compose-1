/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.PagerState
import com.example.androiddevchallenge.ui.screen.AnimalDetails
import com.example.androiddevchallenge.ui.theme.Keyline1
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.AnimaDetailsViewModelFactory
import com.example.androiddevchallenge.viewmodel.AnimalDetailsViewModel
import kotlinx.coroutines.launch

class AnimalDetailsFragment : Fragment() {

    private val viewModel: AnimalDetailsViewModel by viewModels { AnimaDetailsViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.animalDetailsFragment
            lifecycleScope.launch {
                arguments?.getInt(BUNDLE_KEY_ID)?.let { animalId ->
                    viewModel.refresh(animalId)
                }
            }

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                MyTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        viewModel.animal.observeAsState().value?.let { animal ->
                            val pagerState = remember { PagerState() }
                            Spacer(Modifier.height(16.dp))

                            AnimalDetails(
                                animal = animal,
                                showBack = true,
                                { activity?.onBackPressedDispatcher?.onBackPressed() },
                                pagerState = pagerState,
                                modifier = Modifier
                                    .padding(start = Keyline1, top = 16.dp, end = Keyline1)
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(animal.url))
                                startActivity(browserIntent)
                            }

                            Spacer(Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val BUNDLE_KEY_ID = "id"
    }
}

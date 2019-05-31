package com.epam.task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.epam.cdptasks.R

class PlaceholderFragment : androidx.fragment.app.Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setResources(this@PlaceholderFragment.resources)
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: DEFAULT_INDEX)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_second_task, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(viewLifecycleOwner, Observer<String> {
            textView.text = it
        })
        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val DEFAULT_INDEX = 1

        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
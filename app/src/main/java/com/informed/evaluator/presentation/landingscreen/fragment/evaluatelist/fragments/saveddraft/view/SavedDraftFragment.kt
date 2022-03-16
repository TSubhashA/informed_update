package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.saveddraft.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.saveddraft.adapter.itemsectionview.ItemSectionRecyclerView
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.saveddraft.adapter.recycleradapter.SavedDraftListAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SavedDraftFragment : Fragment() {

    private var view1: View? = null
    lateinit var adapter: SavedDraftListAdapter
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (view1 == null) {

            view1 = inflater.inflate(R.layout.fragment_saved_draft, container, false)

            val recyclerView = view1?.findViewById(R.id.recycler_view) as RecyclerView

            adapter = SavedDraftListAdapter(requireContext())

            val layoutManager = LinearLayoutManager(requireContext())

            recyclerView.layoutManager = layoutManager

            recyclerView.adapter = adapter

            recyclerView.addItemDecoration(ItemSectionRecyclerView(requireContext()) { adapter.listData })

        }
        return view1


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavedDraftFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
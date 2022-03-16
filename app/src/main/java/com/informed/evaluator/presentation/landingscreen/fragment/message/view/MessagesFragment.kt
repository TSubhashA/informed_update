package com.informed.evaluator.presentation.landingscreen.fragment.message.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.fragment.message.adapter.MessageUserListAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MessagesFragment : Fragment() {

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
       val view=inflater.inflate(R.layout.fragment_messages, container, false)
            val toolbar=view.findViewById(R.id.my_tool_bar) as MaterialToolbar
            toolbar.setTitle("")
            val text=toolbar.findViewById(R.id.action_bar_title) as TextView
            text.text="Messages"

        val recyclerView=view.findViewById(R.id.user_list_message) as RecyclerView

        val adapter = MessageUserListAdapter(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.setItemViewCacheSize(20)
        recyclerView.adapter = adapter
//try {
//    (activity as LandingActivity?)?.removeBadge()
//}catch (e:Exception){
//    activity?.showToast(e.toString())
//}
        return view
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MessagesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
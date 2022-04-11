package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.view

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.adapter.EvaluatorListAdapter
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.adapter.itemsectionview.ItemSectionRecyclerView
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.model.AttendingListResp
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.model.RowsItem

import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.viewmodel.EvaluatorVMFactory
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.viewmodel.EvaluatorViewModel
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.ResultOf


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NewEvaluateFragment : Fragment() {


    private var view1: View? = null

    lateinit var adapter: EvaluatorListAdapter
    lateinit var progBar: CustomProgressDialogue
    var scrollFlag = false
    private lateinit var bottomProgBar: ProgressBar
    lateinit var searchEditText: TextInputLayout
    private val evalVM by viewModels<EvaluatorViewModel> { EvaluatorVMFactory() }


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
            progBar = CustomProgressDialogue(requireContext())
            view1 = inflater.inflate(R.layout.fragment_new_evaluate, container, false)
            bottomProgBar = view1?.findViewById(R.id.progress_bar) as ProgressBar
            searchEditText = view1?.findViewById(R.id.serch_edit_text) as TextInputLayout

            observeLivedata()
            getAttendingList()
            filter()

            searchEditText.setEndIconOnClickListener {
                if (searchEditText.editText?.text?.length!! > 0)
                    searchEditText.editText?.text?.clear()

            }


            val recyclerView = view1?.findViewById(R.id.recycler_list_trainees) as RecyclerView
            adapter = EvaluatorListAdapter(requireContext())
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(ItemSectionRecyclerView(requireContext()) { adapter.filtered_list });

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        if (
                            layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1
                        ) {
                            scrollFlag = true
                            evalVM.changePage()
                            Log.e(
                                ContentValues.TAG,
                                "onScrolled: ${layoutManager.findLastCompletelyVisibleItemPosition()}",
                            )
                        }
                    }
                }
            })
        }
        return view1

    }


    private fun getAttendingList() {
        evalVM.getAllAttending()
    }

    private fun filter() {

        searchEditText.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                textChangedHandler.removeCallbacksAndMessages(runnable)
                Log.e(ContentValues.TAG, "beforeTextChanged: $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                textChangedHandler.removeCallbacksAndMessages(runnable)
                Log.e(ContentValues.TAG, "onTextChanged: $p0")
                adapter.filter.filter(p0)

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.e(ContentValues.TAG, "afterTextChanged: $p0")

            }

        })

    }

    fun setResponseData(rows: List<RowsItem>) {
        adapter.setData(rows)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewEvaluateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun observeLivedata() {
        evalVM.dataSource?.observe(
            viewLifecycleOwner
        ) { it ->
            when (it) {
                is ResultOf.Empty -> {
                    requireActivity().showToast("Last Page Reached")
                    Log.e(ContentValues.TAG, "observeTraineeList: $it")
                }
                is ResultOf.Failed -> {
                    requireActivity().showToast("Failed")
                    Log.e(TAG, "observeLivedata: Failed : ${it.value.toString()}" )
                }
                is ResultOf.Failure -> {
                    requireActivity().showToast("Failure")
                    Log.e(TAG, "observeLivedata: Failure : ${it.message}, - ${it.throwable}" )
                }
                is ResultOf.Progress -> {
                    if (scrollFlag) {
                        if (it.loading) bottomProgBar.visibility = View.VISIBLE else {
                            bottomProgBar.visibility =
                                View.GONE
                            scrollFlag = false
                        }
                    } else
                        if (it.loading) progBar.show() else progBar.dismiss()
                }
                is ResultOf.Success -> {
                    it.value as AttendingListResp
//                    requireActivity().showToast("Success")
                    setResponseData(
                        it.value.data?.rows as List<RowsItem>
                    )
                }
            }
        }
    }
}
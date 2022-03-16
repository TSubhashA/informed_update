package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.view


import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.adapter.TraineeListAdapter
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model.Row
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model.TraineeListResp
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.viewmodel.TraineeVMFactory
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.viewmodel.TraineeViewModel
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.ResultOf
import kotlinx.android.synthetic.main.fragment_trainee_list.*
import java.time.Year
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TraineeListFragment : Fragment()//, TraineeListAdapter.EventListener
{
    private var view1: View? = null

    private val trainVM by viewModels<TraineeViewModel> { TraineeVMFactory() }

    private val STANDARD_APPEAR = 1
    private val SEARCH_APPEAR = 2

    private lateinit var bottomProgBar: ProgressBar
    private lateinit var progBar: CustomProgressDialogue
    private lateinit var adapter: TraineeListAdapter

    lateinit var toolbar: Toolbar
    lateinit var radio_group: RadioGroup

    var scrollFlag = false
    var searchFlag = false

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (view1 == null) {
            view1 = inflater.inflate(R.layout.fragment_trainee_list, container, false)
            bottomProgBar = view1?.findViewById(R.id.progress_bar)!!
            progBar = CustomProgressDialogue(requireContext())

            val searchBtn = view1?.findViewById(R.id.search_btn) as ImageView
            val searchEditText = view1?.findViewById(R.id.serch_edit_text) as TextInputLayout
            radio_group=view1?.findViewById(R.id.radio_group) as RadioGroup

            searchBtn.setOnClickListener {
                updateSearchView(SEARCH_APPEAR)
            }

            searchEditText.setEndIconOnClickListener {
                if (searchEditText.editText?.text?.length!! > 0)
                    searchEditText.editText?.text?.clear()
                else
                    updateSearchView(STANDARD_APPEAR)
            }

            val recyclerView = view1?.findViewById(R.id.recycler_list_trainees) as RecyclerView

            adapter = TraineeListAdapter(requireContext())
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        if (
                            layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1
                        ) {
                            scrollFlag = true
                            trainVM.changePage()
                            Log.e(
                                TAG,
                                "onScrolled: ${layoutManager.findLastCompletelyVisibleItemPosition()}",
                            )
                        }
                    }
                }
            })

            setFilters()

            traineeList()
            observeLivedata()

        }

        return view1
    }

    private fun setFilters() {
        radio_group.setOnCheckedChangeListener { _group, i ->
            when(i){
                R.id.radio_all->{
                    trainVM.searchTrainee()
                }
                R.id.radio_1->{
                    trainVM.searchTrainee(null, Calendar.getInstance().get(Calendar.YEAR))
                }
                R.id.radio_2->{
                    trainVM.searchTrainee(null, Calendar.getInstance().get(Calendar.YEAR)-1)
                }
                R.id.radio_3->{
                    trainVM.searchTrainee(null, Calendar.getInstance().get(Calendar.YEAR)-2)
                }

            }

        }
    }

    private fun setAdapterData(rows: List<Row?>?) {
//        if (searchFlag) {
                adapter.sections.clear()
//            searchFlag = false
//        }
        adapter.setData(rows as MutableList<Row?>)

    }


    private var textChangedHandler = Handler()
    private var runnable: Runnable? = null
    private fun updateSearchView(searchView: Int) {
        when (searchView) {
            SEARCH_APPEAR -> {
                search_btn.visibility = View.INVISIBLE
                search_layout.visibility = View.VISIBLE
            }
            STANDARD_APPEAR -> {
                search_btn.visibility = View.VISIBLE
                search_layout.visibility = View.GONE
            }
        }

        serch_edit_text.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                textChangedHandler.removeCallbacksAndMessages(runnable)
                Log.e(TAG, "beforeTextChanged: $p0" )
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                textChangedHandler.removeCallbacksAndMessages(runnable)
                Log.e(TAG, "onTextChanged: $p0" )
                adapter.filter.filter(p0)

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.e(TAG, "afterTextChanged: $p0" )
//                textChangedHandler.removeCallbacksAndMessages(runnable)
//                runnable = Runnable {
//                    Log.d("debug", "$p0")
////                    searchFlag = true
//                    trainVM.searchTrainee(if (p0?.length == 0) null else p0.toString())
//                }
//                textChangedHandler.postDelayed(runnable!!, 1000L)
//                adapter.filter.filter(p0)
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TraineeListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setData(): List<String> {

        val list = mutableListOf(
            "Guy Hawkins",
            "Theresa Webb",
            "Theresa Webb",
            "Darlene Robertson",
            "Darrell Steward",
            "Albert Flores",
            "Albert Flores",
            "Albert Flores",
            "Jane Cooper",
            "Blue",
            "Mark"
        )

        list.sort()

        Log.d("Set data : ", list.toString())
        return list

    }

    private fun traineeList() {
        trainVM.getTraineeList()
    }

    fun observeLivedata() {
        trainVM.dataSource?.observe(viewLifecycleOwner,
            { it ->
                when (it) {
                    is ResultOf.Empty -> {
                        requireActivity().showToast("Last Page Reached")
                        Log.e(TAG, "observeTraineeList: $it")
                    }
                    is ResultOf.Failed -> requireActivity().showToast("Failed")
                    is ResultOf.Failure -> requireActivity().showToast("Failure")
                    is ResultOf.Progress -> {
                        if (scrollFlag) {
                            if (it.loading) bottomProgBar.visibility = VISIBLE else {
                                bottomProgBar.visibility = GONE
                                scrollFlag = false
                            }
                        } else
                            if (it.loading) progBar.show() else progBar.dismiss()
                    }
                    is ResultOf.Success -> {
                        it.value as TraineeListResp
                        requireActivity().showToast("Success")

                        setAdapterData(it.value.data?.rows)
                    }
                }
            }
        )
    }


}
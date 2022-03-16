package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.bumptech.glide.Glide
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model.Row
import com.informed.evaluator.presentation.traineedetails.view.TraineeDetailsActivity
import com.informed.evaluator.utils.showToast
import de.hdodenhof.circleimageview.CircleImageView
import org.zakariya.stickyheaders.SectioningAdapter
import java.util.*
import kotlin.collections.ArrayList


class TraineeListAdapter(val context: Context) :
    SectioningAdapter(), Filterable {
    val TAG = "TraineeListAdapter"
//    var listener: EventListener? = event

    private var peoples: MutableList<Row?>? = null
    var sections: ArrayList<Section> = ArrayList()

    class Section {
        var alpha: String? = null
        var people = mutableListOf<Row?>()
    }

    private fun addSection(people: MutableList<Row?>) {
        var alpha = 0.toChar()
        var currentSection: Section? = null
        for (person in people) {
            Log.e(TAG, "setData: ${person?.name}")
            if (person?.name?.first() != alpha) {
                if (currentSection != null) {
                    sections.add(currentSection)
                }
                currentSection = Section()
                alpha = person?.name!![0]
                currentSection.alpha = alpha.toString()
            }
            currentSection?.people?.add(person)
        }
        sections.add(currentSection!!)
        notifyAllSectionsDataSetChanged()
    }


    fun setData(people: MutableList<Row?>) {
        people.sortBy { it?.name?.first()?.uppercase() }
        this.peoples = people
        // sort people into buckets by the first letter of last name
        // sort people into buckets by the first letter of last name
        addSection(people)
    }


    override fun getNumberOfItemsInSection(sectionIndex: Int): Int {
        return sections[sectionIndex].people.size
    }

    override fun doesSectionHaveHeader(sectionIndex: Int): Boolean {
        return true
    }

    override fun doesSectionHaveFooter(sectionIndex: Int): Boolean {
        return false
    }


    class HeaderViewHolder(group: View) : SectioningAdapter.HeaderViewHolder(group) {
        val hTV = group.findViewById(R.id.header_text) as TextView

    }


    class ItemViewHolder(itemView: View) : SectioningAdapter.ItemViewHolder(itemView) {
        val nameTV = itemView.findViewById(R.id.trainee_name) as TextView
        val circleImageV = itemView.findViewById(R.id.trainee_list_image) as CircleImageView
        val yearTV = itemView.findViewById(R.id.trainee_year) as TextView
    }

    override fun getNumberOfSections(): Int {
        return sections.size
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, itemType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.trainee_list_row, parent, false)
        return ItemViewHolder(v)
    }

    override fun onCreateHeaderViewHolder(
        parent: ViewGroup,
        headerType: Int
    ): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.header_data, parent, false)
        return HeaderViewHolder(v)
    }

    override fun onBindItemViewHolder(
        viewHolder: SectioningAdapter.ItemViewHolder?,
        sectionIndex: Int,
        itemIndex: Int,
        itemUserType: Int
    ) {
        viewHolder as ItemViewHolder
        val s: Section = sections[sectionIndex]
        val trainee: Row? = s.people.get(itemIndex)
        viewHolder.nameTV.setText(
            trainee?.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        )
        viewHolder.yearTV.setText(
            trainee?.roles?.get(0)?.year.toString()
        )
        Log.e(TAG, "onBind : ${trainee?.name}")

        if (trainee?.imageUrl != null) {
            Glide.with(viewHolder.circleImageV.context)
                .load(trainee.imageUrl)
                .into(viewHolder.circleImageV)
        }

        viewHolder.itemView.setOnClickListener {
//            event.onEvent(trainee!!, itemIndex)
            context.showToast("Clicked")
            val intent = Intent(context, TraineeDetailsActivity::class.java)
            context.startActivity(intent.putExtra("trainee", trainee))
        }


    }

    override fun onBindHeaderViewHolder(
        viewHolder: SectioningAdapter.HeaderViewHolder?,
        sectionIndex: Int,
        headerUserType: Int
    ) {
        viewHolder as HeaderViewHolder
        val s: Section = sections[sectionIndex]
        val hvh = viewHolder
        hvh.hTV.text = s.alpha?.uppercase()
    }

    override fun getFilter(): Filter {

        return Searched_Filter
    }

    private val Searched_Filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = mutableListOf<Row?>()
            if (constraint.length == 0) {
                filteredList.addAll(peoples!!)
            } else {
                val filterPattern = constraint.toString().trim { it <= ' ' }
                for (item in peoples!!) {
                    if (item?.name?.contains(filterPattern,true) == true) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            results.count=filteredList.size
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            if  (results.count>0){
            sections.clear()
            addSection(results.values as MutableList<Row?>)}
            else {
                Log.println(Log.INFO, "Results", "${results.count} ");
                notifyAllSectionsDataSetChanged()            }
        }
    }


}
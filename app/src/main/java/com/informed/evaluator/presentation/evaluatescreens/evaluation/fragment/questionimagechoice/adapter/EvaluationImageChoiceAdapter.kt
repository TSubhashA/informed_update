package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.adapter


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.*
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.customviews.SlideToActView
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.ChoicesItem


class EvaluationImageChoiceAdapter(val context: Context, val interf: CustomListener) :
    RecyclerView.Adapter<EvaluationImageChoiceAdapter.ViewHolder>() {


    private var data = mutableListOf<ChoicesItem?>()
    var selectedModel: ChoicesItem? =null
    private var selectedData = -1
    var extendedCard = -1
    var commentPos = -1
    var commentSaved = ""
    var isExpansion = false
    var isCommentSaved = false

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<ChoicesItem?>) {

        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_question_image_choice_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setVisiblity(position == extendedCard, position)
        holder.selectedPosition(position == selectedData)

        hide(holder, position)
        holder.bind(data.get(position), position, holder)

    }

    private fun hide(holder: ViewHolder, position: Int) {

        if (isExpansion) {
            if (extendedCard != position) {
                holder.itemView.layoutParams =
                    RecyclerView.LayoutParams(0, 0)
                holder.itemView.visibility = View.GONE

            } else {
                holder.itemView.layoutParams =
                    RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                holder.itemView.visibility = View.VISIBLE
            }
        } else {
            holder.itemView.layoutParams =
                RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            holder.itemView.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onItemLeftSwipe(viewHolder: ViewHolder) {

        Log.e(TAG, "onItemLeftSwipe: $extendedCard")
        Log.e(TAG, "onItemLeftSwipe: ${viewHolder.absoluteAdapterPosition}")

        if (selectedData > -1) {
            val oldSelect = selectedData
            selectedData = -1
            viewHolder.updateItem(oldSelect)
        }

        updateSwipe(viewHolder)


    }

    fun updateSwipe(viewHolder: ViewHolder) {
        extendedCard = viewHolder.absoluteAdapterPosition
        selectedData = viewHolder.absoluteAdapterPosition


//        notifyItemChanged(viewHolder.absoluteAdapterPosition)
        viewHolder.updateItem(viewHolder.absoluteAdapterPosition)

        notifyItemRangeChanged(0, data.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.findViewById(R.id.card_view) as MaterialCardView
        val optionNumber = itemView.findViewById(R.id.option_no) as TextView
        val optionValue = itemView.findViewById(R.id.option_value) as TextView
        val cancelText = itemView.findViewById(R.id.cancel_comment) as ImageButton
        val submit = itemView.findViewById(R.id.submit) as ImageButton
        val slider =
            itemView.findViewById(R.id.slider_button) as SlideToActView
        val shimmer = itemView.findViewById(R.id.shimmer) as ShimmerFrameLayout
        val swipeRelativeLayout = itemView.findViewById(R.id.relativeLayout) as RelativeLayout
        val comment_saved = itemView.findViewById(R.id.comment_saved) as TextView
        val editText = itemView.findViewById(R.id._edit_text_ll) as TextInputLayout
        val llLayout_comment = itemView.findViewById(R.id.comment_option_one_ll) as LinearLayout

                val image_view = itemView.findViewById(R.id.image_view) as ImageView
        val option_no_comment = itemView.findViewById(R.id.option_no_comment) as TextView
        val topll = itemView.findViewById(R.id.topll) as LinearLayout


        @SuppressLint("ClickableViewAccessibility", "SetTextI18n", "Recycle")
        fun bind(get: ChoicesItem?, position: Int, holder: ViewHolder) {
            updateItem(position)
            optionNumber.text = (position + 1 + 64).toChar().toString()
            optionValue.text = get?.label
            option_no_comment.text = (position + 1 + 64).toChar().toString()

            Glide.with(image_view)
                .load(get?.media?.toString())
                .into(image_view)

            setButtonText()


            slider.onSlideToActAnimationEventListener =
                object : SlideToActView.OnSlideToActAnimationEventListener {
                    override fun onSlideCompleteAnimationEnded(view: SlideToActView) {

                    }

                    override fun onSlideCompleteAnimationStarted(
                        view: SlideToActView,
                        threshold: Float
                    ) {
                        shimmer.stopShimmer()
                        shimmer.visibility = View.GONE
                    }

                    override fun onSlideResetAnimationEnded(view: SlideToActView) {

                        shimmer.visibility = View.VISIBLE
                        shimmer.alpha = 1f
                        shimmer.startShimmer()
                    }

                    override fun onSlideResetAnimationStarted(view: SlideToActView) {

                    }

                }

            topll.setOnClickListener {

                if (commentPos == position) {
                    updateSwipe(this)
                } else
                    if (selectedData == position) {
                        selectedData = -1
                        selectedModel=null
                        selectedPosition(false)
                        updateItem(position)

                    } else if (extendedCard == position) {

//                    updateItem(position)
//                    extendedCard = -1
//                    if  (selectedData>-1){
//                    val oldSelected = selectedData
//                    selectedData = position
//                    notifyItemChanged(oldSelected)
//                    notifyItemChanged(selectedData)

//                    cardView.isChecked = true
                    } else {
                        setVisiblity(false, position)

                        if (commentPos > -1) {
                            val oldextendedCard = commentPos
                            commentPos = -1
                            commentSaved = ""
                            updateItem(oldextendedCard)
                            notifyItemChanged(oldextendedCard)
                        }

                        if (selectedData > -1) {
                            val oldSelected = selectedData
                            selectedData = position
                            updateItem(oldSelected)
                            notifyItemChanged(oldSelected)
                            updateItem(position)
                            notifyItemChanged(position)

                        } else {
                            selectedData = position
                            selectedPosition(true)
                            updateItem(position)
                            notifyItemChanged(position)
                        }
                    }
                setButtonText()

            }


            cancelText.setOnClickListener {
                extendedCard = -1
                isExpansion = false
                editText.editText?.setText(
                    ""
                )
                commentSaved = ""
                comment_saved.text = commentSaved
                commentPos = -1
                updateItem(position)

                hideKeyboard(context, editText.editText!!)

                notifyItemRangeChanged(0, data.size)

            }

            submit.setOnClickListener {
                commentSave(this)
            }

            var relParam: LinearLayout.LayoutParams? = null
            var textParam: LinearLayout.LayoutParams? = null

            var param: ViewGroup.LayoutParams? = null
            slider.onSlideListener = object : SlideToActView.OnSlideingListener {
                override fun onSlide(view: SlideToActView, threshold: Float, event: MotionEvent) {
                    val per = threshold / slider.width
                    Log.e(TAG, "onSlide: $per")
                    shimmer.alpha = 1 - 1.8f * per
                    optionValue.alpha = 1 - 1.3f * per

                    if (per > 0) {

                        if (!comment_saved.isVisible) {
                            comment_saved.visibility = View.VISIBLE

                            textParam = comment_saved.layoutParams as LinearLayout.LayoutParams
                            textParam!!.height = 0
                            comment_saved.layoutParams = textParam
                            comment_saved.text = ""
                        }
                        if (textParam != null) {
                            textParam?.height = (slider.height * (per + 1)).toInt()
                            comment_saved.layoutParams = textParam

                        }

                    }

                    if (per == 0f)
//                        llLayout_comment.visibility = View.GONE
                        comment_saved.visibility = View.GONE
                    Log.e(TAG, "onSlideHeight: ${per * 10}")

                }
            }

            slider.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
                override fun onSlideComplete(view: SlideToActView) {
                    optionNumber.animate().translationX(0f)
                    optionNumber.animate().translationY(0f)
                    relParam = LinearLayout.LayoutParams(
                        MATCH_PARENT,
                        WRAP_CONTENT
                    )
                    llLayout_comment.layoutParams = relParam
                    comment_saved.layoutParams = relParam
                    onItemLeftSwipe(this@ViewHolder)
                }
            }
        }

        fun selectedPosition(selectedState: Boolean) {

            selectedModel = if (selectedData!=-1)
                data.get(selectedData)
            else
                null

            if (selectedState) {
                topll.setBackgroundColor(context.resources.getColor(R.color.purple_6))
                cardView.setBackgroundColor(context.resources.getColor(R.color.purple_6))
                if (commentPos == -1) {
                    slider.resetSlider()
                    swipeRelativeLayout.visibility = View.VISIBLE

                } else
                    swipeRelativeLayout.visibility = View.GONE

            } else {
                topll.setBackgroundColor(Color.WHITE)
                cardView.setBackgroundColor(Color.WHITE)
                swipeRelativeLayout.visibility = View.GONE
            }
        }

        fun updateItem(position: Int) {
            if (extendedCard == position) {
                setVisiblity(true, position)
            } else
                setVisiblity(false, position)
        }

        private fun setButtonText() {
            if (selectedData != -1 || extendedCard != -1)
                interf.changeBtn("Next")
            else
                interf.changeBtn("Skip")
        }

        @SuppressLint("ResourceAsColor", "Recycle")
        fun setVisiblity(checkedStatus: Boolean, position: Int) {

            if (checkedStatus) {
                isExpansion = true

                swipeRelativeLayout.visibility = View.GONE

                topll.visibility = GONE
                llLayout_comment.visibility = VISIBLE

                if (commentPos == position)
                    editText.editText?.setText(commentSaved)
                else
                    editText.editText?.setText("")

                showSoftKeyboard(context, editText.editText!!);
            } else {
                if (commentPos == position) {
                    if (commentSaved.length > 0) {
                        comment_saved.text = commentSaved
                        comment_saved.visibility = View.VISIBLE
                    } else {
                        commentSaved = ""
                        comment_saved.text = commentSaved
                        comment_saved.visibility = View.GONE

                    }
                } else
                    comment_saved.visibility = View.GONE

//                llLayout_question.setBackgroundColor(Color.WHITE)
                llLayout_comment.visibility = View.GONE
                topll.visibility = View.VISIBLE
                optionValue.alpha = 1f
//                optionValue.setTextColor(Color.BLACK)
            }
        }

    }

    interface CustomListener {
        fun changeBtn(text: String)
    }


    fun showSoftKeyboard(context: Context, editText: EditText) {
        try {
            editText.requestFocus()
            editText.postDelayed(
                {
                    val keyboard: InputMethodManager =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.showSoftInput(editText, 0)
                }, 500
            )
        } catch (npe: NullPointerException) {
            npe.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard(context: Context, editText: EditText) {
        val methodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        methodManager.hideSoftInputFromWindow(
            editText.applicationWindowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun commentSave(holder: ViewHolder) {
        isExpansion = false
        val textComment = holder.editText.editText?.text.toString()
        hideKeyboard(context, holder.editText.editText!!)
        Log.e(TAG, "commentSave: $textComment")
        if (textComment.length > 0) {
            commentPos = extendedCard
            commentSaved = textComment

        }
        extendedCard = -1


        notifyItemRangeChanged(0, data.size)

    }


}

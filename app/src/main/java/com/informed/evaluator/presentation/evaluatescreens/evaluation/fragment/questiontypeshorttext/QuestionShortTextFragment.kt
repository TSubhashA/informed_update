package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questiontypeshorttext

import android.content.ContentValues
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity
import com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper.SpeechListnerWrapper
import com.informed.evaluator.utils.SpeechRecognizeClass
import com.informed.evaluator.utils.showToast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionShortTextFragment : Fragment() {



    private var param1: QuestionsItem? = null
    private var param2: String? = null

    private var dicatationEnable = false

    private lateinit var textContainer: TextInputLayout
    private lateinit var micButton: ImageButton
    private lateinit var dicationText: TextView
    private lateinit var speech: SpeechRecognizeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_question_short_text, container, false)
        speech = SpeechRecognizeClass(requireContext())
        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        val nextButton = view.findViewById(R.id.btn_next) as MaterialButton
        val question_title = view.findViewById(R.id.question_title) as TextView
        val question = view.findViewById(R.id.question) as TextView
        val image = view.findViewById(R.id.image) as ImageView


        question_title.setText(param1?.title)
        question.setText(param1?.description)
        if (param1?.description?.length==0 || param1?.description==null)
            question.visibility=View.GONE

        if (param1?.media!=null){
            image.visibility=View.VISIBLE
            Glide.with(this)
                .load(param1?.media!![0])
                .into(image)
        }

        val number =view.findViewById<TextView>(R.id.number)
        number?.setText(param1?.position.toString())



        micButton = view.findViewById(R.id.mic_btn) as ImageButton
        dicationText = view.findViewById(R.id.dictatio_text) as TextView
        textContainer = view.findViewById(R.id.textContainer) as TextInputLayout

        setupMic()

        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
            var model: SaveEvaluateRequest? = null
            if (textContainer.editText?.text?.length!! > 0) {

                val text = textContainer.editText?.text


                model = SaveEvaluateRequest()
                model.questionId = param1?.id
                model.textValue = text
            }

            (activity as EvaluationActivity?)?.changeScreen(saveRequest = model)
        }

        return view
    }

    private fun setupMic() {

        micButton.setOnClickListener {
            if (speech.checkPermission()) {
                if (speech.isRecognitionAvailable()) {
                    dicatationEnable = !dicatationEnable
                    dictationStart()
                } else
                    context?.showToast("Speech Not available kindly use keyboard Speech listener")

            }
        }

        speech.setListener(object : SpeechListnerWrapper() {
            override fun onResults(p0: Bundle?) {
                val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                context?.showToast("onResult : ${matches?.get(0)}")
                textContainer.editText?.setText(matches?.get(0).toString())
                Log.e(ContentValues.TAG, "onResults: ${matches?.get(0)}")
            }
        })

    }

    private fun dictationStart() {
        if (dicatationEnable) {
            micButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple))
            dicationText.text = "Dictation is enable"
            speech.speech.startListening(speech.getSpeechIntent())
        } else {
            micButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.purple_4))
            dicationText.text = "Dictation is disable"
            speech.speech.stopListening()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem?, param2: String) =
            QuestionShortTextFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
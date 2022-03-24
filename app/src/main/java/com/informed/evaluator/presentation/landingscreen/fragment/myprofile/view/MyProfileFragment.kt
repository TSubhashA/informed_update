package com.informed.evaluator.presentation.landingscreen.fragment.myprofile.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.informed.evaluator.R
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.changepassword.view.ChangePasswordActivity
import com.informed.evaluator.presentation.landingscreen.fragment.myprofile.ImageUpdateCallBack
import com.informed.evaluator.presentation.login.view.SignInActivity
import com.informed.evaluator.presentation.notification.view.NotificationActivity
import com.informed.evaluator.presentation.personaldata.view.PersonalDataActivity


class MyProfileFragment : Fragment() {

lateinit var profileImage:ImageView
lateinit var imageCallback:ImageUpdateCallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageCallback=activity as ImageUpdateCallBack

    }


    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)
        val toolbar = view.findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.title = ""
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "My Profile"

        val personalData = view.findViewById(R.id.profile_personal_data) as CardView
        val noticationText = view.findViewById(R.id.profile_noti_text) as TextView
        val notication = view.findViewById(R.id.profile_notification) as CardView
        val changePassword = view.findViewById(R.id.profile_change_password) as CardView
        val profileLogout = view.findViewById(R.id.profile_logout) as CardView
         profileImage = view.findViewById(R.id.profile_image) as ImageView

        setImage()



        val badgeDrawable = BadgeDrawable.create(requireContext())

        badgeDrawable.backgroundColor = Color.RED
        badgeDrawable.badgeGravity = BadgeDrawable.TOP_END

//        badgeDrawable.setHorizontalOffset(350)
        val n = 0
        if (n > 0)
            badgeDrawable.number = n

        noticationText.viewTreeObserver
            .addOnGlobalLayoutListener {
                BadgeUtils.attachBadgeDrawable(badgeDrawable, noticationText)
                //                    noticationText.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }

        profileLogout.setOnClickListener {
            SharedPreference().clearSharedPreference()

            activity?.startActivity(
                Intent(
                    requireContext(),
                    SignInActivity::class.java
                )
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            activity?.finish()

        }

        personalData.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    PersonalDataActivity::class.java
                )
            )
        }

        notication.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NotificationActivity::class.java
                )
            )
            BadgeUtils.detachBadgeDrawable(badgeDrawable, noticationText)
        }
        changePassword.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    ChangePasswordActivity::class.java
                )
            )
        }
        return view
    }

    fun setImage(){
        Glide.with(profileImage.context)
            .load(SharedPreference().getValueString(ConstantKeys.IMAGE_URL))
            .into(profileImage)

        imageCallback.setProfileImageAtBottom()

    }

    override fun onResume() {
        super.onResume()
        setImage()

    }


}
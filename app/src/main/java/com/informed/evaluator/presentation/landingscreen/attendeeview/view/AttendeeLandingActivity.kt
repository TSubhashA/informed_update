package com.informed.evaluator.presentation.landingscreen.attendeeview.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.informed.evaluator.R
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.landingscreen.fragment.download.view.DownloadFragment
import com.informed.evaluator.presentation.landingscreen.fragment.message.view.MessagesFragment
import com.informed.evaluator.presentation.landingscreen.fragment.myfeedback.view.MyFeedbackFragment
import com.informed.evaluator.presentation.landingscreen.fragment.myprofile.view.MyProfileFragment
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.view.TraineeListFragment
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.view.EvaluatorListFragment
import com.informed.evaluator.presentation.landingscreen.fragment.myprofile.ImageUpdateCallBack
import kotlinx.android.synthetic.main.activity_attendee_landing.*


class AttendeeLandingActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, ImageUpdateCallBack {

    val _traineeListFragment: Fragment = TraineeListFragment()
    val _myFeedbackFragment: Fragment = MyFeedbackFragment()
//    val _messagesFragment: Fragment = MessagesFragment()
val _evaluatorListFragment: Fragment = EvaluatorListFragment()
    val _downloadFragment: Fragment = DownloadFragment()
    val _myProfileFragment: Fragment = MyProfileFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = _traineeListFragment


    private lateinit var navController: NavController
    private lateinit var naavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendee_landing)

        fm.beginTransaction().add(R.id.fragmentContainerView, _myProfileFragment, "5").hide(_myProfileFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView, _evaluatorListFragment, "4").hide(_evaluatorListFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView, _downloadFragment, "3").hide(_downloadFragment).commit();
//        fm.beginTransaction().add(R.id.fragmentContainerView, _messagesFragment, "3").hide(_messagesFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView, _myFeedbackFragment, "2").hide(_myFeedbackFragment).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView,_traineeListFragment, "1").commit();

        naavigationView=findViewById(R.id.bottom_nav_bar)
        naavigationView.itemIconTintList = null
//        setProfileImageAtBottom()

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment
        navController = navHostFragment.navController  //findNavController(R.id.fragmentContainerView)

        naavigationView.setupWithNavController(navController)

        setBadgeCount()

        naavigationView.setOnNavigationItemSelectedListener(this)



    }


    override fun setProfileImageAtBottom() {

        val menu = bottom_nav_bar.menu
        val menuItem = menu.findItem(R.id.myprofile_nav_menu)
        Glide.with(this)
            .asBitmap()
            .load(SharedPreference().getValueString(ConstantKeys.IMAGE_URL))
            .apply(
                RequestOptions
                    .circleCropTransform()
//                    .placeholder(R.drawable.profile_image)
            )
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    menuItem?.icon = BitmapDrawable(resources, resource)
                    Log.d("Resource : ", "applied")
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })

    }

    fun setBadgeCount(){
        bottom_nav_bar.getOrCreateBadge(R.id.messages_nav_menu).number = 20

    }

    fun removeBadge(){
        if ( bottom_nav_bar.getBadge(R.id.messages_nav_menu)?.isVisible()==true){
            bottom_nav_bar.removeBadge(R.id.messages_nav_menu)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
          R.id.trainees_nav_menu->{
              fm.beginTransaction().hide(active).commit()
              fm.beginTransaction().show(_traineeListFragment).commit();
              active = _traineeListFragment;

          }
          R.id.attend_feedback_nav_menu->{
              fm.beginTransaction().hide(active).commit()
              fm.beginTransaction().show(_myFeedbackFragment).commit();
              active = _myFeedbackFragment
          }
          R.id.messages_nav_menu->{
//              fm.beginTransaction().hide(active).commit()
//              fm.beginTransaction().show(_messagesFragment).commit();
//              active = _messagesFragment

          }
          R.id.attend_download_nav_menu->{
              fm.beginTransaction().hide(active).commit()
              fm.beginTransaction().show(_downloadFragment).commit();
              active = _downloadFragment
          }
          R.id.myprofile_nav_menu->{
              fm.beginTransaction().hide(active).commit()
              fm.beginTransaction().show(_myProfileFragment).commit();
              active =_myProfileFragment
          }

            R.id.evaluator_nav_menu->{
                fm.beginTransaction().hide(active).commit()
                fm.beginTransaction().show(_evaluatorListFragment).commit();
                active =_evaluatorListFragment

            }
        }
        return true
    }


}
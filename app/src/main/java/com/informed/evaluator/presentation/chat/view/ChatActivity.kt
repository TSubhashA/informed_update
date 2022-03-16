package com.informed.evaluator.presentation.chat.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.informed.evaluator.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.customviews.OverLapItemView
import com.informed.evaluator.presentation.chat.adapter.ChatAdapter

import com.informed.trainee.presentation.chat.adapter.viewholder.ItemSectionDecoration
import com.informed.trainee.presentation.chat.domain.model.MyChat


class ChatActivity : BaseActivity() {

    private val stactView: LinearLayout by lazy {
        findViewById(R.id.stack_view)
    }

    private val chatView: RecyclerView by lazy {
        findViewById(R.id.recycler_view_chat)
    }

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var itemSectionDecoration: ItemSectionDecoration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setTTopBar()

        setUserProfileImages()

        setChatAdapter()


    }

    private fun setChatAdapter() {
        layoutManager= LinearLayoutManager(this)
        chatAdapter= ChatAdapter(
            1
        )
        chatAdapter.onLoad(dummyData(0,20))
        itemSectionDecoration=ItemSectionDecoration(this){chatAdapter.list}
        chatView.addItemDecoration(itemSectionDecoration)
        chatView.layoutManager=layoutManager
        chatView.adapter=chatAdapter



    }

    private fun setTTopBar() {
        val toolbar=findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text=toolbar.findViewById(R.id.action_bar_title) as TextView
        val back=toolbar.findViewById(R.id.back) as ImageButton

        setSupportActionBar(toolbar)

        back.setOnClickListener { finish() }

    }

    private fun setUserProfileImages() {
        stactView.removeAllViews()
        val list= arrayListOf<Drawable>(resources.getDrawable(R.drawable.profile_image),resources.getDrawable(R.drawable.profile_image),resources.getDrawable(R.drawable.profile_image),resources.getDrawable(R.drawable.profile_image),resources.getDrawable(R.drawable.profile_pic),resources.getDrawable(R.drawable.ic_message),resources.getDrawable(R.drawable.ic_message))


        val itemCount=list.size

        val maxItem=5
        val calValue= if ( itemCount > maxItem)maxItem else itemCount
        val remainCount= if ( itemCount > maxItem)itemCount-maxItem-1 else 0
        for (i in 0 until calValue )
        {
            OverLapItemView(this).apply {

                if (remainCount > 0) {
                    if (i == calValue - 1) {
                        setText("$remainCount+")
                    } else {
                        setImageResource(list[i])
                    }
                    stactView.addView(this)
                }
                else
                    stactView.addView(this)

            }

        }

        for(i in 0 until stactView.childCount) {
            val child = stactView.getChildAt(i)
            if (child is OverLapItemView)
                if (i != calValue-1)
                {
                    child.overLapatEnd(-6f)
                }
        }

    }

    private fun dummyData(offset:Int,limit:Int):MutableList<MyChat>{
        val list= mutableListOf<MyChat>()

        var itemModel: MyChat

        for (i in offset until offset+limit){
            itemModel= MyChat(1,231,1,"Hello",getDummyDateString("${i+1}"))
            list.add(itemModel)
            itemModel= MyChat(1,1,1,"Hello",getDummyDateString("${i+1}"))
            list.add(itemModel)
            itemModel= MyChat(1,231,1,"How Are You",getDummyDateString("${i+1}"))
            list.add(itemModel)
            itemModel= MyChat(1,1,1,"Fine Good",getDummyDateString("${i+1}"))
            list.add(itemModel)
        }
        return list
    }

    private fun getDummyDateString(day:String):String{ return "2021 - 12 - $day"}


}
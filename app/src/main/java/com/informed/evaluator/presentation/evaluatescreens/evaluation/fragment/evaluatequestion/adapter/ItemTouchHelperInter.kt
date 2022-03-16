package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter

interface ItemTouchHelperInter {

    fun onItemMove (fromPosition:Int, toPosition:Int)
    fun onItemSwiped(postion:Int)
}
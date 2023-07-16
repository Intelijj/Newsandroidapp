package com.example.my1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            ChatFragment()
        } else if (position == 1) {
            StatusFragment()
        }else if(position==2){
            CallFragment()
        }
        else if(position==3){
            BusinessFragment()
        }else if(position==4){
            EntertainmentFragment()
        }else if(position==5){
            TechnologyFragment()
        }else if(position==6){
            ScienceFragment()
        }
        else {
            HealthFragment()
        }
    }

    override fun getCount(): Int {
        return 8 //No. of tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0) {
            return "Trending"
        } else if (position == 1) {
            return "General"
        }else if(position==2){
            return "Sports"
        }else if(position==4){
            return "Entertainment"
        }else if(position==3){
            return "Business"
        }else if(position==5){
            return "Technology"
        }else if(position==6){
            return "Science"
        }
        return "Health"
    }
}
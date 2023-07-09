package com.yws.common.ui.mstudent.answerpaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.widget.TopBar

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class StuAnsPaperFragment : Fragment() {
    companion object {
        private val TABS = arrayOf("未批改", "已批改")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)

            TopBar("答卷", buildCustomShape(0F, fillColor = 0xFFEEEEEE))

            val tableLayout = TabLayout(context).apply { id = View.generateViewId() }
            val viewpager2 = ViewPager2(context).apply { id = View.generateViewId() }

            tableLayout.addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 48.dp) {
                    topToTop = PARENT_ID
                    topMargin = 48.dp
                }
            }

            viewpager2.addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 0.dp) {
                    topToBottom = tableLayout.id
                    bottomToBottom = PARENT_ID
                }

                adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                    override fun getItemCount() = TABS.size

                    override fun createFragment(position: Int): Fragment {
                        if (position == 0) {
                            return StuAnsIdleFragment()
                        }
                        if (position == 1) {
                            return StuAnsCorrectFragment()
                        }
                        return StuAnsIdleFragment()
                    }

                }
            }

            TabLayoutMediator(tableLayout, viewpager2) { tab, position ->
                tab.text = TABS[position]
            }.attach()
        }
    }
}
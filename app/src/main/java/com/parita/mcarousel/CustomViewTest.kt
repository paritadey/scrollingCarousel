package com.parita.mcarousel

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.*

class CustomViewTest(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs),
    OnSnapPositionChangeListener {

    private var list = arrayListOf<CarouselData>()
    var carouselAdapter: CarouselAdapter
    var mainRV: RecyclerView
    var progressRV: RecyclerView
    var snapHelper: SnapHelper
    var mainAdapter: MainCarouselAdapter
    private var carouselNumber = 0
    private lateinit var backgroundLayout: ConstraintLayout

    init {
        inflate(context, R.layout.carousel_layout, this)
        backgroundLayout = findViewById(R.id.rv_ll)
        carouselAdapter = CarouselAdapter(list, context)
        mainAdapter = MainCarouselAdapter(list)
        progressRV = findViewById<RecyclerView>(R.id.rv).apply {
            adapter = carouselAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.HORIZONTAL
                ).apply {
                    setDrawable(resources.getDrawable(R.drawable.divider, null))
                })
        }
        carouselAdapter.notifyDataSetChanged()
        snapHelper = PagerSnapHelper()
        mainRV = findViewById<RecyclerView>(R.id.rv_main).apply {
            adapter = mainAdapter
            attachSnapHelperWithListener(
                snapHelper,
                SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
                this@CustomViewTest
            )
        }
        mainAdapter.notifyDataSetChanged()
    }


    fun RecyclerView.attachSnapHelperWithListener(
        snapHelper: SnapHelper,
        behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
        onSnapPositionChangeListener: OnSnapPositionChangeListener
    ) {
        snapHelper.attachToRecyclerView(this)
        val snapOnScrollListener =
            SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
        addOnScrollListener(snapOnScrollListener)
    }

    override fun onSnapPositionChange(position: Int, direction: SnapOnScrollListener.Direction) {
        if (list.size in 2..6) {
            setCarousel2_6Dots(position)
        } else if (list.size == 7) {
            setCarousel7Dots(position)
        } else if (list.size > 7) {
            setCarousel7moreDots(position)
        }
        progressRV.smoothScrollToPosition(position)
        carouselAdapter.notifyDataSetChanged()
    }

    fun sentCarouselLength(value: Int) {
        carouselNumber = value
        for (i in 0 until carouselNumber) {
            list.add(CarouselData())
        }
        list[0].apply {
            isSelected = true
            viewType = 4
        }
        carouselAdapter.notifyDataSetChanged()
        mainAdapter.notifyDataSetChanged()
        invalidate()
        requestLayout()
    }


    fun setSelectedPosition(position: Int) {
        if (list.size in 2..6) {
            setCarousel2_6Dots(position)
        } else if (list.size == 7) {
            setCarousel7Dots(position)
        } else if (list.size > 7) {
            setCarousel7moreDots(position)
        }
        progressRV.smoothScrollToPosition(position)
        carouselAdapter.notifyDataSetChanged()
        mainAdapter.notifyDataSetChanged()
        invalidate()
        requestLayout()
    }

    fun colorRes(color: CarouselColor) {
        carouselAdapter.setDotColorResource(color.dotColor)
        backgroundLayout.setBackgroundColor(resources.getColor(color.backgroundColor))
        carouselAdapter.notifyDataSetChanged()
        mainAdapter.notifyDataSetChanged()
        invalidate()
        requestLayout()
    }

    fun setCarousel2_6Dots(position: Int) {
        list.map {
            it.isSelected = false
            it.viewType = 1
        }
        list[position].apply {
            isSelected = true
            viewType = 3
        }
    }

    fun setCarousel7Dots(position: Int) {
        when (position) {
            0 -> {
                list[0].apply {
                    isSelected = true
                    viewType = 3
                }
                list[1].apply {
                    isSelected = false
                    viewType = 1
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 2
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                list[5].apply {
                    isSelected = false
                    viewType = 4
                }
                list[6].apply {
                    isSelected = false
                    viewType = 4
                }
            }
            1 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 1
                }
                list[1].apply {
                    isSelected = true
                    viewType = 3
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 2
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                list[5].apply {
                    isSelected = false
                    viewType = 4
                }
                list[6].apply {
                    isSelected = false
                    viewType = 4
                }
            }
            2 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 1
                }
                list[1].apply {
                    isSelected = false
                    viewType = 1
                }
                list[2].apply {
                    isSelected = true
                    viewType = 3
                }
                list[3].apply {
                    isSelected = false
                    viewType = 2
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                list[5].apply {
                    isSelected = false
                    viewType = 4
                }
                list[5].apply {
                    isSelected = false
                    viewType = 4
                }
            }
            3 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 1
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = true
                    viewType = 3
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                list[5].apply {
                    isSelected = false
                    viewType = 2
                }
                list[6].apply {
                    isSelected = false
                    viewType = 4
                }
            }
            4 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 2
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 1
                }
                list[4].apply {
                    isSelected = true
                    viewType = 3
                }
                list[5].apply {
                    isSelected = false
                    viewType = 2
                }
                list[6].apply {
                    isSelected = false
                    viewType = 2
                }
            }
            5 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 2
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 1
                }
                list[4].apply {
                    isSelected = true
                    viewType = 3
                }
                list[5].apply {
                    isSelected = false
                    viewType = 2
                }
                list[6].apply {
                    isSelected = false
                    viewType = 4
                }
            }
            6 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 2
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 1
                }
                list[4].apply {
                    isSelected = true
                    viewType = 3
                }
                list[5].apply {
                    isSelected = false
                    viewType = 4
                }
                list[6].apply {
                    isSelected = false
                    viewType = 4
                }
            }
            else -> {
                list[7].apply {
                    isSelected = false
                    viewType = 4
                }
            }

        }
    }

    fun setCarousel7moreDots(position: Int) {
        when (position) {
            0 -> {
                list[0].apply {
                    isSelected = true
                    viewType = 3
                }
                list[1].apply {
                    isSelected = false
                    viewType = 1
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 2
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                for (i in 5..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
            1 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 1
                }
                list[1].apply {
                    isSelected = true
                    viewType = 3
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 2
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                for (i in 5..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
            2 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 1
                }
                list[1].apply {
                    isSelected = false
                    viewType = 1
                }
                list[2].apply {
                    isSelected = true
                    viewType = 3
                }
                list[3].apply {
                    isSelected = false
                    viewType = 2
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                for (i in 5..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
            3 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 1
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = true
                    viewType = 3
                }
                list[4].apply {
                    isSelected = false
                    viewType = 2
                }
                list[5].apply {
                    isSelected = false
                    viewType = 2
                }
                for (i in 6..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
            list.size - 2 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 2
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 1
                }
                list[4].apply {
                    isSelected = true
                    viewType = 3
                }
                list[5].apply {
                    isSelected = false
                    viewType = 2
                }
                for (i in 6..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
            list.size - 1 -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 2
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 1
                }
                list[4].apply {
                    isSelected = true
                    viewType = 3
                }
                for (i in 5..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
            else -> {
                list[0].apply {
                    isSelected = false
                    viewType = 2
                }
                list[1].apply {
                    isSelected = false
                    viewType = 2
                }
                list[2].apply {
                    isSelected = false
                    viewType = 1
                }
                list[3].apply {
                    isSelected = false
                    viewType = 1
                }
                list[4].apply {
                    isSelected = true
                    viewType = 3
                }
                list[5].apply {
                    isSelected = false
                    viewType = 2
                }
                list[6].apply {
                    isSelected = false
                    viewType = 2
                }
                for (i in 7..list.size - 1) {
                    list[i].apply {
                        isSelected = false
                        viewType = 4
                    }
                }
            }
        }
    }

    fun checkDotAccessibility(position: Int) {
        /*AccessibilityManager.addAccessInfoRoleDesc(
            mainRV, "Active $position"
        )*/
    }
}
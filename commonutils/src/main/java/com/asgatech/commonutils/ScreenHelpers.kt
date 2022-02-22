package com.asgatech.commonutils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt


/**
 * Screen Helpers
 */
class ScreenHelpers {
    /**
     * Hide the statusBar and set the app to full screen mode
     */
    fun setFullscreen(activity: Activity, viewToHavePadding: View) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> setFullScreenForLatestApis(activity)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> setFullScreenForPieAndLater(activity)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> setFullscreenForLollipopAndLater(
                activity
            )
        }
        paddingTopStatusBar(viewToHavePadding)
    }

    /**
     * Set the full screen mode for devices that have lollipop version or later
     */
    private fun setFullscreenForLollipopAndLater(activity: Activity) {
        @Suppress("DEPRECATION")
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        @Suppress("DEPRECATION")
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity.window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * Set the full screen mode for devices that have R version or later
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun setFullScreenForLatestApis(activity: Activity) {
        activity.window.setDecorFitsSystemWindows(false)
        activity.window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    /**
     * Set the full screen mode for devices that have P(Pie) version or later
     */
    @RequiresApi(Build.VERSION_CODES.P)
    private fun setFullScreenForPieAndLater(activity: Activity) {
        activity.window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }


    /**
     * Obtain the height of the screen
     */
    private fun getScreenHeight(context: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getScreenHeightForRAndLater(context)
        } else {
            getScreenHeightForBelowR(context)
        }
    }

    /**
     * Obtain the height of the screen for devices with android version < R
     */
    private fun getScreenHeightForBelowR(context: Activity): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    /**
     * Obtain the height of the screen for devices with android version > R
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun getScreenHeightForRAndLater(context: Activity): Int {
        val windowMetrics: WindowMetrics = context.windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        return windowMetrics.bounds.height() - insets.top - insets.bottom
    }

    /**
     * Obtain the width of the screen
     */
    fun getScreenWidth(context: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getScreenWidthForLatestApis(context)
        } else {
            getScreenWidthForOlderApis(context)
        }
    }

    /**
     * Obtain the width of the screen for devices with android version < R
     */
    private fun getScreenWidthForOlderApis(context: Activity): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    /**
     * Obtain the width of the screen for devices with android version > R
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun getScreenWidthForLatestApis(context: Activity): Int {
        val windowMetrics: WindowMetrics = context.windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        return windowMetrics.bounds.width() - insets.left - insets.right
    }

    /**
     * Obtain the height of a certain view
     */
    fun getMeasuredHeight(view: View): Int {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        return view.measuredHeight
    }

    /**
     * Obtain the width of a certain view
     */
    fun getMeasuredWidth(view: View): Int {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(view.height, View.MeasureSpec.EXACTLY)
        )
        return view.measuredWidth
    }

    /**
     * Set padding top to a size of the statusBar height
     */
    private fun paddingTopStatusBar(view: View) {
        val paddingTop = getStatusBarHeight(view.context).toDouble()
        view.setPadding(
            view.paddingLeft,
            paddingTop.roundToInt(),
            view.paddingRight,
            view.paddingBottom
        )
    }

    /**
     * Obtain the device statusBar height
     */
    private fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
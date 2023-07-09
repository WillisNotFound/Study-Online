package com.yws.baselib.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Space
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.yws.baselib.services.api.ConfirmDialogBuilder
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.displayMetrics
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.setLinearLayoutParams
import com.yws.baselib.utils.setLinearWeight
import com.yws.baselib.utils.setWidthHeight
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class CommonConfirmDialogFragment : DialogFragment() {

    private companion object {
        private const val TAG = "DefaultConfirmDialog"
    }

    var mBuilder: ConfirmDialogBuilder? = null
    var mContinuation: Continuation<Boolean?>? = null
    private var mIsConfirmed: Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val context = inflater.context
        val builder = mBuilder
        val title = builder?.run { if (messageContent == null) null else title }
        val messageContent = builder?.run { if (messageContent == null) this.title else messageContent }
        val messageContentGravity = builder?.messageContentGravity ?: Gravity.CENTER
        return LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            background = buildCustomShape(radius = 21.dpf, fillColor = 0xFFFFFFFF)

            val borderMargin = 24.dp

            Space(context).addTo(this, 0, 36.dp)

            title?.let {
                TextView(context).addTo(this) {
                    setLinearLayoutParams(
                        MATCH_PARENT,
                        WRAP_CONTENT
                    ) {
                        marginStart = borderMargin
                        marginEnd = borderMargin
                    }

                    gravity = Gravity.CENTER
                    setTextColor(Theme.Color.T1)
                    textSize = 20f
                    if (title.lines().size <= 1) {
                        maxLines = 1
                    }
                    ellipsize = TextUtils.TruncateAt.END
                    typeface = Typeface.DEFAULT_BOLD
                    highlightColor = Color.TRANSPARENT
                    movementMethod = LinkMovementMethod.getInstance()
                    text = title
                }

                Space(context).addTo(this, 0, 16.dp)
            }

            ScrollView(context).addTo(this) {
                setLinearLayoutParams(MATCH_PARENT, WRAP_CONTENT) {
                    marginStart = borderMargin
                    marginEnd = borderMargin
                }
                TextView(context).addTo(this) {
                    setWidthHeight(MATCH_PARENT, WRAP_CONTENT)
                    if (title == null) {
                        minHeight = 40.dp
                    }
                    gravity = messageContentGravity
                    highlightColor = Color.TRANSPARENT
                    movementMethod = LinkMovementMethod.getInstance()
                    setTextColor(Theme.Color.T1)
                    setLineSpacing(0f, 1.1f)
                    textSize = 16f
                    text = messageContent
                }
            }

            Space(context).addTo(this, 0, 36.dp)

            View(context).addTo(this) {
                setWidthHeight(MATCH_PARENT, 1)
                setBackgroundColor(Theme.Color.L1)
            }

            LinearLayout(context).addTo(this) {
                orientation = LinearLayout.HORIZONTAL
                setWidthHeight(MATCH_PARENT, 48.dp)

                builder?.cancelButton?.let { cancelButton ->
                    TextView(context).addTo(this) {
                        setLinearWeight(0, MATCH_PARENT, 1f)
                        gravity = Gravity.CENTER
                        text = cancelButton
                        textSize = 18f
                        setTextColor(builder.cancelButtonTextColor)
                        setOnClickListener {
                            mIsConfirmed = false
                            dismissAllowingStateLoss()
                        }
                    }

                    View(context).addTo(this) {
                        setWidthHeight(1, 16.dp)
                        setBackgroundColor(Theme.Color.L1)
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }

                TextView(context).addTo(this) {
                    setLinearWeight(0, MATCH_PARENT, 1f)
                    gravity = Gravity.CENTER
                    text = builder?.confirmButton
                    textSize = 18f
                    setTextColor(builder?.confirmButtonTextColor ?: 0xFFFF3355.toInt())
                    setOnClickListener {
                        mIsConfirmed = true
                        dismissAllowingStateLoss()
                    }
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.run {
                setBackgroundDrawableResource(android.R.color.transparent)
                setGravity(Gravity.CENTER)
                setLayout(displayMetrics.widthPixels - 84.dp, WindowManager.LayoutParams.WRAP_CONTENT)
            }
            mBuilder?.let {
                setCanceledOnTouchOutside(it.isCanceledOnTouchOutside)
                setCancelable(it.isCancellable)
                if (!it.isCancellable) {
                    setOnKeyListener { _, keyCode, _ ->
                        keyCode == KeyEvent.KEYCODE_BACK
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mContinuation?.run {
            val isConfirmed = mIsConfirmed
            resume(isConfirmed)
        }
        mContinuation = null
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.run {
            attributes = attributes?.apply {
                height = WindowManager.LayoutParams.WRAP_CONTENT
                width = displayMetrics.widthPixels - 84.dp
                dimAmount = 0.4f
            }
        }
    }
}
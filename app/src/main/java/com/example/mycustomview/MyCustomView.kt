package com.example.mycustomview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.mycustomview.databinding.MyCustomViewBinding


class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: MyCustomViewBinding

    init {
        binding = inflateLayout()

        setupAttributes(attrs, defStyleAttr)

        binding.tvActionTitle.setOnClickListener {
            binding.tvActionTitle.text = "Text from code"
            Log.d("MyCustomView_TAG", "tvActionTitle.setOnClickListener{}")

        }
    }

    private fun inflateLayout(): MyCustomViewBinding {
        val inflater = LayoutInflater.from(context)
        return MyCustomViewBinding.inflate(inflater, this)
    }

    private fun setupAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
//        val intArray = intArrayOf(R.attr.text)

        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MyCustomView,
            defStyleAttr,
            0
        )

        try {
//            val text = attributes.getString(R.styleable.MyCustomView_myText)
            val text = attributes.getString(R.styleable.MyCustomView_android_text)
            val hint = attributes.getString(R.styleable.MyCustomView_android_hint)
            val textColor = attributes.getResourceId(R.styleable.MyCustomView_myTextColor, R.color.black)
            val textSize = attributes.getDimensionPixelSize(R.styleable.MyCustomView_myTextSize,-1)

            Log.d("MyCustomView_TAG", "setupAttributes()\n" +
                    "   text: $text\n" +
                    "   textColor: $textColor\n" +
                    "   textSize: $textSize")

            binding.tvActionTitle.text = hint // text
            binding.tvActionTitle.setTextColor(ContextCompat.getColor(context, textColor))
            binding.tvActionTitle.textSize = textSize.toFloat()
        } finally {
            attributes.recycle()
        }


//        val a = context.theme.obtainStyledAttributes(
//            attrs,
//            intArrayOf(android.R.attr.text),
//            defStyleAttr,
//            0
//        )

//        val myText = a.getString(0)
//        binding.tvActionTitle.text = myText
//
//
//        a.recycle()
    }


}
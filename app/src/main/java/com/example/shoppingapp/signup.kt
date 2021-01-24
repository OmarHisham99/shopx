package com.example.shoppingapp

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shoppingapp.databinding.FragmentSignupBinding


class signup : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var binding:FragmentSignupBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
        val txtview =binding.sentence3
        val spannableString = SpannableString(txtview.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.sentence3.setOnClickListener { view->
                    view.findNavController().navigate(R.id.action_signup_to_loginpage)
                }
            }

            override fun updateDrawState(drawState: TextPaint) {
                super.updateDrawState(drawState)
                drawState.isUnderlineText = false
                drawState.color = Color.rgb(224, 143, 98)
            }
        }
        spannableString.setSpan(clickableSpan, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtview.text = spannableString
        txtview.movementMethod = LinkMovementMethod.getInstance()
        txtview.highlightColor = Color.TRANSPARENT

        return binding.root
    }

}
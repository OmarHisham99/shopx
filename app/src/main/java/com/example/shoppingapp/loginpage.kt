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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.shoppingapp.databinding.FragmentLoginpageBinding


class loginpage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginpageBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_loginpage,container,false)
        val txtview = binding.sentence2
        val spannableString = SpannableString(txtview.text)
        val btnLogin= binding.loginBtn
        val tmp = binding.temp


        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.sentence2.setOnClickListener { view->
                    view.findNavController().navigate(R.id.action_loginpage_to_signup) }
            }



            override fun updateDrawState(drawState: TextPaint) {
                super.updateDrawState(drawState)
                drawState.isUnderlineText = false
                drawState.color = Color.rgb(224, 143, 98)
            }
        }
        tmp.setOnClickListener{view->view.findNavController().navigate(R.id.action_loginpage_to_homepage)}
        spannableString.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtview.text = spannableString
        txtview.movementMethod = LinkMovementMethod.getInstance()
        txtview.highlightColor = Color.TRANSPARENT

        return binding.root
    }


    }

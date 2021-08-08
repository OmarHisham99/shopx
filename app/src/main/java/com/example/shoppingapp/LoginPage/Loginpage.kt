package com.example.shoppingapp.LoginPage

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentLoginpageBinding


class loginpage : Fragment() {
    lateinit var binding: FragmentLoginpageBinding
    lateinit var loginViewmodel: LoginViewmodel
    lateinit var txtview:TextView
    lateinit var editText:EditText
    lateinit var spannableString:SpannableString
    public lateinit var progressBar:ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        loginViewmodel = ViewModelProvider(this)[LoginViewmodel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loginpage,container,false)
        txtview = binding.sentence2
        editText = binding.passwordTxt
        spannableString = SpannableString(txtview.text)
        progressBar = binding.progressBar
        setclickablespan()
        togglepass()
        toast()
        setlogin()
        goto()
        return binding.root
    }

    fun setclickablespan()
    {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                binding.sentence2.setOnClickListener { view->
                    view.findNavController().navigate(R.id.action_loginpage_to_signup)
                }
            }

            override fun updateDrawState(drawState: TextPaint) {
                super.updateDrawState(drawState)
                drawState.isUnderlineText = false
                drawState.color = Color.rgb(224, 143, 98)
            }
        }
        spannableString.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtview.text = spannableString
        txtview.movementMethod = LinkMovementMethod.getInstance()
        txtview.highlightColor = Color.TRANSPARENT
    }
    fun toast()
    {
        loginViewmodel.Toast
            ?.observe(viewLifecycleOwner,
                Observer<String?> { string ->
                    if (string != null)
                    {
                        Toast.makeText(activity?.applicationContext, string, Toast.LENGTH_SHORT).show()
                    }
                })
    }


    @SuppressLint("ClickableViewAccessibility")
    fun togglepass()
    {
        binding.passwordTxt.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX + binding.passwordTxt.paddingRight >= binding.passwordTxt.getRight() + - binding.passwordTxt.getCompoundDrawables().get(DRAWABLE_RIGHT).getBounds().width()
                )
                {
                    if(  binding.passwordTxt.inputType!=1)
                        binding.passwordTxt.inputType =  1
                    else
                        binding.passwordTxt.inputType =  InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                    return@OnTouchListener true
                }
            }
            false
        })
    }
    fun setlogin()
    {
           binding.loginBtn.setOnClickListener(View.OnClickListener {
               val Email: String = binding.usernameTxt.getText().toString()
                val password: String = binding.passwordTxt.getText().toString()
                loginViewmodel.login(Email, password)
               progressBar.visibility = View.VISIBLE
           })

    }
    fun goto()
    {
        loginViewmodel.userLiveData
            ?.observe(viewLifecycleOwner,
                Observer { string ->
                    if (string != null) {
                       findNavController().navigate(R.id.action_loginpage_to_holderfrag)
                    }
                    progressBar.visibility = View.GONE

                })
    }

}

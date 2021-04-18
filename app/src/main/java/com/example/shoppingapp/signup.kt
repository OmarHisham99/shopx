package com.example.shoppingapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.Firestore_objs.User
import com.example.ViewModel.LoginViewmodel
import com.example.ViewModel.SignupViewmodel
import com.example.shoppingapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseUser


class signup : Fragment() {
    lateinit var binding:FragmentSignupBinding
     lateinit var txtview:TextView
    var user: User? = null
     lateinit var spannableString:SpannableString
    lateinit var signupViewmodel: SignupViewmodel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        signupViewmodel = ViewModelProvider(this)[SignupViewmodel::class.java]
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
         txtview =binding.sentence3
         spannableString = SpannableString(txtview.text)
         setclickablespan()
         togglepass()
         toast()
         setsignup()
         registeruser()

        return binding.root
    }

    private fun registeruser() {
        signupViewmodel.userLiveData
            ?.observe(viewLifecycleOwner,
                Observer { firebaseuser ->
                    if (firebaseuser != null) {
                        user?.id = firebaseuser.uid
                        user?.let { signupViewmodel.insertuser(it) }
                        findNavController().navigate(R.id.action_signup_to_holderfrag)
                    }
                })
    }

    fun  toast()
    {
        signupViewmodel.toast
            ?.observe(viewLifecycleOwner,
                Observer { string ->
                    if (string != null) {
                        Toast.makeText(activity?.applicationContext, string, Toast.LENGTH_SHORT).show()
                    }
                })
    }
    fun setclickablespan()
    {

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
    }
    @SuppressLint("ClickableViewAccessibility")
    fun togglepass()
    {
        binding.passwordTxt.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX + binding.passwordTxt.paddingRight >= binding.passwordTxt.getRight() + -binding.passwordTxt.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width()
                ) {
                    if (binding.passwordTxt.inputType != 1)
                        binding.passwordTxt.inputType = 1
                    else
                        binding.passwordTxt.inputType =
                            InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                    return@OnTouchListener true
                }
            }
            false
        })
    }
    fun setsignup()
    {
        binding.signupBtn.setOnClickListener(View.OnClickListener {
            val Email: String = binding.emailTxt.getText().toString()
            val password: String = binding.passwordTxt.getText().toString()
            val firstname: String = binding.firstnameTxt.getText().toString()
            val lastname: String = binding.lastnameTxt.getText().toString()
            user = User("",Email,firstname,lastname )
            signupViewmodel.register(user!!, password)
        })
    }

}
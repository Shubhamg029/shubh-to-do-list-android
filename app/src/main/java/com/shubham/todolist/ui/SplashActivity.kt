package com.shubham.todolist.ui

import android.animation.Animator
import android.content.Intent
import com.shubham.todolist.R
import com.shubham.todolist.base.AppBaseActivity
import com.shubham.todolist.databinding.ActivitySplashBinding
import com.shubham.todolist.utils.extensions.startHomeActivity

class SplashActivity : AppBaseActivity<ActivitySplashBinding>() {


    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun onCreateView() {
        super.onCreateView()

        //Init Lottie Anim
        initLottieAnimation()
    }

    private fun initLottieAnimation() {
        binding?.lottieSplash?.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                goToHome()
            }

            override fun onAnimationCancel(animation: Animator) {
                System.currentTimeMillis()
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })
        binding?.lottieSplash?.repeatCount = 1
        binding?.lottieSplash?.playAnimation()
    }

    private fun goToHome() {
        this.startHomeActivity()
        finish()
    }
}
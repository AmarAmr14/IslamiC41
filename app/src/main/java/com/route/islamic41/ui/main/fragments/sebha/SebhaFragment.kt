package com.route.islamic41.ui.main.fragments.sebha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import com.route.islamic41.databinding.FragmentSebhaBinding
import com.route.islamic41.ui.Constants.azkar

class SebhaFragment : Fragment() {

    private var currentRotation = 0f
    private var counter = 0
    private var zekr = 0

    private lateinit var binding: FragmentSebhaBinding

    companion object {
        private const val MAX_COUNTER = 33
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        frameLayout: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSebhaBinding.inflate(inflater, frameLayout, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateZekrText()

        binding.sebhaBody.setOnClickListener {
            sebhaAnimation()
            sebhaCounter()
        }
    }

    private fun sebhaCounter() {
        counter++
        binding.counterTV.text = "$counter"
        if (counter == MAX_COUNTER) {
            counter = 0
            zekr++
            if (zekr >= azkar.size) {
                zekr = 0
            }
            updateZekrText()
        }
    }

    private fun sebhaAnimation() {
        val rotationAngle = 360f / MAX_COUNTER // Dynamic rotation angle
        val nextRotation = currentRotation + rotationAngle

        val rotateAnimation = RotateAnimation(
            currentRotation,
            nextRotation,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 300
        rotateAnimation.fillAfter = true

        binding.sebhaBody.startAnimation(rotateAnimation)
        currentRotation = nextRotation
    }

    private fun updateZekrText() {
        binding.zekrTV.text = if (azkar.isNotEmpty()) azkar[zekr] else "No Azkar Available"
    }
}

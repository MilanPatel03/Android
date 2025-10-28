package com.example.quickecommerce.auth
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Fragment
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quickecommerce.R
import com.example.quickecommerce.Utils
import com.example.quickecommerce.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    //private var _binding: FragmentSignInBinding? = null
    //private val binding get() = _binding!!
    private lateinit var binding : FragmentSignInBinding

    // Keep references to animators so we can stop them when fragment is destroyed
    private val animators = mutableListOf<ObjectAnimator>()
    private var isKeyboardVisible = false


    private val handler = Handler(Looper.getMainLooper())
    private val scrollRunnable1 = Runnable { scrollRow1() }
    private val scrollRunnable2 = Runnable { scrollRow2() }
    private val scrollRunnable3 = Runnable { scrollRow3() }
    private val scrollRunnable4 = Runnable { scrollRow4() }

    // Define scroll speeds and interval
    private val fastSpeed = 2f    // Fast speed for rows 1 and 2 (pixels per update)
    private val slowSpeed = 1f    // Slow speed for rows 3 and 4 (pixels per update)
    private val scrollInterval = 16L // ~60fps (1000ms / 60 ≈ 16ms)

    private var topHalfAlphaAnimator: ValueAnimator? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using view binding
        //_binding = FragmentSignInBinding.inflate(inflater, container, false)
        // Add padding to avoid content overlap with status bar
        //binding.root.setPadding(0, getStatusBarHeight(), 0, 0)

        binding = FragmentSignInBinding.inflate(layoutInflater)

        getUserNummber()
        setupContinueButton()
        disableManualScroll()
        //getStatusBarHeight()
        setupKeyboardListener()
        duplicateImagesForSeamlessLoop()
        startAutoScroll()

        // Listen for text field focus
//        binding.phoneInput.setOnFocusChangeListener { _, hasFocus ->
//            if (hasFocus && !isKeyboardVisible) {
//                // User tapped on text field but keyboard not yet visible
//                // We can prepare for the fade, but wait for keyboard detection
//            }
//        }

        return binding.root
    }

    private fun getUserNummber() {
        binding.phoneInput.addTextChangedListener (
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
                ) {


                }

                override fun onTextChanged(
                    number: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
                ) {

                    val len = number?.length

                    if (len == 10){
                        binding.continueButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
                    } else {
                        binding.continueButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grayish_blue))
                    }
                }

            }
        )
    }

    private fun duplicateImagesForSeamlessLoop() {
        // Duplicate images in each row for seamless looping
        listOf(
            binding.row1Container to listOf(R.drawable.amulic1, R.drawable.corneto1, R.drawable.attta1, R.drawable.tea),
            binding.row2Container to listOf(R.drawable.magggi1, R.drawable.organic_premium, R.drawable.milma_milk, R.drawable.instant),
            binding.row3Container to listOf(R.drawable.baby, R.drawable.home_office, R.drawable.munchies, R.drawable.pet_care),
            binding.row4Container to listOf(R.drawable.dairy_breakfast, R.drawable.cold_and_juices, R.drawable.sauce_spreads, R.drawable.cleaning)
        ).forEach { (container, images) ->
            // Add duplicate set of images
            images.forEach { imageRes ->
                val frameLayout = layoutInflater.inflate(R.layout.item_image, container, false)
                frameLayout.findViewById<ImageView>(R.id.imageView).setImageResource(imageRes)
                container.addView(frameLayout)
            }
        }
    }

    private fun startAutoScroll() {
        handler.post(scrollRunnable1)
        handler.post(scrollRunnable2)
        handler.post(scrollRunnable3)
        handler.post(scrollRunnable4)
    }

    private fun scrollRow1() {
        binding.row1Scroll.scrollBy(fastSpeed.toInt(), 0)
        checkLoop(binding.row1Scroll, binding.row1Container)
        handler.postDelayed(scrollRunnable1, scrollInterval)
    }

    private fun scrollRow2() {
        binding.row2Scroll.scrollBy(-fastSpeed.toInt(), 0)
        checkLoop(binding.row2Scroll, binding.row2Container)
        handler.postDelayed(scrollRunnable2, scrollInterval)
    }

    private fun scrollRow3() {
        binding.row3Scroll.scrollBy(slowSpeed.toInt(), 0)
        checkLoop(binding.row3Scroll, binding.row3Container)
        handler.postDelayed(scrollRunnable3, scrollInterval)
    }

    private fun scrollRow4() {
        binding.row4Scroll.scrollBy(-slowSpeed.toInt(), 0)
        checkLoop(binding.row4Scroll, binding.row4Container)
        handler.postDelayed(scrollRunnable4, scrollInterval)
    }

    private fun checkLoop(scrollView: HorizontalScrollView, container: LinearLayout) {
        val scrollX = scrollView.scrollX
        val totalWidth = container.width
        val setWidth = totalWidth / 2 // Since we duplicated the images

        if (scrollX >= setWidth) {
            scrollView.scrollTo(scrollX - setWidth, 0)
        } else if (scrollX <= 0) {
            scrollView.scrollTo(setWidth, 0)
        }
    }

    override fun onPause() {
        super.onPause()
        // Stop scrolling when fragment is paused
        handler.removeCallbacks(scrollRunnable1)
        handler.removeCallbacks(scrollRunnable2)
        handler.removeCallbacks(scrollRunnable3)
        handler.removeCallbacks(scrollRunnable4)
    }

    override fun onResume() {
        super.onResume()
        // Resume scrolling when fragment is resumed
        startAutoScroll()
    }







    private fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }


    private fun disableManualScroll() {
        // Disable touch scrolling on all HorizontalScrollViews
        listOf(binding.row1Scroll, binding.row2Scroll, binding.row3Scroll, binding.row4Scroll).forEach { scrollView ->
            scrollView.setOnTouchListener { _, _ ->
                // Consume all touch events to prevent manual scrolling
                true
            }
        }
    }


    /**private fun setupAnimations() {
    // Load animations
    val scrollLeftFast = AnimationUtils.loadAnimation(requireContext(), R.anim.scroll_left_fast)
    val scrollRightFast = AnimationUtils.loadAnimation(requireContext(), R.anim.scroll_right_fast)
    val scrollLeftSlow = AnimationUtils.loadAnimation(requireContext(), R.anim.scroll_left_slow)
    val scrollRightSlow = AnimationUtils.loadAnimation(requireContext(), R.anim.scroll_right_slow)


    // Apply animations to rows
    binding.row1Container.startAnimation(scrollLeftFast)
    binding.row2Container.startAnimation(scrollRightFast)
    binding.row3Container.startAnimation(scrollLeftSlow)
    binding.row4Container.startAnimation(scrollRightSlow)


    //    Optional: Adjust animation delta dynamically based on image width
    //        binding.row1Container.post {
    //            val imageWidth = binding.row1Container.getChildAt(0)?.width?.toFloat() ?: 100f
    //            scrollLeftFast.toXDelta = (-4 * imageWidth)
    //            scrollRightFast.toXDelta = (4 * imageWidth)
    //            scrollLeftSlow.toXDelta = (-4 * imageWidth)
    //            scrollRightSlow.toXDelta = (4 * imageWidth)
    //        }
    }**/



    private fun setupKeyboardListener() {
        val rootView = binding.root
        rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                rootView.getWindowVisibleDisplayFrame(rect)
                val screenHeight = rootView.height
                val keypadHeight = screenHeight - rect.bottom

                val keyboardThreshold = 150 * resources.displayMetrics.density // 150dp threshold

                if (keypadHeight > keyboardThreshold) {
                    // Keyboard is visible
                    if (!isKeyboardVisible) {
                        isKeyboardVisible = true
                        fadeTopHalfOut()
                    }
                } else {
                    // Keyboard is hidden
                    if (isKeyboardVisible) {
                        isKeyboardVisible = false
                        fadeTopHalfIn()
                    }
                }
            }
        })
    }

    private fun fadeTopHalfOut() {
        // Cancel any ongoing animation
        topHalfAlphaAnimator?.cancel()

        topHalfAlphaAnimator = ValueAnimator.ofFloat(binding.topHalf.alpha, 0.2f).apply {
            duration = 300L // Fixed: Use 'this.duration' instead of 'ValueAnimator.setDuration'
            addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                binding.topHalf.alpha = alpha
            }
            doOnEnd {
                // Stop all scrolling animations when fully faded
                pauseScrolling()
            }
            start()
        }
    }

    private fun fadeTopHalfIn() {
        // Cancel any ongoing animation
        topHalfAlphaAnimator?.cancel()

        topHalfAlphaAnimator = ValueAnimator.ofFloat(binding.topHalf.alpha, 1.0f).apply {
            duration = 400L // Fixed: Use 'this.duration' instead of 'ValueAnimator.setDuration'
            addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                binding.topHalf.alpha = alpha
            }
            doOnEnd {
                // Resume scrolling animations when fully visible
                resumeScrolling()
            }
            start()
        }
    }

    private fun pauseScrolling() {
        // Pause all scroll runnables but keep them scheduled
        handler.removeCallbacks(scrollRunnable1)
        handler.removeCallbacks(scrollRunnable2)
        handler.removeCallbacks(scrollRunnable3)
        handler.removeCallbacks(scrollRunnable4)
    }

    private fun resumeScrolling() {
        // Resume scrolling from current positions
        handler.post(scrollRunnable1)
        handler.post(scrollRunnable2)
        handler.post(scrollRunnable3)
        handler.post(scrollRunnable4)
    }




    private fun setupContinueButton() {
        binding.continueButton.setOnClickListener {
            val phoneNumber = binding.phoneInput.text.toString()
            if (phoneNumber.isEmpty() || phoneNumber.length != 10) {
                Utils.showToast(requireContext(),"Please enter valid phone number")
            } else {
                val bundle = Bundle()
                bundle.putString("number", phoneNumber)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment, bundle)
            }
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        animators.forEach { it.cancel() }
//        animators.clear()
//        _binding = null
//    }

}
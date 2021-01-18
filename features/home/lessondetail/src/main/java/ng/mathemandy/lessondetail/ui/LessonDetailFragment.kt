package ng.mathemandy.lessondetail.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout

import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_LANDSCAPE
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_PORTRAIT
import ng.groove.exovideoview.ui.ExoVideoPlaybackControlView
import ng.mathemandy.lessondetail.R
import ng.mathemandy.lessondetail.databinding.FragmentLessonDetailBinding
import ng.mathemandy.lessondetail.ui.video.ExoplayerVideoManager
import ng.mathemandy.ulesson.navigation.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Provider


class LessonDetailFragment  : Fragment (){


    private  lateinit var  binding : FragmentLessonDetailBinding
    private var orientation: Int = 0
    private val lesson by lazy { LessonDetailFragmentArgs.fromBundle(requireArguments()).lesson }
    private val window: Window by lazy { requireActivity().window }

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_detail, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVideoView()
        setupClickEvents()
        initViews()
        startVideo()
    }


    fun startVideo(){
        ExoplayerVideoManager.getInstance().onNewIntent()
        ExoplayerVideoManager.getInstance().init(
            requireContext(), listOf(
                lesson.media_url ?: ""
            ), 0
        )
        ExoplayerVideoManager.getInstance().prepareExoplayer(binding.videoView)
        ExoplayerVideoManager.getInstance().gotoForeground()
    }



    private fun initViews(){
        binding.lessonTitle.text = lesson.name
        binding.lessonSubtitle.text = "Rational Numbers"
    }


    private fun initVideoView(){
        binding.videoView.isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        with(binding.videoView) {
            setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)
            setBackListener(object : ExoVideoPlaybackControlView.ExoClickListener {
                override fun onClick(view: View?, isPortrait: Boolean): Boolean {
                    if (isPortrait) {
//                        finish()
                    }
                    return false
                }
            })
            setOrientationListener(object : ExoVideoPlaybackControlView.OrientationListener {
                override fun onOrientationChanged(orientation: Int) {
                    if (orientation == SENSOR_PORTRAIT) {
                        changeToPortrait()
                        binding.videoView.showController()
                    } else if (orientation == SENSOR_LANDSCAPE) {
                        changeToLandscape()
                    }
                }
            })

            setControllerVisibilityListener(object : ExoVideoPlaybackControlView.VisibilityListener {
                override fun onVisibilityChange(visibility: Int) {
                    if (visibility == View.VISIBLE) {
                        binding.toolbar?.visibility = View.VISIBLE
                    } else {
                        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                            binding.toolbar.visibility = View.INVISIBLE
                        }
                    }
                }
            })

        }

    }


    fun changeToLandscape(){
        val lp = window.attributes
//        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        val window = window
        window.attributes = lp
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        hideViewsOnConfigChanges()

    }

    private fun changeToPortrait(){
        val attr = window.attributes
        val window = window
        window.attributes = attr
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        showViewsOnConfigChanges()

    }




    private fun setupClickEvents(){
        binding.toolbar.setOnClickListener {
            navigator.get().goBack()
        }
    }

    override fun onStart() {
        super.onStart()
        ExoplayerVideoManager.getInstance().gotoForeground()
    }

    override fun onResume() {
        super.onResume()
        ExoplayerVideoManager.getInstance().onResume()
    }

    public override fun onPause() {
        super.onPause()
        ExoplayerVideoManager.getInstance().gotoBackground()
    }

    public override fun onStop() {
        super.onStop()
        ExoplayerVideoManager.getInstance().onStop()

    }


    override fun onDestroy() {
        super.onDestroy()
        ExoplayerVideoManager.getInstance().releaseVideoPlayer()
    }
}
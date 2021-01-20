package ng.mathemandy.lessondetail.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Renderer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_LANDSCAPE
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_PORTRAIT
import ng.groove.exovideoview.ui.ExoVideoPlaybackControlView
import ng.mathemandy.lessondetail.R
import ng.mathemandy.lessondetail.databinding.FragmentLessonDetailBinding
import ng.mathemandy.lessondetail.di.inject
import ng.mathemandy.lessondetail.ui.video.ExoplayerVideoManager
import ng.mathemandy.ulesson.navigation.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Provider


class LessonDetailFragment : Fragment() {


    private lateinit var binding: FragmentLessonDetailBinding
    private var orientation: Int = 0
    private val lesson by lazy { LessonDetailFragmentArgs.fromBundle(requireArguments()).lesson }
    private val window: Window by lazy { requireActivity().window }

    private var playWhenReady = true
    private var mResumeWindow: Int = 0
    private var mResumePosition: Long = 0

    private val STATE_RESUME_WINDOW = "mResumeWindow"
    private val STATE_RESUME_POSITION = "mResumePosition"
    private val STATE_PLAYING = "mPlaying"

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: LessonDetailViewModel by viewModels { factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.saveLesson(lesson)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_detail, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVideoView()
        setupClickEvents()
        initViews()
        startVideo()
        startListener()
    }

    private fun startListener() {
        ExoplayerVideoManager.getInstance().getPlayer()?.addListener(object : Player.EventListener {
            override fun onPlaybackStateChanged(state: Int) {
                binding.videoView.keepScreenOn =
                    !(state == Player.STATE_IDLE || state == Player.STATE_ENDED)
            }
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(this)
    }

    private fun startVideo() {
        ExoplayerVideoManager.getInstance().onNewIntent()
        ExoplayerVideoManager.getInstance().init(
            requireContext(), listOf(
                lesson.media_url ?: ""
            ), 0
        )
        ExoplayerVideoManager.getInstance().prepareExoplayer(binding.videoView)
        ExoplayerVideoManager.getInstance().gotoForeground()
    }

    private fun initViews() {
        binding.lessonTitle.text = lesson.name
        binding.lessonSubtitle.text = "Rational Numbers"
    }


    private fun initVideoView() {

        binding.videoView.isPortrait =
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

        with(binding.videoView) {
            setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)
            setBackListener(object : ExoVideoPlaybackControlView.ExoClickListener {
                override fun onClick(view: View?, isPortrait: Boolean): Boolean {
                    if (isPortrait) {
                        navigator.get().goBack()
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

            setControllerVisibilityListener(object :
                ExoVideoPlaybackControlView.VisibilityListener {
                override fun onVisibilityChange(visibility: Int) {
                    if (visibility == View.VISIBLE) {
                        binding.toolbar.visibility = View.VISIBLE
                    } else {
                        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                            binding.toolbar.visibility = View.INVISIBLE

                        }
                    }
                }
            })

        }

    }


    fun changeToLandscape() {
        val lp = window.attributes
        val window = window
        window.attributes = lp
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        hideViewsOnConfigChanges()

    }

    private fun changeToPortrait() {
        val attr = window.attributes
        val window = window
        window.attributes = attr
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        showViewsOnConfigChanges()

    }

    private fun hideViewsOnConfigChanges() {
        binding.otherView.visibility = View.GONE
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.videoContainer)
        constraintSet.clear(R.id.video_view, ConstraintSet.BOTTOM)
        //video_view
        constraintSet.connect(
            R.id.video_view,
            ConstraintSet.BOTTOM,
            R.id.videoContainer,
            ConstraintSet.BOTTOM
        )
        constraintSet.applyTo(binding.videoContainer)
    }

    fun showViewsOnConfigChanges() {
        binding.otherView.visibility = View.VISIBLE
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.videoContainer)
        constraintSet.clear(R.id.video_view, ConstraintSet.BOTTOM)
        //video_view
        constraintSet.connect(
            R.id.video_view,
            ConstraintSet.BOTTOM,
            R.id.guideline_center,
            ConstraintSet.TOP
        )
        constraintSet.applyTo(binding.videoContainer)
    }


    private fun setupClickEvents() {
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
        try {
            ExoplayerVideoManager.getInstance().getPlayer()?.videoScalingMode =
                Renderer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
            binding.videoView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL)
        } catch (e: Exception) {
            Log.e(TAG, "$e")
        }
    }

    override fun onPause() {
        super.onPause()
        ExoplayerVideoManager.getInstance().gotoBackground()
    }

    override fun onStop() {
        super.onStop()
        ExoplayerVideoManager.getInstance().onStop()

    }

    companion object {
        private val TAG = this.javaClass.simpleName
    }

    override fun onDestroy() {
        super.onDestroy()
        ExoplayerVideoManager.getInstance().releaseVideoPlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (ExoplayerVideoManager.getInstance().getPlayer() == null) {
            return
        }
        mResumePosition = ExoplayerVideoManager.getInstance().getPlayer()?.currentPosition!!
        mResumeWindow = ExoplayerVideoManager.getInstance().getPlayer()?.currentWindowIndex!!
        playWhenReady = ExoplayerVideoManager.getInstance().getPlayer()?.playWhenReady!!
        outState.putInt(STATE_RESUME_WINDOW, mResumeWindow)
        outState.putLong(STATE_RESUME_POSITION, mResumePosition)
        outState.putBoolean(STATE_PLAYING, playWhenReady)
    }
}
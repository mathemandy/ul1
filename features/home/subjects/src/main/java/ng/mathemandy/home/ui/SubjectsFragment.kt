package ng.mathemandy.home.ui


import GridSpacingItemDecoration
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.resource.AppStatus
import ng.mathemandy.home.R
import ng.mathemandy.home.databinding.FragmentSubjectsBinding
import ng.mathemandy.home.di.inject
import ng.mathemandy.home.presentation.SubjectsViewModel
import ng.mathemandy.model.LessonModel
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.ulesson.navigation.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Provider


class SubjectsFragment : Fragment(){

    private lateinit var binding: FragmentSubjectsBinding

    @Inject
    lateinit var subjectAdapter: SubjectsAdapter

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: SubjectsViewModel by viewModels { factory }

    @Inject
    lateinit var recentVideosAdapter: RecentVideosAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subjects, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerview()
        initDataListener()
    }

    private fun initDataListener() {
        viewModel.subjectsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                AppStatus.FAILED -> {
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()
                }
                AppStatus.EMPTY -> {
                    Toast.makeText(requireContext(), "empty", Toast.LENGTH_SHORT).show()
                }
                AppStatus.LOADING -> {
                    binding.subjectsFlipper.displayedChild = 0
                }
                AppStatus.SUCCESS -> {
                    it.data?.let { it1 -> renderSuccessState(it1) }
                }
            }
        }
    }

    private fun renderSuccessState(data: List<SubjectModel>) {
        binding.subjectsFlipper.displayedChild = 1
        subjectAdapter.swapData(data, null)
    }


    private fun setUpRecyclerview() {
        val spanCount = 2
        val spacing = 25
        val includeEdge = false

        binding.subjectRv.apply {
            adapter = subjectAdapter.apply {
                clickListener = navigator.get()::openChapters
            }
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }

        binding.recentlyWatchedRv.apply {
            adapter = recentVideosAdapter.apply {
                clickListener = navigator.get()::watchLesson
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }


        recentVideosAdapter.swapData(MutableList(5) {
            LessonModel(
                it, "vvvffg",
                "https://ulesson-staging.s3.eu-west-2.amazonaws.com/lesson_icons/icons/defaults/thumb/lesson.png",
                "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/70\\/original\\/stapler-bRXerd.MP4",
                85, 85
            )
        }, null)

    }
}
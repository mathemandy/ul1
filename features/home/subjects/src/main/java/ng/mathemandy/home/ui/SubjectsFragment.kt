package ng.mathemandy.home.ui


import GridSpacingItemDecoration
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ng.mathemandy.home.R
import ng.mathemandy.home.databinding.FragmentSubjectsBinding
import ng.mathemandy.home.di.inject
import ng.mathemandy.ulesson.base.Lessons
import ng.mathemandy.ulesson.base.Subjects
import javax.inject.Inject

class SubjectsFragment : Fragment(), SubjectsAdapter.Interaction, RecentVideosAdapter.Interaction {

    private lateinit var binding: FragmentSubjectsBinding


    @Inject
    lateinit var  subjectAdapter: SubjectsAdapter


    private val recentVideosAdapter: RecentVideosAdapter by lazy {
        RecentVideosAdapter(
            this
        )
    }

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
    }


    private fun setUpRecyclerview() {
//        retry_btn.setOnClickListener {
//            filterViewModel.getFilters()
//            renderLoadingState()
//        }


        val spanCount = 2
        val spacing  = 50
        val includeEdge = false

        binding.subjectRv.apply {
            adapter = subjectAdapter
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
           layoutManager  = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }

        subjectAdapter.swapData(MutableList(5) {
            Subjects(it, "Mathematics", "https://ulesson-staging.s3.eu-west-2.amazonaws.com/lesson_icons/icons/defaults/thumb/lesson.png", emptyList())
        }, null)
//        renderSuccessState()

        binding.recentlyWatchedRv.apply {
            adapter = recentVideosAdapter
            layoutManager  = LinearLayoutManager(context,  RecyclerView.VERTICAL, false)
        }

        recentVideosAdapter.swapData(MutableList(5) {
            Lessons(it, "vvvffg",
                "https://ulesson-staging.s3.eu-west-2.amazonaws.com/lesson_icons/icons/defaults/thumb/lesson.png",
                "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/70\\/original\\/stapler-bRXerd.MP4",
                85, 85)
        }, null)

    }

    override fun itemClicked(item: Subjects) {

    }

    override fun itemClicked(item: Lessons) {

    }
}
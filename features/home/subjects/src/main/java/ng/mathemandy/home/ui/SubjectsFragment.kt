package ng.mathemandy.home.ui


import GridSpacingItemDecoration
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ng.mathemandy.domain.resource.AppStatus
import ng.mathemandy.home.R
import ng.mathemandy.home.databinding.FragmentSubjectsBinding
import ng.mathemandy.home.di.inject
import ng.mathemandy.home.presentation.SubjectsViewModel
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.model.LessonAndSubjectModel
import ng.mathemandy.ulesson.navigation.NavigationDispatcher
import ng.mathemandy.ulesson.ui.MainActivity
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
        clickEvent()
    }


    private fun clickEvent(){
        binding.viewAllButton.setOnClickListener {
            val text  = binding.viewAllButton.text
            if (text == "View All"){
                binding.viewAllButton.text =  "View Less"
                viewModel.viewMoreClicked(false)
            }else {
                binding.viewAllButton.text =  "View All"
                viewModel.viewMoreClicked(true)
            }
        }
    }

    private fun initDataListener() {
        viewModel.subjectsLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                AppStatus.FAILED -> {
                   binding.subjectsFlipper.displayedChild = 3
                    binding.errorContentLayoutHome.description.text = it.message
                 }
                AppStatus.EMPTY -> {
                    binding.subjectsFlipper.displayedChild  = 2
                }
                AppStatus.LOADING -> {
                    binding.subjectsFlipper.displayedChild = 0
                }
                AppStatus.OFFLINE -> {
                    it.data?.let { it1 -> renderSuccessState(it1) }
                    it.message?.let { it1 -> showSnackBar(it1,true) }
                }

                AppStatus.SUCCESS, AppStatus.LOADING_WITH_DATA -> {
                    it.data?.let { it1 -> renderSuccessState(it1) }
                }
            }
        }

        viewModel.recentTopicsLiveData.observe(viewLifecycleOwner){
            when(it.status){
                AppStatus.FAILED -> {
                    binding.recentlyWatchedFlipper.displayedChild = 3
                    it.message?.let { it1 -> showSnackBar(it1,true) }
                    Log.e(TAG, "${it.message}")
                }
                AppStatus.LOADING -> {
                    binding.recentlyWatchedFlipper.displayedChild = 0
                }

                AppStatus.SUCCESS -> {
                    it.data?.let { it1 -> renderSuccessStateRecentlyWatched(it1) }
                }

                AppStatus.EMPTY -> {
                    binding.recentlyWatchedFlipper.displayedChild  = 2
                    binding.emptyContentLayoutRecentlyWatched.description.text  =  "Lessons you watched will Appear here"

                }
            }
        }

        viewModel.viewMoreLiveData.observe(viewLifecycleOwner){
            binding.viewMoreContainer.visibility  = if (it) View.VISIBLE else View.GONE

        }

    }

    fun showSnackBar(message: String, isError: Boolean){
        (requireActivity() as MainActivity).showSnackBar(binding.root,message, isError )
    }

    private fun renderSuccessState(data: List<SubjectModel>) {
        binding.subjectsFlipper.displayedChild = 1
        subjectAdapter.swapData(data, null)
    }
    private fun renderSuccessStateRecentlyWatched(data: List<LessonAndSubjectModel>) {
        binding.recentlyWatchedFlipper.displayedChild = 1
        recentVideosAdapter.swapData(data, null)
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

    }

    companion object {
        private  val TAG = this::class.simpleName
    }
}
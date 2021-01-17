package ng.mathemandy.lessons.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.OnAsyncUpdateListener
import ng.mathemandy.lessons.R
import ng.mathemandy.lessons.databinding.FragmentLessonBinding
import ng.mathemandy.lessons.di.inject
import ng.mathemandy.lessons.ui.LessonsFragmentArgs.fromBundle
import ng.mathemandy.lessons.ui.adapteritems.SectionAdapter
import ng.mathemandy.ulesson.navigation.NavigationDispatcher
import javax.inject.Inject
import javax.inject.Provider

class LessonsFragment : Fragment() {

    private lateinit var binding: FragmentLessonBinding

    private val subject by lazy { fromBundle(requireArguments()).subject }

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    @Inject
    lateinit var chapterAdapter: SectionAdapter

    private lateinit var groupLayoutManager: GridLayoutManager

    private val async = OnAsyncUpdateListener {

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lesson, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        initdataListener()
        setupClickEvent()
    }

    private fun setupClickEvent(){
        binding.toolbar.setNavigationOnClickListener {
            navigator.get().goBack()
        }

    }

    private fun setupRecyclerview() {
        groupLayoutManager = GridLayoutManager(context, chapterAdapter.spanCount).apply {
            spanSizeLookup = chapterAdapter.spanSizeLookup
        }
        binding.lessonRecyclerView.apply {
            layoutManager = groupLayoutManager
            adapter = chapterAdapter
            setItemViewCacheSize(3)
        }
    }

    private fun initdataListener() {
        chapterAdapter.apply {
            lessonClickListener = navigator.get()::watchLesson
            setItems(subject.chapters, async)
        }

    }
}
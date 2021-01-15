package ng.mathemandy.home.ui


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
import ng.mathemandy.ulesson.base.Subjects

class SubjectsFragment : Fragment(), SubjectsAdapter.Interaction {

    private lateinit var binding: FragmentSubjectsBinding

    private val adapter: SubjectsAdapter by lazy { SubjectsAdapter(this) }


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

        binding.subjectRv.adapter = adapter
        binding.subjectRv.layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        adapter.swapData(MutableList(5) {
            Subjects(it, "Mathematics", "https://ulesson-staging.s3.eu-west-2.amazonaws.com/lesson_icons/icons/defaults/thumb/lesson.png", emptyList())
        }, null)
//        renderSuccessState()

    }

    override fun itemClicked(item: Subjects) {

    }
}
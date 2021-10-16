package com.thescreamer74.mycvandroid.presentation.ui.graduation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.GraduationFragmentBinding
import com.thescreamer74.mycvandroid.presentation.ui.adapter.GraduationItemAdapter
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import org.koin.core.parameter.parametersOf

@KoinApiExtension
class GraduationFragment : Fragment() {
    private val viewModel: GraduationViewModel by inject { parametersOf(this) }
    private lateinit var binding: GraduationFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container : ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.graduation_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = resources.getString(R.string.graduation)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.graduationListRecyclerView.adapter = GraduationItemAdapter(GraduationItemAdapter.OnClickListener{
            Toast.makeText(this.context, "item $it", Toast.LENGTH_LONG).show()

        })

    }
}
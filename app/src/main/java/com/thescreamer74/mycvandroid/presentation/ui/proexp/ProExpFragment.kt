package com.thescreamer74.mycvandroid.presentation.ui.proexp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.ProExpFragmentBinding
import com.thescreamer74.mycvandroid.presentation.ui.adapter.ExperienceItemAdapter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProExpFragment : Fragment() {
    private val viewModel: ProExpViewModel by inject { parametersOf(this) }
    private lateinit var binding: ProExpFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container : ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.pro_exp_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.graduationListRecyclerView.adapter = ExperienceItemAdapter(ExperienceItemAdapter.OnClickListener{
            Toast.makeText(this.context, "item $it", Toast.LENGTH_LONG).show()
        })
    }
}
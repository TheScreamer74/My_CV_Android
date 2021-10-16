package com.thescreamer74.mycvandroid.presentation.ui.aboutme

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thescreamer74.mycvandroid.R
import com.thescreamer74.mycvandroid.databinding.AboutMeFragmentBinding
import com.thescreamer74.mycvandroid.presentation.ui.adapter.GraduationItemAdapter
import com.thescreamer74.mycvandroid.presentation.ui.adapter.HobbyItemAdapter
import com.thescreamer74.mycvandroid.presentation.ui.adapter.LanguageItemAdapter
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import org.koin.core.parameter.parametersOf

@KoinApiExtension
class AboutMeFragment : Fragment() {

    private val viewModel: AboutMeViewModel by inject { parametersOf(this) }
    private lateinit var binding: AboutMeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container : ViewGroup?, savedInstanceState: Bundle?) : View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.about_me_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = resources.getString(R.string.about_me)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonLinkedin.setOnClickListener {
            viewModel.personal.value?.data?.results?.webProfiles?.linkedin?.let { it1 -> openLink(it1) }
        }
        binding.buttonGithub.setOnClickListener {
            viewModel.personal.value?.data?.results?.webProfiles?.github?.let { it1 -> openLink(it1) }
        }
        binding.callMeButton.setOnClickListener {
            viewModel.personal.value?.data?.results?.phone?.let { it1 -> callMe(it1) }
        }

        binding.languageListRecyclerView.adapter = LanguageItemAdapter(LanguageItemAdapter.OnClickListener {
            Toast.makeText(this.context, "item $it", Toast.LENGTH_LONG).show()
        })

        binding.recyclerViewHobbies.adapter = HobbyItemAdapter(HobbyItemAdapter.OnClickListener{
            Toast.makeText(this.context, "item $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun openLink(path: String){
        val uri = Uri.parse(path)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun callMe(phone: String){
        val uri = Uri.parse("tel:$phone")
        val intent = Intent(Intent.ACTION_CALL, uri)
        startActivity(intent)
    }
}
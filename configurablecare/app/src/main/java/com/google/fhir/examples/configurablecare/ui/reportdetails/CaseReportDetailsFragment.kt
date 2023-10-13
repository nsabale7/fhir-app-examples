package com.google.fhir.examples.configurablecare.ui.reportdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.fhir.examples.configurablecare.MainActivity
import com.google.fhir.examples.configurablecare.R
import com.google.fhir.examples.configurablecare.databinding.FragmentCaseReportDetailsBinding
import com.google.fhir.examples.configurablecare.ui.reportlist.CaseReportItemRecyclerViewAdapter
import com.google.fhir.examples.configurablecare.ui.reportlist.CaseReportListViewModel

class CaseReportDetailsFragment : Fragment() {

  private lateinit var adapter: CaseReportDetailsRecyclerViewAdapter

  private val viewModel : CaseReportDetailsViewModel by viewModels()

  private var _binding: FragmentCaseReportDetailsBinding? = null
  private val binding
    get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentCaseReportDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (requireActivity() as AppCompatActivity).supportActionBar?.apply {
      title = resources.getString(R.string.case_report_details)
      setDisplayHomeAsUpEnabled(true)
    }
    setupRecyclerView()
    observeCaseReports()
    (activity as MainActivity).setDrawerEnabled(false)
  }
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      android.R.id.home -> {
        NavHostFragment.findNavController(this).navigateUp()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun setupRecyclerView() {
    adapter = CaseReportDetailsRecyclerViewAdapter()
    binding.rvCaseReportDetails.adapter = adapter
  }

  private fun observeCaseReports() {
    viewModel.caseReportDetailsData.observe(viewLifecycleOwner) {
      adapter.submitList(it)
    }
  }

}
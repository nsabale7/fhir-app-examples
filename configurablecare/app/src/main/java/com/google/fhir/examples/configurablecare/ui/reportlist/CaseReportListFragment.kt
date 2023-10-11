package com.google.fhir.examples.configurablecare.ui.reportlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.fhir.examples.configurablecare.R
import com.google.fhir.examples.configurablecare.databinding.FragmentCaseReportListBinding

class CaseReportListFragment : Fragment() {

  private lateinit var adapter: CaseReportItemRecyclerViewAdapter

  private val viewModel : CaseReportListViewModel by viewModels()

  private var _binding: FragmentCaseReportListBinding? = null
  private val binding
    get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentCaseReportListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
    observeCaseReports()
  }

  private fun setupRecyclerView() {
    adapter = CaseReportItemRecyclerViewAdapter {
      findNavController().navigate(CaseReportListFragmentDirections.actionCaseReportListFragmentToCaseReportDetailsFragment(it.questionnaireResponseId))
    }
    binding.caseReportListContainer.caseReportList.adapter = adapter
  }

  private fun observeCaseReports() {
    viewModel.caseReportList.observe(viewLifecycleOwner) {
      println("*** ${it}")
      adapter.submitList(it)
      binding.caseReportListContainer.reportCount.text = getString(R.string.d_case_reports, it.size)
    }
  }

}
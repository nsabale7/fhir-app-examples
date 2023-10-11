package com.google.fhir.examples.configurablecare.ui.reportdetails;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.fhir.examples.configurablecare.R
import com.google.fhir.examples.configurablecare.databinding.CaseReportDetailExpandleViewBinding
import com.google.fhir.examples.configurablecare.databinding.CaseReportDetailsOverviewBinding

class CaseReportDetailsRecyclerViewAdapter() :
  ListAdapter<CaseReportDetailsData, CaseReportDetailsViewHolder>(CaseReportDetailsDiffUtil()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseReportDetailsViewHolder {
    return when (ViewTypes.from(viewType)) {
      ViewTypes.OVERVIEW -> CaseReportDetailsOverviewViewHolder(
        CaseReportDetailsOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )

      ViewTypes.EXPANDABLE_VIEW -> CaseReportDetailsExpandableViewHolder(
        CaseReportDetailExpandleViewBinding.inflate(
          LayoutInflater.from(parent.context),
          parent,
          false
        )
      )
    }
  }

  override fun onBindViewHolder(holder: CaseReportDetailsViewHolder, position: Int) {
    val model = getItem(position)
    holder.bind(model)
  }

  override fun getItemViewType(position: Int): Int {
    val item = getItem(position)
    return when (item) {
      is CaseReportDetailsOverviewData -> ViewTypes.OVERVIEW
      is CaseReportDetailsExpandableViewData -> ViewTypes.EXPANDABLE_VIEW
      else -> {
        throw IllegalArgumentException("Undefined Item type $item")
      }
    }.ordinal
  }
}

class CaseReportDetailsDiffUtil : DiffUtil.ItemCallback<CaseReportDetailsData>() {
  override fun areItemsTheSame(
    oldItem: CaseReportDetailsData,
    newItem: CaseReportDetailsData
  ): Boolean {
    return when (oldItem) {
      is CaseReportDetailsOverviewData -> {
        if (newItem !is CaseReportDetailsOverviewData) false
        else oldItem.caseReportItem.questionnaireResponseId == newItem.caseReportItem.questionnaireResponseId
      }

      is CaseReportDetailsExpandableViewData -> {
        if (newItem !is CaseReportDetailsExpandableViewData) false
        else oldItem.reportPropertyList.size == newItem.reportPropertyList.size
      }

      else -> oldItem == newItem
    }
  }

  override fun areContentsTheSame(
    oldItem: CaseReportDetailsData,
    newItem: CaseReportDetailsData
  ): Boolean {
    return areItemsTheSame(oldItem, newItem)
  }

}

abstract class CaseReportDetailsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
  abstract fun bind(data: CaseReportDetailsData)
}

class CaseReportDetailsOverviewViewHolder(
  private val binding: CaseReportDetailsOverviewBinding
) : CaseReportDetailsViewHolder(binding.root) {
  override fun bind(data: CaseReportDetailsData) {
    (data as CaseReportDetailsOverviewData).let {
      binding.tvInitialDiagnosis.text = it.caseReportItem.initialDiagnosis
      binding.tvCaseNo.text = "Case No: ${it.caseReportItem.caseNo}"
      binding.tvPatientName.text = "Patient Name: ${it.caseReportItem.patientName}"
      binding.tvDetectedBy.text = "Detected By: ${it.caseReportItem.detectedBy}"
      binding.tvClassification.text = "Classification: ${it.caseReportItem.classification}"
    }
  }
}

class CaseReportDetailsExpandableViewHolder(
  private val binding: CaseReportDetailExpandleViewBinding
) : CaseReportDetailsViewHolder(binding.root) {
  override fun bind(data: CaseReportDetailsData) {
    (data as CaseReportDetailsExpandableViewData).let {
      binding.tvTitle.text = data.sectionTitle
      updateUI(it.isExpandable)
      bindData(data)

      binding.headerLayout.setOnClickListener {
        data.isExpandable = !data.isExpandable
        updateUI(data.isExpandable)
      }
    }
  }

  private fun updateUI(isExpandable: Boolean) {
    if (isExpandable) {
      binding.ivExpandableButton.setImageResource(R.drawable.ic_outline_keyboard_arrow_up)
      binding.expandableLayout.visibility = View.VISIBLE
    } else {
      binding.ivExpandableButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down)
      binding.expandableLayout.visibility = View.GONE
    }
  }

  private fun bindData(caseReportDetailsExpandableViewData: CaseReportDetailsExpandableViewData) {
    binding.expandableLayout.removeAllViews()
    caseReportDetailsExpandableViewData.reportPropertyList.forEach {
      val expandableItemView = ExpandableItemView(binding.expandableLayout.context, null)
      expandableItemView.bind(it.title, it.value)
      binding.expandableLayout.addView(expandableItemView)
    }
  }

}

enum class ViewTypes {
  OVERVIEW,
  EXPANDABLE_VIEW;

  companion object {
    fun from(ordinal: Int): ViewTypes {
      return values()[ordinal]
    }
  }
}

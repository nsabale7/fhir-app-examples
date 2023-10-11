package com.google.fhir.examples.configurablecare.ui.reportlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.fhir.examples.configurablecare.databinding.CaseReportListItemViewBinding


class CaseReportItemRecyclerViewAdapter(
  private val onItemClicked: (CaseReportItem) -> Unit
) :
  ListAdapter<CaseReportItem, CaseReportItemViewHolder>(CaseReportItemDiffCallback()) {

  class CaseReportItemDiffCallback : DiffUtil.ItemCallback<CaseReportItem>() {
    override fun areItemsTheSame(oldItem: CaseReportItem, newItem: CaseReportItem): Boolean =
      oldItem.questionnaireResponseId == newItem.questionnaireResponseId

    override fun areContentsTheSame(oldItem: CaseReportItem, newItem: CaseReportItem): Boolean =
      oldItem.caseNo == newItem.caseNo && oldItem.questionnaireResponseId == newItem.questionnaireResponseId && oldItem.patientName == newItem.patientName
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseReportItemViewHolder {
    return CaseReportItemViewHolder(
      CaseReportListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: CaseReportItemViewHolder, position: Int) {
    val item = currentList[position]
    holder.bindTo(item, onItemClicked)
  }
}

/*
 * Copyright 2022-2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.fhir.examples.configurablecare.ui.reportlist

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.fhir.examples.configurablecare.databinding.CaseReportListItemViewBinding

class CaseReportItemViewHolder(binding: CaseReportListItemViewBinding) :
  RecyclerView.ViewHolder(binding.root) {
  private val finalClassification: TextView = binding.tvFinalClassification
  private val caseNo: TextView = binding.tvCaseNo
  private val patientName: TextView = binding.tvPatientName

  fun bindTo(
    caseReportItem: CaseReportItem,
    onItemClicked: (CaseReportItem) -> Unit
  ) {
    this.finalClassification.text  = caseReportItem.classification
    this.caseNo.text  = caseReportItem.caseNo
    this.patientName.text  = caseReportItem.patientName
    this.itemView.setOnClickListener { onItemClicked(caseReportItem) }
  }
}

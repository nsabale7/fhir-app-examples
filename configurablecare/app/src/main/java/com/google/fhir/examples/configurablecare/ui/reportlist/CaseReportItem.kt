package com.google.fhir.examples.configurablecare.ui.reportlist

import android.service.autofill.FieldClassification

data class CaseReportItem(
  val questionnaireResponseId : String,
  val initialDiagnosis: String,
  val address: String,
  val patientName: String,
  val caseNo: String,
  val detectedBy: String,
  val classification: String,
)

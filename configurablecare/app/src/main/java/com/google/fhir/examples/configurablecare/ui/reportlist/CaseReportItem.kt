package com.google.fhir.examples.configurablecare.ui.reportlist

data class CaseReportItem(
  val questionnaireResponseId : String,
  val address: String,
  val patientName: String,
  val caseNo: String,
  val detectedBy: String,
  val classification: String,
)

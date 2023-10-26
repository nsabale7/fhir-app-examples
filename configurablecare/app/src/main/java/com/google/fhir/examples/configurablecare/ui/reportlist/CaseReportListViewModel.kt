package com.google.fhir.examples.configurablecare.ui.reportlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.fhir.search.Order
import com.google.android.fhir.search.Search
import com.google.fhir.examples.configurablecare.FhirApplication
import com.google.fhir.examples.configurablecare.util.FhirPathEvaluator
import kotlinx.coroutines.launch
import org.hl7.fhir.r4.model.QuestionnaireResponse
import org.hl7.fhir.r4.model.ResourceType

class CaseReportListViewModel(application: Application) : AndroidViewModel(application)  {

  private val fhirEngine = FhirApplication.fhirEngine(application.applicationContext)

  val caseReportList = MutableLiveData<List<CaseReportItem>>()

  init {
    updateCaseReportList()
  }

  fun updateCaseReportList() = viewModelScope.launch {
    val result = searchCaseReportQR()
    caseReportList.value = result
  }

  private suspend fun searchCaseReportQR(): List<CaseReportItem> {
    return fhirEngine.search<QuestionnaireResponse>(Search(ResourceType.QuestionnaireResponse).apply {
      sort(QuestionnaireResponse.AUTHORED, Order.ASCENDING)
    }).map { questionnaireResponse ->
      questionnaireResponse.toCaseReportItem()
    }
  }

}

fun QuestionnaireResponse.toCaseReportItem() : CaseReportItem {
  val questionnaireResponseId = if (hasIdElement()) idElement.idPart else ""
  val address = FhirPathEvaluator.extractValueFromResource(this, "QuestionnaireResponse.repeat(item).where(linkId='locality').answer.value")
  val caseNo = FhirPathEvaluator.extractValueFromResource(this, "QuestionnaireResponse.repeat(item).where(linkId='caseID').answer.value")
  val patientName = FhirPathEvaluator.extractValueFromResource(this, "QuestionnaireResponse.repeat(item).where(linkId='name').answer.value")
  val detectedBy = FhirPathEvaluator.extractValueFromResource(this, "QuestionnaireResponse.repeat(item).where(linkId='reportingFacility').answer.value")
  val classification = FhirPathEvaluator.extractValueFromResource(this, "QuestionnaireResponse.repeat(item).where(linkId='finalClassification').answer.value")
  return CaseReportItem(
    questionnaireResponseId = questionnaireResponseId,
    address = address,
    caseNo = caseNo,
    patientName = patientName,
    detectedBy = detectedBy,
    classification = classification
  )
}


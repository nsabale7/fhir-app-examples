package com.google.fhir.examples.configurablecare.ui.reportdetails;

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.fhir.examples.configurablecare.FhirApplication
import com.google.fhir.examples.configurablecare.ui.reportlist.CaseReportItem
import com.google.fhir.examples.configurablecare.ui.reportlist.toCaseReportItem
import com.google.fhir.examples.configurablecare.util.FhirPathEvaluator
import com.google.fhir.examples.configurablecare.util.FhirPathHelper
import com.google.fhir.examples.configurablecare.util.ReportProperty
import kotlinx.coroutines.launch
import org.hl7.fhir.r4.model.QuestionnaireResponse
import org.hl7.fhir.r4.model.ResourceType

class CaseReportDetailsViewModel(application: Application, state: SavedStateHandle) :
  AndroidViewModel(application) {

  private val questionnaireResponseId: String = state["questionnaire_response_id"]!!
  private val fhirEngine = FhirApplication.fhirEngine(application.applicationContext)

  val caseReportDetailsData = MutableLiveData<List<CaseReportDetailsData>>()

  init {
    getCaseReportDetailData()
  }

  private fun getCaseReportDetailData() = viewModelScope.launch {
    val data = mutableListOf<CaseReportDetailsData>()

    val questionnaireResponse = fhirEngine.get(
      ResourceType.QuestionnaireResponse,
      questionnaireResponseId
    ) as QuestionnaireResponse
    val caseReportItem = questionnaireResponse.toCaseReportItem()

    data.add(CaseReportDetailsOverviewData(caseReportItem))
    data.add(
      CaseReportDetailsExpandableViewData(
        "I IDENTIFICATION OF THE REPORTING INSTITUTION",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.reportingInstitution
        )
      )
    )

    data.add(
      CaseReportDetailsExpandableViewData(
        "II PATIENT INFORMATION",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.patientInformation
        )
      )
    )

    data.add(
      CaseReportDetailsExpandableViewData(
        "III VACCINATION HISTORY",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.vaccinationHistory
        )
      )
    )

    data.add(
      CaseReportDetailsExpandableViewData(
        "IV CLINICAL DATA, FOLLOW-UP AND TREATMENT ",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.clinicalData
        )
      )
    )

    data.add(
      CaseReportDetailsExpandableViewData(
        "V SPECIMENS AND LABORATORY TESTING",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.specimensTesting
        )
      )
    )

    data.add(
      CaseReportDetailsExpandableViewData(
        "VI INVESTIGATION",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.investigation
        )
      )
    )
    data.add(
      CaseReportDetailsExpandableViewData(
        "VII RESPONSE MEASURES",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.responseMeasures
        )
      )
    )
    data.add(
      CaseReportDetailsExpandableViewData(
        "VIII CLASSIFICATION",
        FhirPathEvaluator.getReportPropertyListFromResource(
          questionnaireResponse,
          FhirPathHelper.classification
        )
      )
    )

    caseReportDetailsData.value = data
  }

}

interface CaseReportDetailsData {
  var isExpandable: Boolean
}

data class CaseReportDetailsOverviewData(
  val caseReportItem: CaseReportItem,
  override var isExpandable: Boolean = false
) : CaseReportDetailsData {
  override fun equals(other: Any?): Boolean {
    return if (other is CaseReportDetailsOverviewData) {
      caseReportItem == other.caseReportItem
    } else {
      false
    }
  }
}


data class CaseReportDetailsExpandableViewData(
  val sectionTitle: String,
  val reportPropertyList: List<ReportProperty>,
  override var isExpandable: Boolean = false
) : CaseReportDetailsData {
  override fun equals(other: Any?): Boolean {
    return if (other is CaseReportDetailsExpandableViewData) {
      reportPropertyList == other.reportPropertyList
    } else {
      false
    }
  }
}
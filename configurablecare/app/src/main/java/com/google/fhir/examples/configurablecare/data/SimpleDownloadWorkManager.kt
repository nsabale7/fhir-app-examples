package com.google.fhir.examples.configurablecare.data

import com.google.android.fhir.sync.DownloadWorkManager
import com.google.android.fhir.sync.Request
import java.util.LinkedList
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.Resource
import org.hl7.fhir.r4.model.ResourceType

class SimpleDownloadWorkManager : DownloadWorkManager {
  private val urls = LinkedList(
    listOf(
      "Patient",
      "Composition",
      "Practitioner",
      "Encounter",
      "Observation",
      "DiagnosticReport",
      "Specimen",
      "Organization",
      "Condition",
      "QuestionnaireResponse",
      "Immunization",
      "RelatedPerson",
    )
  )

  override suspend fun getNextRequest(): Request? {
    val url = urls.poll() ?: return null
    return Request.of(url)
  }

  override suspend fun getSummaryRequestUrls() = mapOf<ResourceType, String>()

  override suspend fun processResponse(response: Resource): Collection<Resource> {
    var bundleCollection: Collection<Resource> = mutableListOf()
    if (response is Bundle && response.type == Bundle.BundleType.SEARCHSET) {
      bundleCollection = response.entry.map { it.resource }
    }
    return bundleCollection
  }
}
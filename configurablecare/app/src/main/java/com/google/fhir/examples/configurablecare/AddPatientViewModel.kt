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
package com.google.fhir.examples.configurablecare

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import ca.uhn.fhir.parser.IParser
import com.google.android.fhir.FhirEngine
import com.google.android.fhir.datacapture.validation.Invalid
import com.google.android.fhir.datacapture.validation.QuestionnaireResponseValidator
import com.google.fhir.examples.configurablecare.util.TransformSupportServicesMatchBox
import java.io.File
import java.util.UUID
import kotlinx.coroutines.launch
import org.hl7.fhir.r4.model.Base
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.DateType
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.Questionnaire
import org.hl7.fhir.r4.model.QuestionnaireResponse
import org.hl7.fhir.r4.model.Reference

/** ViewModel for patient registration screen {@link AddPatientFragment}. */
class AddPatientViewModel(application: Application, private val state: SavedStateHandle) :
  AndroidViewModel(application) {

  val questionnaire: String
    get() = getQuestionnaireJson()

  val questionnaireUri : Uri?
    get() = getQuestionnaire()

  val questionnaireResponse: String
    get() = getQuestionnaireResponseJson()

  val savedMeasles = MutableLiveData<Boolean?>()

  private val questionnaireResource: Questionnaire
    get() =
      FhirContext.forCached(FhirVersionEnum.R4).newJsonParser().parseResource(questionnaire)
        as Questionnaire
  private var fhirEngine: FhirEngine = FhirApplication.fhirEngine(application.applicationContext)
  private var questionnaireJson: String? = null

  /**
   * Saves patient registration questionnaire response into the application database.
   *
   * @param questionnaireResponse patient registration questionnaire response
   */
  fun savePatient(questionnaireResponse: QuestionnaireResponse) {
    viewModelScope.launch {
      if (QuestionnaireResponseValidator.validateQuestionnaireResponse(
            questionnaireResource,
            questionnaireResponse,
            getApplication()
          )
          .values
          .flatten()
          .any { it is Invalid }
      ) {
        savedMeasles.value = null
        return@launch
      }

      val contextR4 = FhirApplication.contextR4(getApplication<FhirApplication>().applicationContext)
      if(contextR4 == null) {
        savedMeasles.value = null
        return@launch
      }

      val outputs = mutableListOf<Base>()
      val transformSupportServices =
        TransformSupportServicesMatchBox(
          contextR4,
          outputs
        )
      val structureMapUtilities =
        org.hl7.fhir.r4.utils.StructureMapUtilities(contextR4, transformSupportServices)

      val locationStructureMap = readFileFromAssets("AFROMRCIFQuestionnaireToResources.fml")

      val structureMap = structureMapUtilities.parse(locationStructureMap, "AFROMRCIFQuestionnaireToResources")
      val iParser: IParser = FhirContext.forCached(FhirVersionEnum.R4).newJsonParser()
      val targetResource = Bundle()
      val baseElement =
        iParser.parseResource(
          QuestionnaireResponse::class.java, iParser.encodeResourceToString(questionnaireResponse))
      structureMapUtilities.transform(contextR4, baseElement, structureMap, targetResource)


      if (!targetResource.hasEntry()) {
        savedMeasles.value = null
        return@launch
      }

      var patientID :String? = null
     targetResource.entry.forEach { bundleEntryComponent ->
       val resource = bundleEntryComponent.resource
       if(resource is Patient) {
         patientID = resource.idElement.idPart
       }
       if(resource is Observation && resource.effective is DateType) {
         resource.effective = null
       }
       fhirEngine.create(resource)
     }

      questionnaireResponse.id = UUID.randomUUID().toString()
      if(patientID != null) {
        questionnaireResponse.subject = Reference("Patient/$patientID")
      }
      fhirEngine.create(questionnaireResponse)
      savedMeasles.value = true
    }
  }

  private fun getQuestionnaireJson(): String {
    questionnaireJson?.let {
      return it
    }
    questionnaireJson = readFileFromAssets(state[AddPatientFragment.QUESTIONNAIRE_FILE_PATH_KEY]!!)
    return questionnaireJson!!
  }

  private fun getQuestionnaireResponseJson(): String {
    return readFileFromAssets("response.json")
  }
  private fun getQuestionnaire(): Uri? {
      val outputFile = File(getApplication<Application>().externalCacheDir, "questionnaire")
      val questionnaireJson = readFileFromAssets(state[AddPatientFragment.QUESTIONNAIRE_FILE_PATH_KEY]!!)
      if (questionnaireJson.isEmpty()) return null
      outputFile.writeText(questionnaireJson)
      return Uri.fromFile(outputFile)
  }

  private fun readFileFromAssets(filename: String): String {
    return getApplication<Application>().assets.open(filename).bufferedReader().use {
      it.readText()
    }
  }

  private fun generateUuid(): String {
    return UUID.randomUUID().toString()
  }
}

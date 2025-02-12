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
package com.google.fhir.examples.configurablecare.care

import android.content.Context
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.hl7.fhir.r4.model.Resource

data class RequestResourceConfig(
  var resourceType: String,
  var values: List<Value>,
  var maxDuration: String,
  var unit: String
) {
  data class Value(var field: String, var value: String)
}

class ImplementationGuideConfig(
  var implementationGuideId: String,
  var entryPoint: String,
  var requestResourceConfigurations: List<RequestResourceConfig>,
  var supportedValueSets: JsonArray
)

data class SupportedImplementationGuide(
  var location: String,
  var carePlanPolicy: String,
  var implementationGuideConfig: ImplementationGuideConfig,
)

data class CareConfiguration(var supportedImplementationGuides: List<SupportedImplementationGuide>)

object ConfigurationManager {
  var careConfiguration: CareConfiguration? = null

  fun getCareConfiguration(context: Context): CareConfiguration {
    if (careConfiguration == null) {
      val gson = Gson()
      val careConfig = readFileFromAssets(context, "care-config.json").trimIndent()
      careConfiguration = gson.fromJson(careConfig, CareConfiguration::class.java)
    }
    return careConfiguration!!
  }

  fun getCareConfigurationResources(): Collection<Resource> {
    var bundleCollection: Collection<Resource> = mutableListOf()
    val jsonParser = FhirContext.forCached(FhirVersionEnum.R4).newJsonParser()

    for (implementationGuide in careConfiguration?.supportedImplementationGuides!!) {
      val resourceJsonList = implementationGuide.implementationGuideConfig.supportedValueSets

      for (resourceJson in resourceJsonList) {
        val resource = jsonParser.parseResource(resourceJson.toString()) as Resource
        bundleCollection += resource
      }
    }
    return bundleCollection
  }

  private fun readFileFromAssets(context: Context, filename: String): String {
    return context.assets.open(filename).bufferedReader().use { it.readText() }
  }
}

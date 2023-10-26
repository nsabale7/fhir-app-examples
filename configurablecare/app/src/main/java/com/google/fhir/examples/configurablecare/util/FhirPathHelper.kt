package com.google.fhir.examples.configurablecare.util

object FhirPathHelper {

  val reportingInstitution = listOf(
    FhirPathItem(
      "EPID Number:  Country-Province-District-year-onset-case #",
      "QuestionnaireResponse.repeat(item).where(linkId='caseID').answer.value"
    ),
    FhirPathItem(
      "Health service name",
      "QuestionnaireResponse.repeat(item).where(linkId='reportingFacility').answer.value"
    ),
    FhirPathItem(
      "Locality/Neighborhood",
      "QuestionnaireResponse.repeat(item).where(linkId='locality').answer.value"
    ),
    FhirPathItem(
      "Date the form was received at national level",
      "QuestionnaireResponse.repeat(item).where(linkId='DateReportedNational').answer.value"
    ),
  )

  val patientInformation = listOf(
    FhirPathItem(
      "Name of Patient",
      "QuestionnaireResponse.repeat(item).where(linkId='name').answer.value"
    ),
    FhirPathItem(
      "Date of Birth",
      "QuestionnaireResponse.repeat(item).where(linkId='DoB').answer.value"
    ),
    FhirPathItem(
      "If date of birth is unknown, age",
      "QuestionnaireResponse.repeat(item).where(linkId='ageOnset').answer.value"
    ),
    FhirPathItem(
      "Sex",
      "QuestionnaireResponse.repeat(item).where(linkId='sex').answer.value"
    ),
    FhirPathItem(
      "Address",
      "QuestionnaireResponse.repeat(item).where(linkId='address').answer.value"
    ),
    FhirPathItem(
      "Type of locality",
      "QuestionnaireResponse.repeat(item).where(linkId='localityType').answer.value"
    )
  )

  val vaccinationHistory = listOf(
    FhirPathItem(
      "Type of Vaccine received",
      "QuestionnaireResponse.repeat(item).where(linkId='vaccineType').answer.value"
    ),
    FhirPathItem(
      "Number of measles containing vaccine doses received in routine EPI and/or SIAs -valid values: 1 - 4 doses",
      "QuestionnaireResponse.repeat(item).where(linkId='noOfDoses').answer.value"
    ),
    FhirPathItem(
      "Date of last dose",
      "QuestionnaireResponse.repeat(item).where(linkId='lastDoseDate').answer.value"
    )
  )

  val caseInvestigation = listOf(
    FhirPathItem("Date case was notified to the district", "QuestionnaireResponse.repeat(item).where(linkId='dateReported').answer.value"),
    FhirPathItem(
      "Date Of case investigation",
      "QuestionnaireResponse.repeat(item).where(linkId='DateOfConsultation').answer.value"
    ),
    FhirPathItem(
      "Date of rash onset",
      "QuestionnaireResponse.repeat(item).where(linkId='rashOnsetDate').answer.value"
    ),
  )

  val clinicalData = listOf(
    FhirPathItem("Fever", "QuestionnaireResponse.repeat(item).where(linkId='fever').answer.value"),
    FhirPathItem("Generalized Rash", "QuestionnaireResponse.repeat(item).where(linkId='rash').answer.value"),
    FhirPathItem("Cough", "QuestionnaireResponse.repeat(item).where(linkId='cough').answer.value"),
    FhirPathItem(
      "Red Eyes",
      "QuestionnaireResponse.repeat(item).where(linkId='conjunctivitis').answer.value"
    ),
    FhirPathItem(
      "Running nose",
      "QuestionnaireResponse.repeat(item).where(linkId='coryza').answer.value"
    ),
    FhirPathItem(
      "Swollen lymph nodes behind ears",
      "QuestionnaireResponse.repeat(item).where(linkId='lymphadenopathy').answer.value"
    ),
    FhirPathItem(
      "Joint pain/swelling",
      "QuestionnaireResponse.repeat(item).where(linkId='jointPain').answer.value"
    ),
    FhirPathItem(
      "In/out-patient",
      "QuestionnaireResponse.repeat(item).where(linkId='admitted').answer.value"
    ),
    FhirPathItem(
      "Outcome",
      "QuestionnaireResponse.repeat(item).where(linkId='outcome').answer.value"
    ),
    FhirPathItem(
      "History of travel outside the village/ town/ district in last 7 - 21 days before onset of rash",
      "QuestionnaireResponse.repeat(item).where(linkId='travelledOutside').answer.value"
    ),
    FhirPathItem(
      "Most probable place of exposure to measles/ rubella: district",
      "QuestionnaireResponse.repeat(item).where(linkId='infectionSetting').answer.value"
    ),
  )

  val specimensTesting = listOf(
    FhirPathItem(
      "Type of specimen",
      "QuestionnaireResponse.repeat(item).where(linkId='sampleType').answer.value"
    ),
    FhirPathItem(
      "Date specimen obtained",
      "QuestionnaireResponse.repeat(item).where(linkId='collectionDate').answer.value"
    ),
    FhirPathItem(
      "Date specimen was sent to lab",
      "QuestionnaireResponse.repeat(item).where(linkId='specimenSentDate').answer.value"
    ),
    FhirPathItem(
      "Date Received",
      "QuestionnaireResponse.repeat(item).where(linkId='specimenReceivedDate').answer.value"
    ),
    FhirPathItem(
      "Type of test",
      "QuestionnaireResponse.repeat(item).where(linkId='testPerformed').answer.value"
    ),
    FhirPathItem(
      "Result",
      "QuestionnaireResponse.repeat(item).where(linkId='result').answer.value"
    ),
    FhirPathItem(
      "Virus Detection -Genotype",
      "QuestionnaireResponse.repeat(item).where(linkId='specifyVirus').answer.value"
    ),
    FhirPathItem(
      "Laboratory Name",
      "QuestionnaireResponse.repeat(item).where(linkId='labName').answer.value"
    ),
  )

  val investigation = listOf(
    FhirPathItem(
      "Were active case-searches conducted?",
      "QuestionnaireResponse.repeat(item).where(linkId='caseSearchConducted').answer.value"
    ),
    FhirPathItem(
      "Number of suspect cases detected during active case-search",
      "QuestionnaireResponse.repeat(item).where(linkId='noOfsuspectCases').answer.value"
    ),
    FhirPathItem(
      "Was the patient in contact with any pregnant woman?",
      "QuestionnaireResponse.repeat(item).where(linkId='pregnantContact').answer.value"
    ),
    FhirPathItem(
      "Name(s)",
      "QuestionnaireResponse.repeat(item).where(linkId='pregnantContactNames').answer.value"
    ),
    FhirPathItem(
      "Are there other cases present in the case’s municipality of residence?",
      "QuestionnaireResponse.repeat(item).where(linkId='localCasesPresent').answer.value"
    ),
    FhirPathItem(
      "Did the patient travel outside his/her province/state of residence 7-23 days before rash onset?",
      "QuestionnaireResponse.repeat(item).where(linkId='travelledOutside').answer.value"
    ),
    FhirPathItem(
      "Cities/Countries",
      "QuestionnaireResponse.repeat(item).where(linkId='placeOfTravel').answer.value"
    ),
    FhirPathItem(
      "Date of arrival",
      "QuestionnaireResponse.repeat(item).where(linkId='arrivalDate').answer.value"
    ),
    FhirPathItem(
      "Date of departure",
      "QuestionnaireResponse.repeat(item).where(linkId='departureDate').answer.value"
    ),
    FhirPathItem(
      "Setting where infected?",
      "QuestionnaireResponse.repeat(item).where(linkId='infectionSetting').answer.value"
    ),
  )

  val responseMeasures = listOf(
    FhirPathItem(
      "Ring vaccination?",
      "QuestionnaireResponse.repeat(item).where(linkId='ringVaccination').answer.value"
    ),
    FhirPathItem(
      "Date started",
      "QuestionnaireResponse.repeat(item).where(linkId='dateStarted').answer.value"
    ),
    FhirPathItem(
      "Date Ended",
      "QuestionnaireResponse.repeat(item).where(linkId='dateEnded').answer.value"
    ),
    FhirPathItem(
      "Number of doses given during ring vaccination",
      "QuestionnaireResponse.repeat(item).where(linkId='noOfDosesRingVaccination').answer.value"
    ),
    FhirPathItem(
      "Was rapid coverage monitoring done?",
      "QuestionnaireResponse.repeat(item).where(linkId='rapidCoverageMonitioring').answer.value"
    ),
    FhirPathItem(
      "What % of vaccinated persons was found?",
      "QuestionnaireResponse.repeat(item).where(linkId='percentVaccinated').answer.value"
    ),
    FhirPathItem(
      "Were the contacts followed for up to 30 days after the date of the rash onset of the case?",
      "QuestionnaireResponse.repeat(item).where(linkId='contactTracingDone').answer.value"
    ),
    FhirPathItem(
      "Date of the last day of contact follow-up",
      "QuestionnaireResponse.repeat(item).where(linkId='lastFollowUp').answer.value"
    ),
  )

  val classification = listOf(
    FhirPathItem(
      "Final Classification",
      "QuestionnaireResponse.repeat(item).where(linkId='finalClassification').answer.value"
    ),
    FhirPathItem(
      "Person Completing the form: Name",
      "QuestionnaireResponse.repeat(item).where(linkId='reportedBy').answer.value"
    ),
  )
}
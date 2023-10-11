package com.google.fhir.examples.configurablecare.util

object FhirPathHelper {

  val reportingInstitution = listOf(
    FhirPathItem(
      "Initial Diagnosis",
      "QuestionnaireResponse.repeat(item).where(linkId='initialDiagnosis').answer.value"
    ),
    FhirPathItem(
      "Case Number",
      "QuestionnaireResponse.repeat(item).where(linkId='caseID').answer.value"
    ),
    FhirPathItem(
      "Country",
      "QuestionnaireResponse.repeat(item).where(linkId='country').answer.value"
    ),
    FhirPathItem(
      "Province/State",
      "QuestionnaireResponse.repeat(item).where(linkId='state').answer.value"
    ),
    FhirPathItem(
      "Municipality",
      "QuestionnaireResponse.repeat(item).where(linkId='municipality').answer.value"
    ),
    FhirPathItem(
      "Locality/Neighborhood",
      "QuestionnaireResponse.repeat(item).where(linkId='locality').answer.value"
    ),
    FhirPathItem(
      "Detected by",
      "QuestionnaireResponse.repeat(item).where(linkId='DetectedBy').answer.value"
    ),
    FhirPathItem(
      "Health service name",
      "QuestionnaireResponse.repeat(item).where(linkId='reportingFacility').answer.value"
    ),
    FhirPathItem(
      "Health service telephone",
      "QuestionnaireResponse.repeat(item).where(linkId='telephone').answer.value"
    ),
    FhirPathItem(
      "Reported by",
      "QuestionnaireResponse.repeat(item).where(linkId='reportedBy').answer.value"
    ),
    FhirPathItem(
      "Date of consultation",
      "QuestionnaireResponse.repeat(item).where(linkId='DateOfConsultation').answer.value"
    ),
    FhirPathItem(
      "Date Reported, Local",
      "QuestionnaireResponse.repeat(item).where(linkId='dateReported').answer.value"
    ),
    FhirPathItem(
      "Date of home visit",
      "QuestionnaireResponse.repeat(item).where(linkId='DateOfHomeVisit').answer.value"
    ),
    FhirPathItem(
      "Date Reported, National",
      "QuestionnaireResponse.repeat(item).where(linkId='DateReportedNational').answer.value"
    ),
    FhirPathItem(
      "Type of provider reporting",
      "QuestionnaireResponse.repeat(item).where(linkId='TypeOfProviderReporting').answer.value"
    ),
  )

  val patientInformation = listOf(
    FhirPathItem(
      "Patient’s first and last names",
      "QuestionnaireResponse.repeat(item).where(linkId='name').answer.value"
    ),
    FhirPathItem(
      "Name of the mother or guardian",
      "QuestionnaireResponse.repeat(item).where(linkId='guardian').answer.value"
    ),
    FhirPathItem(
      "Address",
      "QuestionnaireResponse.repeat(item).where(linkId='address').answer.value"
    ),
    FhirPathItem(
      "Telephone",
      "QuestionnaireResponse.repeat(item).where(linkId='telephone').answer.value"
    ),
    FhirPathItem(
      "Landmarks to locate the house",
      "QuestionnaireResponse.repeat(item).where(linkId='landmark').answer.value"
    ),
    FhirPathItem(
      "Patient’s Occupation",
      "QuestionnaireResponse.repeat(item).where(linkId='occupation').answer.value"
    ),
    FhirPathItem(
      "Type of locality",
      "QuestionnaireResponse.repeat(item).where(linkId='localityType').answer.value"
    ),
    FhirPathItem(
      "Patient’s sex",
      "QuestionnaireResponse.repeat(item).where(linkId='sex').answer.value"
    ),
    FhirPathItem(
      "Work or school address",
      "QuestionnaireResponse.repeat(item).where(linkId='workAddress').answer.value"
    ),
    FhirPathItem(
      "Patient’s Date of Birth",
      "QuestionnaireResponse.repeat(item).where(linkId='DoB').answer.value"
    ),
    FhirPathItem(
      "If date of birth is unknown, age",
      "QuestionnaireResponse.repeat(item).where(linkId='ageOnset').answer.value"
    ),
  )

  val vaccinationHistory = listOf(
    FhirPathItem(
      "Type of Vaccine",
      "QuestionnaireResponse.repeat(item).where(linkId='vaccineType').answer.value"
    ),
    FhirPathItem(
      "Number of doses*",
      "QuestionnaireResponse.repeat(item).where(linkId='noOfDoses').answer.value"
    ),
    FhirPathItem(
      "Date of last dose",
      "QuestionnaireResponse.repeat(item).where(linkId='lastDoseDate').answer.value"
    ),
    FhirPathItem(
      "Source of vaccination Information",
      "QuestionnaireResponse.repeat(item).where(linkId='sourceOfInformation').answer.value"
    ),
  )

  val clinicalData = listOf(
    FhirPathItem("Fever?", "QuestionnaireResponse.repeat(item).where(linkId='fever').answer.value"),
    FhirPathItem(
      "Temperature (°)",
      "QuestionnaireResponse.repeat(item).where(linkId='temperature').answer.value"
    ),
    FhirPathItem(
      "Date of fever onset",
      "QuestionnaireResponse.repeat(item).where(linkId='dateOfFeverOnset').answer.value"
    ),
    FhirPathItem("Rash?", "QuestionnaireResponse.repeat(item).where(linkId='rash').answer.value"),
    FhirPathItem(
      "duration of the rash (in days)",
      "QuestionnaireResponse.repeat(item).where(linkId='rashDuration').answer.value"
    ),
    FhirPathItem(
      "Date of rash onset",
      "QuestionnaireResponse.repeat(item).where(linkId='rashOnsetDate').answer.value"
    ),
    FhirPathItem(
      "Type of rash",
      "QuestionnaireResponse.repeat(item).where(linkId='rashType').answer.value"
    ),
    FhirPathItem("Cough?", "QuestionnaireResponse.repeat(item).where(linkId='cough').answer.value"),
    FhirPathItem(
      "Conjunctivitis?",
      "QuestionnaireResponse.repeat(item).where(linkId='conjunctivitis').answer.value"
    ),
    FhirPathItem(
      "Coryza?",
      "QuestionnaireResponse.repeat(item).where(linkId='coryza').answer.value"
    ),
    FhirPathItem(
      "Koplik Spots?",
      "QuestionnaireResponse.repeat(item).where(linkId='koplikSpots').answer.value"
    ),
    FhirPathItem(
      "Lymphadenopathy?",
      "QuestionnaireResponse.repeat(item).where(linkId='lymphadenopathy').answer.value"
    ),
    FhirPathItem(
      "Arthralgia?",
      "QuestionnaireResponse.repeat(item).where(linkId='jointPain').answer.value"
    ),
    FhirPathItem(
      "Is the patient pregnant?",
      "QuestionnaireResponse.repeat(item).where(linkId='pregnancy').answer.value"
    ),
    FhirPathItem(
      "Weeks of pregnancy (01-42)",
      "QuestionnaireResponse.repeat(item).where(linkId='monthsPregnant').answer.value"
    ),
    FhirPathItem(
      "Place where birth will likely take place",
      "QuestionnaireResponse.repeat(item).where(linkId='placeOfBirth').answer.value"
    ),
    FhirPathItem(
      "Hospitalized?",
      "QuestionnaireResponse.repeat(item).where(linkId='admitted').answer.value"
    ),
    FhirPathItem(
      "Hospital name",
      "QuestionnaireResponse.repeat(item).where(linkId='admittedHCFName').answer.value"
    ),
    FhirPathItem(
      "Date of admission",
      "QuestionnaireResponse.repeat(item).where(linkId='admissionDate').answer.value"
    ),
    FhirPathItem(
      "Hospital record number",
      "QuestionnaireResponse.repeat(item).where(linkId='hospitalRecordNumber').answer.value"
    ),
    FhirPathItem(
      "Death?",
      "QuestionnaireResponse.repeat(item).where(linkId='outcome').answer.value"
    ),
    FhirPathItem(
      "Date of death",
      "QuestionnaireResponse.repeat(item).where(linkId='outcomeDate').answer.value"
    ),
    FhirPathItem(
      "Primary cause of death",
      "QuestionnaireResponse.repeat(item).where(linkId='primaryCauseOfDeath').answer.value"
    ),
    FhirPathItem(
      "Comments",
      "QuestionnaireResponse.repeat(item).where(linkId='diseaseAdditionalInfo').answer.value"
    ),
  )

  val specimensTesting = listOf(
    FhirPathItem(
      "Specimen number",
      "QuestionnaireResponse.repeat(item).where(linkId='specimenNumber').answer.value"
    ),
    FhirPathItem(
      "Type of specimen",
      "QuestionnaireResponse.repeat(item).where(linkId='sampleType').answer.value"
    ),
    FhirPathItem(
      "Date specimen obtained",
      "QuestionnaireResponse.repeat(item).where(linkId='collectionDate').answer.value"
    ),
    FhirPathItem(
      "Laboratory Name",
      "QuestionnaireResponse.repeat(item).where(linkId='labName').answer.value"
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
      "# specimen ID in lab.",
      "QuestionnaireResponse.repeat(item).where(linkId='sampleId').answer.value"
    ),
    FhirPathItem(
      "Type of test",
      "QuestionnaireResponse.repeat(item).where(linkId='testPerformed').answer.value"
    ),
    FhirPathItem(
      "Antigen",
      "QuestionnaireResponse.repeat(item).where(linkId='antigen').answer.value"
    ),
    FhirPathItem(
      "Result",
      "QuestionnaireResponse.repeat(item).where(linkId='result').answer.value"
    ),
    FhirPathItem(
      "Date of Results",
      "QuestionnaireResponse.repeat(item).where(linkId='resultDate').answer.value"
    ),
    FhirPathItem(
      "viral genotype",
      "QuestionnaireResponse.repeat(item).where(linkId='specifyVirus').answer.value"
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
      "Basis for Confirmation",
      "QuestionnaireResponse.repeat(item).where(linkId='confirmationBasis').answer.value"
    ),
    FhirPathItem(
      "Basis for Discarding",
      "QuestionnaireResponse.repeat(item).where(linkId='countryImportation').answer.value"
    ),
    FhirPathItem(
      "For confirmed cases, Source of infection",
      "QuestionnaireResponse.repeat(item).where(linkId='discardingBasis').answer.value"
    ),
    FhirPathItem(
      "If Imported or Import-related",
      "QuestionnaireResponse.repeat(item).where(linkId='sourceOfInfection').answer.value"
    ),
    FhirPathItem(
      "Contact of another case?",
      "QuestionnaireResponse.repeat(item).where(linkId='contact').answer.value"
    ),
    FhirPathItem(
      "Contact of (or epidemiologically-linked to) case number",
      "QuestionnaireResponse.repeat(item).where(linkId='contactCaseNo').answer.value"
    ),
    FhirPathItem(
      "Classified by",
      "QuestionnaireResponse.repeat(item).where(linkId='classifiedBy').answer.value"
    ),
    FhirPathItem(
      "Date of final classification",
      "QuestionnaireResponse.repeat(item).where(linkId='classificationDate').answer.value"
    ),
  )
}
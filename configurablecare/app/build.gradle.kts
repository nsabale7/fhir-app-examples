plugins {
  id("com.android.application")
  id("kotlin-android")
  id("androidx.navigation.safeargs.kotlin")
}

android {
  namespace = "com.google.fhir.examples.configurablecare"
  compileSdk = 33
  defaultConfig {
    applicationId = "com.google.fhir.examples.configurablecare"
    minSdk = 24
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    manifestPlaceholders["appAuthRedirectScheme"] = applicationId!!
    buildFeatures.buildConfig = true
  }
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  buildFeatures { viewBinding = true }
  compileOptions {
    // Flag to enable support for the new language APIs
    // See https://developer.android.com/studio/write/java8-support
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlin { jvmToolchain(11) }
  packaging {
    resources.excludes.addAll(
      listOf(
        "META-INF/ASL-2.0.txt",
        "META-INF/LGPL-3.0.txt",
        "META-INF/LICENSE.md",
        "META-INF/NOTICE.md",
        "META-INF/sun-jaxb.episode",
        "META-INF/DEPENDENCIES"
      )
    )
  }
  sourceSets {
    getByName("main") {
      resources {
        srcDirs("src\\main\\resources", "src\\test")
      }
    }
  }
}

configurations.all {
  resolutionStrategy {
    force(HapiFhir.caffeine)

    force(HapiFhir.fhirBase)
    force(HapiFhir.fhirClient)
    force(HapiFhir.fhirCoreConvertors)

    force(HapiFhir.fhirCoreDstu2)
    force(HapiFhir.fhirCoreDstu2016)
    force(HapiFhir.fhirCoreDstu3)
    force(HapiFhir.fhirCoreR4)
    force(HapiFhir.fhirCoreR4b)
    force(HapiFhir.fhirCoreR5)
    force(HapiFhir.fhirCoreUtils)

    force(HapiFhir.structuresDstu2)
    force(HapiFhir.structuresDstu3)
    force(HapiFhir.structuresR4)
    force(HapiFhir.structuresR5)

    force(HapiFhir.validation)
    force(HapiFhir.validationDstu3)
    force(HapiFhir.validationR4)
    force(HapiFhir.validationR5)
  }
}

object Versions {
  const val caffeine = "2.9.1"

  // Hapi FHIR and HL7 Core Components are interlinked.
  // Newer versions of HapiFhir don't work on Android due to the use of Caffeine 3+
  // Wait for this to release (6.3): https://github.com/hapifhir/hapi-fhir/pull/4196
  const val hapiFhir = "6.0.1"

  // Newer versions don't work on Android due to Apache Commons Codec:
  // Wait for this fix: https://github.com/hapifhir/org.hl7.fhir.core/issues/1046
  const val hapiFhirCore = "5.6.36"
}

object HapiFhir {
  const val fhirBase = "ca.uhn.hapi.fhir:hapi-fhir-base:${Versions.hapiFhir}"
  const val fhirClient = "ca.uhn.hapi.fhir:hapi-fhir-client:${Versions.hapiFhir}"
  const val structuresDstu2 = "ca.uhn.hapi.fhir:hapi-fhir-structures-dstu2:${Versions.hapiFhir}"
  const val structuresDstu3 = "ca.uhn.hapi.fhir:hapi-fhir-structures-dstu3:${Versions.hapiFhir}"
  const val structuresR4 = "ca.uhn.hapi.fhir:hapi-fhir-structures-r4:${Versions.hapiFhir}"
  const val structuresR4b = "ca.uhn.hapi.fhir:hapi-fhir-structures-r4b:${Versions.hapiFhir}"
  const val structuresR5 = "ca.uhn.hapi.fhir:hapi-fhir-structures-r5:${Versions.hapiFhir}"

  const val validation = "ca.uhn.hapi.fhir:hapi-fhir-validation:${Versions.hapiFhir}"
  const val validationDstu3 =
    "ca.uhn.hapi.fhir:hapi-fhir-validation-resources-dstu3:${Versions.hapiFhir}"
  const val validationR4 = "ca.uhn.hapi.fhir:hapi-fhir-validation-resources-r4:${Versions.hapiFhir}"
  const val validationR5 = "ca.uhn.hapi.fhir:hapi-fhir-validation-resources-r5:${Versions.hapiFhir}"

  const val fhirCoreDstu2 = "ca.uhn.hapi.fhir:org.hl7.fhir.dstu2:${Versions.hapiFhirCore}"
  const val fhirCoreDstu2016 = "ca.uhn.hapi.fhir:org.hl7.fhir.dstu2016may:${Versions.hapiFhirCore}"
  const val fhirCoreDstu3 = "ca.uhn.hapi.fhir:org.hl7.fhir.dstu3:${Versions.hapiFhirCore}"
  const val fhirCoreR4 = "ca.uhn.hapi.fhir:org.hl7.fhir.r4:${Versions.hapiFhirCore}"
  const val fhirCoreR4b = "ca.uhn.hapi.fhir:org.hl7.fhir.r4b:${Versions.hapiFhirCore}"
  const val fhirCoreR5 = "ca.uhn.hapi.fhir:org.hl7.fhir.r5:${Versions.hapiFhirCore}"
  const val fhirCoreUtils = "ca.uhn.hapi.fhir:org.hl7.fhir.utilities:${Versions.hapiFhirCore}"
  const val fhirCoreConvertors = "ca.uhn.hapi.fhir:org.hl7.fhir.convertors:${Versions.hapiFhirCore}"

  // Runtime dependency that is required to run FhirPath (also requires minSDK of 26).
  // Version 3.0 uses java.lang.System.Logger, which is not available on Android
  // Replace for Guava when this PR gets merged: https://github.com/hapifhir/hapi-fhir/pull/3977
  const val caffeine = "com.github.ben-manes.caffeine:caffeine:${Versions.caffeine}"
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
  testImplementation("junit:junit:4.13.2")
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
  implementation("androidx.activity:activity-ktx:1.7.2")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.datastore:datastore-preferences:1.0.0")
  implementation("androidx.fragment:fragment-ktx:1.6.0")
  implementation("androidx.recyclerview:recyclerview:1.3.0")
  implementation("androidx.work:work-runtime-ktx:2.8.1")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
  implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
  implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("androidx.datastore:datastore-preferences:1.0.0")
  implementation("com.google.android.material:material:1.9.0")
  implementation("com.jakewharton.timber:timber:5.0.1")
  implementation("net.openid:appauth:0.11.1")
  implementation("com.auth0.android:jwtdecode:2.0.1")
  implementation("com.google.android.fhir:engine:0.1.0-beta03")
  implementation("com.google.android.fhir:data-capture:1.0.0")
  implementation("com.google.android.fhir:knowledge:0.1.0-alpha01")
  implementation("com.google.android.fhir:workflow:0.1.0-alpha03")
}

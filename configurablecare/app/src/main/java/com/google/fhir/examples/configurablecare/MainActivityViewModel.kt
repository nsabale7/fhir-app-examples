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
import android.text.format.DateFormat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.fhir.sync.PeriodicSyncConfiguration
import com.google.android.fhir.sync.RepeatInterval
import com.google.android.fhir.sync.Sync
import com.google.android.fhir.sync.SyncJobStatus
import com.google.fhir.examples.configurablecare.data.FhirSyncWorker
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

/** View model for [MainActivity]. */
@OptIn(InternalCoroutinesApi::class)
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
  private val _lastSyncTimestampLiveData = MutableLiveData<String>()
  val lastSyncTimestampLiveData: LiveData<String>
    get() = _lastSyncTimestampLiveData

  private val _pollState = MutableSharedFlow<SyncJobStatus>()
  val pollState: Flow<SyncJobStatus>
    get() = _pollState

  init {
    viewModelScope.launch {
      Sync.periodicSync<FhirSyncWorker>(
          application.applicationContext,
          PeriodicSyncConfiguration(
            syncConstraints = Constraints.Builder().build(),
            repeat = RepeatInterval(interval = 15, timeUnit = TimeUnit.MINUTES)
          )
        )
        .shareIn(this, SharingStarted.Eagerly, 10)
        .collect { _pollState.emit(it) }
    }
  }

  fun triggerOneTimeSync() {
    println("*** triggerOneTimeSync")

    viewModelScope.launch {

      val oneTimeWorkRequest = OneTimeWorkRequestBuilder<FhirSyncWorker>().build()

      WorkManager.getInstance(getApplication()).enqueueUniqueWork("upload", ExistingWorkPolicy.REPLACE, oneTimeWorkRequest)
    }
  }

  /** Emits last sync time. */
  fun updateLastSyncTimestamp() {
    val formatter =
      DateTimeFormatter.ofPattern(
        if (DateFormat.is24HourFormat(getApplication())) formatString24 else formatString12
      )
    _lastSyncTimestampLiveData.value =
      Sync.getLastSyncTimestamp(getApplication())?.toLocalDateTime()?.format(formatter) ?: ""
  }

  companion object {
    private const val formatString24 = "yyyy-MM-dd HH:mm:ss"
    private const val formatString12 = "yyyy-MM-dd hh:mm:ss a"
  }
}

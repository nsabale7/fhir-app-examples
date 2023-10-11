/*
 * Copyright 2021 IPRD LLC
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

package com.google.fhir.examples.configurablecare.ui.reportdetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.google.fhir.examples.configurablecare.R

class ExpandableItemView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

  init {
    LayoutInflater.from(context).inflate(R.layout.expandable_item_view, this, true)
  }

  private var tvKey: TextView = findViewById(R.id.tvKey)
  private var tvValue: TextView = findViewById(R.id.tvValue)

  fun bind(key: String, value: String) {
    tvKey.text = key
    tvValue.text = value
  }
}

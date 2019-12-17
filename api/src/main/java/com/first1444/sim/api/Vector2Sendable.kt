package com.first1444.sim.api

import com.first1444.dashboard.ActiveComponent
import com.first1444.dashboard.ActiveComponentMultiplexer
import com.first1444.dashboard.BasicDashboard
import com.first1444.dashboard.advanced.Sendable
import com.first1444.dashboard.value.BasicValue
import com.first1444.dashboard.value.ValueProperty
import com.first1444.dashboard.value.implementations.PropertyActiveComponent
import java.text.DecimalFormat
import java.text.Format

class Vector2Sendable @JvmOverloads constructor(
        private val vector2Supplier: () -> Vector2,
        private val format: Format = DEFAULT_FORMAT
) : Sendable<ActiveComponent> {
    companion object {
        val DEFAULT_FORMAT = DecimalFormat(" #0.000;-#0.000")
    }
    override fun init(title: String, dashboard: BasicDashboard): ActiveComponent {
        return ActiveComponentMultiplexer(
                title,
                listOf(
                        PropertyActiveComponent("", dashboard["x"], ValueProperty.createGetOnly { BasicValue.makeString(format.format(vector2Supplier().x)) }),
                        PropertyActiveComponent("", dashboard["y"], ValueProperty.createGetOnly { BasicValue.makeString(format.format(vector2Supplier().y)) })
                )
        )
    }

}

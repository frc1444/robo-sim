package com.first1444.sim.api

import com.first1444.dashboard.ActiveComponent
import com.first1444.dashboard.BasicDashboard
import com.first1444.dashboard.advanced.Sendable
import com.first1444.dashboard.value.BasicValue
import com.first1444.dashboard.value.ValueProperty
import com.first1444.dashboard.value.implementations.PropertyActiveComponent

class ClockSendable(
        private val clock: Clock
) : Sendable<ActiveComponent> {
    override fun init(title: String, dashboard: BasicDashboard): ActiveComponent {
        val entry = dashboard["seconds"]
        return PropertyActiveComponent(title, entry, ValueProperty.createGetOnly { BasicValue.makeDouble(clock.timeSeconds) })
    }
}

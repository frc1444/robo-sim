package com.first1444.sim.api.sensors

import com.first1444.dashboard.ActiveComponent
import com.first1444.dashboard.BasicDashboard
import com.first1444.dashboard.advanced.Sendable
import com.first1444.dashboard.advanced.SendableHelper
import com.first1444.dashboard.value.BasicValue
import com.first1444.dashboard.value.ValueProperty
import com.first1444.dashboard.value.implementations.PropertyActiveComponent

class OrientationSendable(
        private val orientation: Orientation
) : Sendable<ActiveComponent> {
    override fun init(title: String, dashboard: BasicDashboard): ActiveComponent {
        SendableHelper(dashboard).setType("Gyro")
        return PropertyActiveComponent(title, dashboard["Value"], ValueProperty.createGetOnly { BasicValue.makeDouble(orientation.orientationDegrees) })
    }
}

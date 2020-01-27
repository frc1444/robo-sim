# RoboSim
A 2D simulation and abstraction of WPILib code for FRC 

### About
This is a 2D simulation built using libGDX. To use this it is recommended to
use very few features from WPILib because this project has many abstractions for many WPILib features.

### Examples
* [robot2019-sim](https://github.com/frc1444/robot2019-sim)
* [robot2020](https://github.com/frc1444/robot2020)

### Using
This is not recommended for use unless you know what you're doing.

## Importing
Put this in the core robot code (the subproject that has no reference to WPI or LibGDX)
```
dependencies {
    implementation "com.github.frc1444.robo-sim:api:<VERSION or commit hash>"
}
```
Put this in the build.gradle for the root project (the project that has the WPI implementation)
```
dependencies {
    implementation "com.github.frc1444.robo-sim:wpi:<VERSION or commit hash>"
}
```
Put this in the gdx subproject (the subproject that has the LibGDX implementation)
```
dependencies {
    implementation "com.github.frc1444.robo-sim:gdx:<VERSION or commit hash>"
}
```

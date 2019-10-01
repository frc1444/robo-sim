# RoboSim
A 2D simulation and abstraction of WPILib code for FRC 

### Goal
The goal of this is to eventually make it usable by others. However, in the early stages of development, the goal
will be to make something that is usable by team1444 and retrodaredevil

### Using
This is in the very early stages of development and will not be ready for use for a while.

### About
This is a 2D simulation built using libGDX. To use this it is recommended to
not use the WPILib API for anything as this project will provide as much abstraction as possible to avoid that.

## Importing
Put this in the core robot code (the subproject that has no reference to WPI or LibGDX)
```
dependencies {
    implementation "com.github.frc1444:robo-sim:v0.0.1"
    implementation "com.github.frc1444.robo-sim:api:v0.0.1"
}
```
Put this in the build.gradle for the root project (the project that has the WPI implementation)
```
dependencies {
    implementation "com.github.frc1444:robo-sim:v0.0.1"
    implementation "com.github.frc1444.robo-sim:wpi:v0.0.1"
}
```
Put this in the gdx subproject (the subproject that has the LibGDX implementation)
```
dependencies {
    implementation "com.github.frc1444:robo-sim:v0.0.1"
    implementation "com.github.frc1444.robo-sim:gdx:v0.0.1"
}
```


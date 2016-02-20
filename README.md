# 2016-Robotics-Code
CyberCavs 2016 FRC code repository

## Building

### Install Eclipse

https://wpilib.screenstepslive.com/s/4485
http://wiki.cybercavs.com/CyberCavs/wiki/index.php?title=Robot_Programming. (CyberCavs access only)

### Configure Robot Builder

* Open RobotBuilder
* Open existing project
* Select CyberCavs.yaml (from wherever you cloned this repository)

### Running

* Run As WPILib Java Deploy

## GRIP

### Install GRIP

https://github.com/WPIRoboticsProjects/GRIP

### Deploying

* Team number: 4678
* Deploy address: roborio-4678-frc.local
* Enable robot (only needs to be done each time the camera is reconnected)
* Deploy

#### Troubleshooting problems connecting to the camera

* incorrect index - Reconnect camera
* incorrect pixel format - Probably a code (ours) problem. Usually happens when the code tries to open the camera or smartdashboard tries to view it

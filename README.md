# FRC 4247 2018 Code - "Link"

FIRST Robotics Competition 2018 "Power Up" robot code for FRC 4247 from Milwaukee, WI

## Introduction

This program is intended to run FRC 4247's robot, "Link", through the 2018 competition season. As the season progresses, this codebase will be updated with the current state of our code development, along with documentation and tests that ensure proper functionality. After the end of the 2018 season, this code will be archived and documented in a Postmortem which will be posted into the attached Wiki.

## Robot Design Goals

Right now, we have three main goals with our robot:

- Have a high-scoring autonomous
- Deliver cubes anywhere
- Climb at the end of every match

As of right now, the design specifics of our robot are a secret (ie. they're not done yet) but we plan on being able to do the following:

- Use holonomic drive to accurately position our robot anywhere on the field
- Score in auto by passing the base line and depositing cubes into the switch
- Score on all switches and the scale
- Deposit cubes into the portal
- Pick up cubes off the ground
- Hoist the robot at the end of the match

## Installation Notes

In order to compile this software, you will need the following libraries installed:

- WPILib (from http://first.wpi.edu/FRC/roborio/release/eclipse/)
- CTRE Phoenix Framework Installer 5.2.1.1 (from http://www.ctr-electronics.com/hro.html#product_tabs_technical_resources)
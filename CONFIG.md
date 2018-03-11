Robot Configuration
======================================

This file denotes the different configurable aspects of the robot.

Robot Ports & CAN Bus IDs
-------------------------------------------

CAN Bus IDs:

1. Front Left Motor: 1
2. Front Right Motor: 2
3. Rear Left Motor: 3
4. Rear Right Motor: 4
5. Lift Motor: 5
6. Climb Motor: 6
7. Pneumatics Control Module: 10
8. Power Distribution Panel: 11

Analog Input Ports:

- Analog Input 0: Gyro Rate

Digital Input/Output Ports:

- 0: Unused
- 1: (Output) Open Claw
- 2: (Output) Close Claw
- 3: (Output) Extend Grabber
- 4: (Output) Retract Grabber
- 5: Unused
- 6: Unused
- 7: Unused
- 8: (Input) Lift Encoder Channel A
- 9: (Input) Lift Encoder Channel B

SPI/I2C Ports:

- I2C Onboard: ADXL345 Accelerometer (4G range) (needs testing!)

Camera:

- Axis Camera (10.42.47.11 or axis-camera) (needs reconfiguration!)

Joystick Inputs
-------------------------------------------

Axes:

- Drive Forward/Backward: LStick Y+
- Drive Right/Left: LStick X+
- Rotate CW/CCW: RStick X+
- Raise Lift: DPad Up
- Lower Lift: DPad Down
- Extend Climber: B
- Open Claw: A
- Extend Grabber: RT
- Retract Grabber: LT

TODO
-------------------------------------------

- Get climber programmed
- Get lift/grabber programmed
- Reverse drive controls (swap around motor names?)
- Fix camera connection
- Configure pneumatics
- Troubleshoot connector for accelerometer
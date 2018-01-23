# Autonomous Code Overview

The purpose of the autonomous code is to replicate the thought process of a two-man team
in operating the robot during Teleop mode. Generally, since one person operates the robot,
they are tasked with judging distances and determining the driving of the robot, whereas
the spotter or coach helps judge distances, tracks other robots, and keeps the overall
strategy of the match going. Since our opponent robots during Autonomous will generally
not interfere with our operation, we can generally ignore them and focus on scoring.
With this in mind, the strategy for autonomous is fairly straightforward; the navigator
will use its knowledge of the game to establish a strategy, and the driver will perform
the strategy. Knowledge of the arcade board will be encoded into a Field Map, which is
coded to yield information as quickly as possible (so as not to overload the watchdogs),
and allow modification as soon as new information is available. By using a simplified
representation, we let it act as a strategy board, rather than an accurate view of the
field. The driver is then tasked with taking the base information and using it to hone
in on the various targets, perform the tasks required, and feed back information that
is relevant to the match.

## Field Map

The Field Map represents our simplified version of the state of the field. It consists
of a set of Obstacles (physical objects to avoid), Cubes (power cubes), and Targets
(switch plates and scale plates) arranged in a 50x100 grid of 6.5"x6.5" units (roughly
half of the width of a power cube). This was chosen because most of the dimensions fall
closely along those lines, and it reduces the complexity of the calculations just far
enough to be simple to process within the limited memory space of the robot, especially
if we forego the standard A* pathfinder with a simpler pathfinding algorithm that
prefers long, direct spans to optimal routes.

## Autonomous Initialization and State Machine

Right off the bat, the navigator and the driver need to agree on the overall strategy for
the autonomous period. Since we start at one of three locations (left, center, right), and
we have to respond to the game-specific code telling us where our alliance's switch and
scale plates are, we will start by feeding this information into the Field Map and
generating the list of targets on our map. From that, we feed in the standard Cube and
Obstacle lists, and let the individual cells be generated on-the-fly (we may change this
later if it proves too hard to recalculate). Temporary Obstacles may be added to the Field
Map as needed, which will linger for a short period of time before disappearing. (We will
need to ensure the field map also gets a slice of each frame, to remove these.)

From this, our overall strategy is to perform the following actions:

- Set up our map of the field
- Cross the base line
- Deliver cubes
- Get more cubes

As such, the base state machine is as follows:

	Start -> BaseLine -> ScoreCube <-> FindCube -> Idle

When we start, the navigator has to assess the field, in this case, from the game-specific
code delivered by the FMS. Once we have that information, and the field map set, we move 
into BaseLine.

BaseLine is simple enough to process; we need to do some basic pathfinding to a point just
past the base line. At the beginning, the Navigator will find a base path across the line,
by creating two targets representing the regions on either side of the near side Switch;
the Driver will then execute this path, notifying the Navigator if it runs into a robot.
If another robot is encountered, a Temporary Obstacle will be added to the map, and the
Navigator will route around it. This new route will be delivered to the Driver, and the
old one discarded. Since it's too complicated for our algorithm to resolve right now,
we won't pass any new information into the Field Map, and instead simply assume any
collisions are other robots. Once we think we've passed the base line, we'll move into the
ScoreCube state.

Since we start with a cube pre-loaded, we'll do a quick check at the start of ScoreCube to
make sure this still holds true, and also check periodically against the vision system to
make sure we still have it throughout this run (ignoring other tasks during this process).
If we ever don't have it, we'll transition immediately to FindCube. Otherwise, we'll begin
by having the Navigator consult the Field Map to choose a target, and plot out a set of
Tasks to navigate to the target (which will be the switch or scale plate that's closest, 
favoring switches over scales in the case of a tie). After that, the set of Tasks will be
delivered to the Driver, which will execute them. Since we have no way of determining when
we've scored a cube, we'll just keep dropping them into the nearest scoring target. We
do a basic path-follow, same as BaseLine, to get to a point chosen to be close to the target
and angle ourselves towards the target's center, then raise our arm to the right level,
extend, and drop the cube, before retracting and lowering to ground level. This will all be
the responsibility of the Driver to coordinate. After the cube has been dropped, we report
our current location back into the Navigator and move into the FindCube state.

Once we are in the FindCube state, we need to assess our current location, and figure out
which cube is closest (using cubes not in the stack by preference in case of a tie). Once
the Navigator figures that out, it will plot a path to the cube using the same path-find
algorithm used for the other two stages. The Driver will then execute this, informing the
Navigator of any collisions, and more importantly, of missing cubes or "found" cubes. This
will be accomplished by doing a vision-detect periodically instead of path-follow, and then
doing one when the path is complete. If a cube is located, we stop what we're doing, turn
towards it, and pick it up (raise, extend, grab, retract, lower). Once we have the cube, we
remove it from the Field Map (if it was expected), and transition immediately into the
ScoreCube state. If we get to where the cube is and don't find it, we remove it from the
FieldMap, and re-initialize the FindCube state.

If we get to the point where no more cubes remain, when entering FindCube, we transition
to Idle and stay put where we are. In the future, this may be where we start picking random
points around the field, navigating to them, and hoping we run into a cube, at which point
we'd pick it up and transition to ScoreCube.

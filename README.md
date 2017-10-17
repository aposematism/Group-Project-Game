# Here Be The Title

[![pipeline status](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/badges/master/pipeline.svg)](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/commits/master)

**Read "Usage" section for instructions on running**

## Developers

| Name           | Handle      | Maintains                | Code Reviews | Tests                 | Git log
|----------------|-------------|--------------------------|--------------|-----------------------|--------
| Dylan McKay    | @mckaydyla  | Pathfinding, Game logic  |              | Entity, Parsing       | `git log --author="Dylan McKay"`
| Mark Metcalfe  | @metcalmark | Entity system            |              | Parser                | `git log --author="Mark Metcalfe"`
| Jacob Woods    | @mckiewjaco | Controller, Audio        | Game logic   | Graphics              | `git log --author="J Woods"`
| Jordan Milburn | @milburjord | Map parser, ReverseParser|              | Entity, Pathfinding   | `git log --author="Aposematism"`
| Josh Weir      | @weirjosh   | Game Graphics            |              | GUI, Game logic       | `git log --author="Josh"`

## Statistics and information

| Name                      | Link
|---------------------------|-----
| Commit log                | [GitLab](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/commits/master)
| Branch list               | [GitLab](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/branches)
| Commit versus time graphs | [GitLab](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/graphs/master)
| History tree              | [GitLab](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/network/master)


## Usage

Here Be The Title uses Maven for compilation and running tests.

All generated class files and executables are placed into the `target/` folder.

The JAR **must** be run alongside the `res/` folder. It is sufficient to create the JAR,
and put the `res/` folder next to it to get it working.

```bash
# Creates an executable JAR file
# Will be placed at target/herebethetitle-0.0.1.jar
mvn package

# Compiles and runs all JUnit tests
mvn verify
```

## Layout

All source code is located in the [`src/`](src/) folder.

All third-party libraries can be found in JAR form inside the [`lib/`](lib/) folder.

## Libraries

### Pathfinding (Dylan McKay)

[`com.swen.herebethetitle.pathfinding`](src/com/swen/herebethetitle/pathfinding)

This library implements the [A*](https://en.wikipedia.org/wiki/A*_search_algorithm) search algorithm.

This is used by the other libraries to perform tasks such as plotting a path for an enemy
to take in order to attack the player.

Tests are located inside the [`com.swen.herebethetitle.pathfinding.test`](src/com/swen/herebethetitle/pathfinding/test) package.

Pathfinding is done via the [`Graph`](src/com/swen/herebethetitle/pathfinding/Graph.java) class with the `findPath` method in particular.

### Game logic (Dylan McKay)

[`com.swen.herebethetitle.logic`](src/com/swen/herebethetitle/logic)

This library is responsible for **all** updates to the game state.

In order to move a player on the board, interact with objects, or even attack an enemy,
the [`GameLogic`](src/com/swen/herebethetitle/logic/GameLogic.java) class is used.

This library is also responsible for

* Controlling the [non playable characters](src/com/swen/herebethetitle/entity/NPC.java), such as monsters.
* Timing and triggering of discussions with non-playable characters

Tests are located inside the [`com.swen.herebethetitle.logic.test`](src/com/swen/herebethetitle/logic/test) package.

### Entity system (Mark Metcalfe)

[`com.swen.herebethetitle.entity`](src/com/swen/herebethetitle/entity) (and its subpackages)

This library implements both interative and static game objects such as
items, NPCs, terrain elements and the like.

This is used by other libraries to load, save, interact and draw the game objects.

Interactions are handled by interact(), of which every entity has its own implementation.

Tests are located inside the [`com.swen.herebethetitle.entity.tests`](src/com.swen.herebethetitle.entity.tests) package.

### Control (Jacob Woods)

[`com.swen.herebethetitle.control`](src/com/swen/herebethetitle/control)

This library wraps the game graphics in a JavaFX application and handles events from user input.
The Graphics library is used to draw the world. Menus are built here using JavaFX.

Controller also handles the "main loop", running from a JavaFX timeline that periodically calls update() internally,
which calls internal methods and methods in GameLogic to update the game state and then renders the world.

Through the UI built by Controller, the player can load and save the game with the parsing library.

### Audio (Jacob Woods)

[`com.swen.herebethetitle.audio`](src/com/swen/herebethetitle/audio)

Library for playing sounds and songs in the main JavaFX thread.
Loads all sounds as AudioClips internally on construct, which are then played when observer-pattern methods
are called on it by the game logic, or externally by use of integer soundcodes to play specific sounds or songs.

### Map parser (Jordan Milburn)

[`com.swen.herebethetitle.parser`](src/com/swen/herebethetitle/parser)

This library is responsible for loading, saving and generating the level in the game. It handles this in three classes which each perform a unique function. This involves two libraries, mapParser and EntityParser, which taking the textfile of the level (new_game.txt) and turning it into a region containing all the tiles and entities involved. As well as ReverseParser which handles saving from the region to a textfile and loading from a textfile to a region.

### Game Graphics (Josh Weir)
This library is responsible for rendering the game, World and HUD included. It has helper classes
for drawing text boxes, health bars, inventory slots, and also manages the transformation of 
game world coordinates (grid based) into screen coordinates (pixel based).
[`com.swen.herebethetitle.graphics`](src/com/swen/herebethetitle/graphics)

<information here>


# Here Be The Title

[![pipeline status](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/badges/master/pipeline.svg)](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/commits/master)

## Developers

| Name           | Handle      | Maintains                | Code Reviews | Tests                 | Git log
|----------------|-------------|--------------------------|--------------|-----------------------|--------
| Dylan McKay    | @mckaydyla  | Pathfinding, Game logic  |              | Entity, Parsing       | `git log --author="Dylan McKay"`
| Mark Metcalfe  | @metcalmark | Entity system            |              | Parser                | `git log --author="Mark Metcalfe"`
| Jacob Woods    | @mckiewjaco | GUI                      |              | Graphics              | `git log --author="J Woods"`
| Jordan Milburn | @milburjord | Map parser               |              | Entity, Pathfinding   | `git log --author="Aposematism"`
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

### GUI (Jacon Woods)

[`com.swen.herebethetitle.model`](src/com/swen/herebethetitle/view)

This library wraps the game graphics in a JavaFX application and handles events from user input.
The Graphics library is used to draw the world, with an HUD implemented in this libary being drawn on top of it.
The menus and other GUI elements are also implemented here.

### Map parser (Jordan Milburn)

[`com.swen.herebethetitle.parser`](src/com/swen/herebethetitle/parser)

<information here>

### Game Graphics (Josh Weir)

[`com.swen.herebethetitle.graphics`](src/com/swen/herebethetitle/graphics)

<information here>


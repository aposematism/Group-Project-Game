# Here Be The Title

[![pipeline status](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/badges/master/pipeline.svg)](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/commits/master)

## Developers

| Name           | Handle      | Maintains     | Code Reviews | Tests | Git log
|----------------|-------------|---------------|--------------|-------|--------
| Dylan McKay    | @mckaydyla  | Pathfinding   |              |       | `git log --author="Dylan McKay"`
| Mark Metcalfe  | @metcalmark | Entity system |              |       | `git log --author="Mark Metcalfe"`
| Jacob Woods    | @mckiewjaco | Game model    |              |       | `git log --author="J Woods"`
| Jordan Milburn | @milburjord | Map parser    |              |       | `git log --author="Aposematism"`
| Josh Weir      | @weirjosh   | JavaFX GUI    |              |       | `git log --author="Josh"`

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

### Entity system (Mark Metcalfe)

[`com.swen.herebethetitle.entity`](src/com/swen/herebethetitle/entity) (and its subpackages)

This library implements both interative and static game objects such as
items, NPCs, terrain elements and the like.

This is used by other libraries to load, save, interact and draw the game objects.

Interactions are handled by interact(), of which every entity has its own implementation.

Tests are located inside the [`com.swen.herebethetitle.entity.tests`](src/com.swen.herebethetitle.entity.tests) package.

### Game model library (Jacon Woods)

[`com.swen.herebethetitle.model`](src/com/swen/herebethetitle/model)

<information here>

### Map parser (Jordan Milburn)

[`com.swen.herebethetitle.parser`](src/com/swen/herebethetitle/parser)

<information here>

### JavaFX GUI (Josh Weir)

[`com.swen.herebethetitle.graphics`](src/com/swen/herebethetitle/graphics)

<information here>


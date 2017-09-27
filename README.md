# Here Be The Title

[![pipeline status](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/badges/master/pipeline.svg)](https://gitlab.ecs.vuw.ac.nz/metcalmark/swen-222-group-project/commits/master)

## Developers

| Name           | Handle      | Maintains     | Code Reviews | Tests |
|----------------|-------------|---------------|--------------|-------|
| Dylan McKay    | @mckaydyla  | Pathfinding   |
| Mark Metcalfe  | @metcalmark | Entity system |
| Jacob Woods    | @mckiewjaco | Game model    |
| Jordan Milburn | @milburjord | Map parser    |
| Josh Weir      | @weirjosh   | JavaFX GUI    |

## Usage

Here Be The Title uses Maven for compilation and running tests.

All generated class files and executables are placed into the `target/` folder.

```bash
# Creates an executable JAR file
# Will be placed at target/herebethetitle-0.0.1.jar
mvn compile

# Compiles and runs all JUnit tests
mvn verify
```

## Libraries

### Pathfinding (Dylan McKay)

[`com.swen.herebethetitle.pathfinding`](src/com/swen/herebethetitle/pathfinding)

This library implements the [A*](https://en.wikipedia.org/wiki/A*_search_algorithm) search algorithm.

This is used by the other libraries to perform tasks such as plotting a path for an enemy
to take in order to attack the player.

Tests are located inside the [`com.swen.herebethetitle.pathfinding.test`](src/com/swen/herebethetitle/pathfinding/test) package.

Pathfinding is done via the [`Graph`](src/com/swen/herebethetitle/pathfinding/Graph.java) class with the `findPath` method in particular.

### Entity system (Mark Metcalfe)

[`com.swen.herebethetitle.entity`](src/com/swen/herebethetitle/entity)

<information here>

### Game model library (Jacon Woods)

[`com.swen.herebethetitle.model`](src/com/swen/herebethetitle/model)

<information here>

### Map parser (Jordan Milburn)

[`com.swen.herebethetitle.parser`](src/com/swen/herebethetitle/parser)

<information here>

### JavaFX GUI (Josh Weir)

[`com.swen.herebethetitle.graphics`](src/com/swen/herebethetitle/graphics)

<information here>


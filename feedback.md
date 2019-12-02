# mp2 Feedback

## Grade: 5.0

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
|Yes|No|9.29|

## Test Results
### cpen221.mp2.graph.Task1
| Test Status | Count |
| ----------- | ----- |
|tests|9|
|errors|0|
|skipped|0|
|failures|0|
### cpen221.mp2.graph.Task2
| Test Status | Count |
| ----------- | ----- |
|tests|20|
|errors|0|
|skipped|0|
|failures|0|

## Test Coverage
96
## Other Comments
Good RIs, AFs, specs. Very clean and readable code in shorter methods. Some longer methods doDijkstra, getAllShortestPaths, MillenniumFalcon methods could use some inline comments and potentially use helper methods. Some tests missing asserts.

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `88` to `88`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `218` to `229`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `80` to `80`

**minor: Similar blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `235` to `246`

**major: Avoid too many `return` statements within this method.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `125` to `125`

**major: Method `main` has 63 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `70` to `137`

**minor: Method `main` has a Cognitive Complexity of 38 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/controllers/Kamino.java`; lines `70` to `137`

**minor: Method `equals` has a Cognitive Complexity of 11 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Edge.java`; lines `42` to `53`

**minor: Method `pruneRandomEdges` has a Cognitive Complexity of 10 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `615` to `665`

**major: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `342` to `350`

**minor: Method `doDijkstra` has a Cognitive Complexity of 25 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `321` to `366`

**minor: Method `shortestPath` has a Cognitive Complexity of 7 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `283` to `310`

**minor: Method `pruneRandomEdges` has 41 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `615` to `665`

**minor: Method `isConnected` has a Cognitive Complexity of 10 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `45` to `56`

**minor: Method `addEdge` has a Cognitive Complexity of 7 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `111` to `124`

**minor: Method `remove` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `202` to `215`

**minor: Method `getAllShortestPaths` has a Cognitive Complexity of 24 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `375` to `423`

**minor: `Graph` has 25 methods (exceeds 20 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `32` to `667`

**major: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `397` to `405`

**minor: Method `getAllShortestPaths` has 39 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `375` to `423`

**minor: Method `search` has a Cognitive Complexity of 8 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `505` to `528`

**minor: Method `doDijkstra` has 41 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `321` to `366`

**major: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `411` to `419`

**minor: File `Graph.java` has 392 lines of code (exceeds 250 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `1` to `667`

**minor: Method `getEdge` has a Cognitive Complexity of 6 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `586` to `598`

**minor: Method `minimumSpanningTree` has 26 lines of code (exceeds 25 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `431` to `465`

**minor: Method `minimumSpanningTree` has a Cognitive Complexity of 12 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `431` to `465`

**major: Identical blocks of code found in 2 locations. Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `355` to `363`

**minor: Method `diameter` has a Cognitive Complexity of 16 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/graph/Graph.java`; lines `540` to `560`

**minor: Method `endPhase` has a Cognitive Complexity of 9 (exceeds 5 allowed). Consider refactoring.**
file: `src/main/java/cpen221/mp2/gui/GUI.java`; lines `252` to `280`


## Checkstyle Results
### `MillenniumFalcon.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 7 | 8 | `Unused import - cpen221.mp2.models.Link.` |
| 10 | 8 | `Unused import - cpen221.mp2.util.Heap.` |
| 12 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 17 | None | `Type Javadoc comment is missing an @author tag.` |
| 74 | 5 | `Missing a Javadoc comment.` |
| 133 | 5 | `Missing a Javadoc comment.` |
| 187 | None | `Line is longer than 100 characters (found 101).` |
| 197 | None | `Line is longer than 100 characters (found 105).` |
| 205 | 5 | `Missing a Javadoc comment.` |
| 7 | 8 | `Unused import - cpen221.mp2.models.Link.` |
| 10 | 8 | `Unused import - cpen221.mp2.util.Heap.` |
| 12 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 17 | None | `Type Javadoc comment is missing an @author tag.` |
| 74 | 5 | `Missing a Javadoc comment.` |
| 133 | 5 | `Missing a Javadoc comment.` |
| 187 | None | `Line is longer than 100 characters (found 101).` |
| 197 | None | `Line is longer than 100 characters (found 105).` |
| 205 | 5 | `Missing a Javadoc comment.` |
### `Graph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 19 | None | `Type Javadoc comment is missing an @author tag.` |
| 34 | 5 | `Missing a Javadoc comment.` |
| 37 | 5 | `Missing a Javadoc comment.` |
| 40 | 19 | `'if' is not followed by whitespace.` |
| 40 | 34 | `'{' is not preceded with whitespace.` |
| 41 | 23 | `'if' is not followed by whitespace.` |
| 41 | 39 | `',' is not followed by whitespace.` |
| 41 | 69 | `'{' is not preceded with whitespace.` |
| 50 | 5 | `Missing a Javadoc comment.` |
| 290 | 11 | `Catching 'Exception' is not allowed.` |
| 363 | 11 | `'if' is not followed by whitespace.` |
| 363 | 38 | `'{' is not preceded with whitespace.` |
| 492 | 11 | `'if' is not followed by whitespace.` |
| 492 | 39 | `'{' is not preceded with whitespace.` |
| 499 | 15 | `'if' is not followed by whitespace.` |
| 499 | 52 | `'{' is not preceded with whitespace.` |
| 523 | 11 | `'if' is not followed by whitespace.` |
| 529 | None | `Line is longer than 100 characters (found 140).` |
| 534 | 9 | `'}' at column 9 should be on the same line as the next part of a multi-block statement (one that directly contains multiple blocks: if/else-if/else or try/catch/finally).` |
| 535 | 13 | `'else' is not followed by whitespace.` |
| 535 | 13 | `'{' is not preceded with whitespace.` |
| 542 | 5 | `Missing a Javadoc comment.` |
| 542 | 39 | `'{' is not preceded with whitespace.` |
| 544 | 12 | `'for' is not followed by whitespace.` |
| 544 | 29 | `'{' is not preceded with whitespace.` |
| 545 | 15 | `'if' is not followed by whitespace.` |
| 545 | 33 | `'{' is not preceded with whitespace.` |
| 590 | None | `Missing a Javadoc comment.` |
| 594 | 13 | `Missing a Javadoc comment.` |
| 594 | 13 | `Redundant 'public' modifier.` |
| 19 | None | `Type Javadoc comment is missing an @author tag.` |
| 34 | 5 | `Missing a Javadoc comment.` |
| 37 | 5 | `Missing a Javadoc comment.` |
| 40 | 19 | `'if' is not followed by whitespace.` |
| 40 | 34 | `'{' is not preceded with whitespace.` |
| 41 | 23 | `'if' is not followed by whitespace.` |
| 41 | 39 | `',' is not followed by whitespace.` |
| 41 | 69 | `'{' is not preceded with whitespace.` |
| 50 | 5 | `Missing a Javadoc comment.` |
| 290 | 11 | `Catching 'Exception' is not allowed.` |
| 363 | 11 | `'if' is not followed by whitespace.` |
| 363 | 38 | `'{' is not preceded with whitespace.` |
| 492 | 11 | `'if' is not followed by whitespace.` |
| 492 | 39 | `'{' is not preceded with whitespace.` |
| 499 | 15 | `'if' is not followed by whitespace.` |
| 499 | 52 | `'{' is not preceded with whitespace.` |
| 523 | 11 | `'if' is not followed by whitespace.` |
| 529 | None | `Line is longer than 100 characters (found 140).` |
| 534 | 9 | `'}' at column 9 should be on the same line as the next part of a multi-block statement (one that directly contains multiple blocks: if/else-if/else or try/catch/finally).` |
| 535 | 13 | `'else' is not followed by whitespace.` |
| 535 | 13 | `'{' is not preceded with whitespace.` |
| 542 | 5 | `Missing a Javadoc comment.` |
| 542 | 39 | `'{' is not preceded with whitespace.` |
| 544 | 12 | `'for' is not followed by whitespace.` |
| 544 | 29 | `'{' is not preceded with whitespace.` |
| 545 | 15 | `'if' is not followed by whitespace.` |
| 545 | 33 | `'{' is not preceded with whitespace.` |
| 590 | None | `Missing a Javadoc comment.` |
| 594 | 13 | `Missing a Javadoc comment.` |
| 594 | 13 | `Redundant 'public' modifier.` |


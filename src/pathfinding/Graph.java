package pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.Tile;

/**
 * A grid-based graph that can be used to find shortest paths.
 * 
 * @author dylan
 */
public class Graph {
    /**
     * An A* search heuristic .
     * @author dylan
     */
    public interface Heuristic {
        /**
         * Evaluates the heuristic for two nodes.
         * @param node The source node.
         * @param goal The destination node.
         * @param grid The grid which contains the nodes.
         */
        double calculate(Tile node, Tile goal, PathfindingGrid grid);
    }

    /**
     * The grid we are performing pathfinding in.
     */
    private final PathfindingGrid grid;

    /**
     * The source tile.
     */
    private final Tile source;
    /**
     * The destination tile.
     */
    private final Tile destination;
    /**
     * The heuristic.
     */
    private final Heuristic heuristic;

    /**
     * The set of nodes already evaluated.
     */
    private Set<Tile> closedSet = new HashSet<Tile>();
    /**
     * The set of nodes that have been discovered but not evaluated yet.
     */
    private Set<Tile> openSet = new HashSet<Tile>();

    /**
     * For each node, which node it can most efficiently be reached from.
     * If a node can be reached from many nodes, cameFrom will eventually contain the
     * most efficient previous step.
     */
    private Map<Tile, Tile> cameFrom = new HashMap<Tile, Tile>();

    /**
     * For each node, the cost of getting from the start node to that node.
     */
    private Map<Tile, Double> gScore = new HashMap<Tile, Double>();

    /**
     * For each node, the total cost of getting from the start node to the goal
     * by passing by that node. That value is partly known, partly heuristic.
     */
    private Map<Tile, Double> fScore = new HashMap<Tile, Double>();

    /**
     * The cached result of the pathfinding operation.
     */
    private Optional<Path> result = Optional.empty();

    /**
     * Creates a new graph.
     * @param grid The grid which both tiles are located in.
     * @param source The source tile.
     * @param destination The destination tile.
     */
    public Graph(PathfindingGrid grid, Tile source, Tile destination) {
        this(grid, source, destination, Heuristics::EucledianDistance);
    }

    /**
     * Creates a new graph.
     * @param grid The grid which both tiles are located in.
     * @param source The source tile.
     * @param destination The destination tile.
     * @param heuristic The heuristic used in the A* algorithm.
     */
    public Graph(PathfindingGrid grid, Tile source, Tile destination, Heuristic heuristic) {
        this.grid = grid;
        this.source = source;
        this.destination = destination;
        this.heuristic = heuristic;
    }

    /**
     * Finds the most optimal path between two nodes
     * 
     * The ordering of the parameters does not matter, as the path will always
     * be the same.
     */
    public Optional<Path> findPath() {
        if (this.result.isPresent()) return this.result;
        
        Optional<List<Tile>> tiles = solve();
        
        // Check if there is a valid path.
        if (tiles.isPresent()) {
            Path path = new Path(tiles.get().stream().map(Tile::getLocation).collect(Collectors.toList()));
            this.result = Optional.of(path);
            return this.result;
        } else {
            // Nodes are unreachable.
            return Optional.empty();
        }
    }

    /**
     * Finds the shortest path between two points.
     *
     * NOTE: Shows pseudocode inline for readability.
     *
     * Pseudocode taken from Wikipedia, licensed under CC BY-SA 3.0.
     * https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode
     */
    protected Optional<List<Tile>> solve() {
        // The set of currently discovered nodes that are not evaluated yet.
        // Initially, only the start node is known.
        // openSet := {start}
        openSet.add(this.source);
        
        // The cost of going from start to start is zero.
        // gScore[start] := 0
        gScore.put(source, 0.0);

        // For the first node, that value is completely heuristic.
        // fScore[start] := heuristic_cost_estimate(start, goal)
        fScore.put(source, getCost(this.source, this.destination));

        // while openSet is not empty
        while (!openSet.isEmpty()) {
            // current := the node in openSet having the lowest fScore[] value
            Tile current = getCheapestTile();

            // if current = goal
            if (current == this.destination)
                return Optional.of(reconstructPath(cameFrom));

            // openSet.Remove(current)
            openSet.remove(current);
            // closedSet.Add(current)
            closedSet.add(current);

            // for each neighbor of current
            Collection<Tile> neighbours = getReachableAdjacentTiles(current);
            for (Tile neighbour : neighbours) {
                // if neighbor in closedSet
                if (closedSet.contains(neighbour))
                    continue; // Ignore the neighbor which is already evaluated.

                // // The distance from start to a neighbor
                // tentative_gScore := gScore[current] + dist_between(current,
                // neighbor)
                double tenative_gScore = current.getLocation().distanceBetween(neighbour.getLocation());

                // if neighbor not in openSet // Discover a new node
                if (!openSet.contains(neighbour))
                    openSet.add(neighbour);
                // else if tentative_gScore >= gScore[neighbor]
                else if (tenative_gScore >= gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY))
                    continue; // This is not a better path.

                // This path is the best until now. Record it!
                // cameFrom[neighbor] := current
                cameFrom.put(neighbour, current);
                // gScore[neighbor] := tentative_gScore
                gScore.put(neighbour, tenative_gScore);
                // fScore[neighbor] := gScore[neighbor] +
                // heuristic_cost_estimate(neighbor, goal)
                fScore.put(neighbour, getCost(neighbour, this.destination));
            }
        }

        // No path found, nodes are unreachable from eachother.
        return Optional.empty();
    }

    /**
     * Builds a Path object from the final result of A*.
     * @param cameFrom A map containing each parent and its predecessor in the path.
     */
    private List<Tile> reconstructPath(Map<Tile, Tile> cameFrom) {
        Tile current = this.destination;

        // total_path := [current]
        List<Tile> total_path = new ArrayList<Tile>();
        total_path.add(destination);

        // while current in cameFrom.Keys:
        while (cameFrom.containsKey(current)) {
            // current := cameFrom[current]
            current = cameFrom.get(current);
            // total_path.append(current)
            total_path.add(current);
        }
        Collections.reverse(total_path);
        return total_path;
    }

    /**
     * Gets the cost between two tiles.
     */
    protected double getCost(Tile from, Tile to) {
        double costFromStartToFrom = gScore.get(from);
        return costFromStartToFrom + this.heuristic.calculate(from, to, grid);
    }
    
    /**
     * Gets neighbouring tiles that we can actually travel through.
     */
    protected Collection<Tile> getReachableAdjacentTiles(Tile tile) {
        return grid.getAdjacent(tile)
                   .stream()
                   .filter(t -> t.isPenetrable())
                   .collect(Collectors.toList());
    }

    /**
     * Gets the cheapest tile from the open set.
     */
    protected Tile getCheapestTile() {
        Tile cheapestTile = openSet.iterator().next();
        double cheapestTileCost = fScore.getOrDefault(cheapestTile, Double.POSITIVE_INFINITY);

        for (Tile node : openSet) {
            double nodeScore = fScore.getOrDefault(node, Double.POSITIVE_INFINITY);

            if (nodeScore < cheapestTileCost) {
                cheapestTile = node;
                cheapestTileCost = nodeScore;
            }
        }

        return cheapestTile;
    }
}

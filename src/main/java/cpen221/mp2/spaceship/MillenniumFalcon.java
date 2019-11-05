package cpen221.mp2.spaceship;

import cpen221.mp2.controllers.GathererStage;
import cpen221.mp2.controllers.HunterStage;
import cpen221.mp2.controllers.Spaceship;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.ImGraph;
import cpen221.mp2.models.Link;
import cpen221.mp2.models.Planet;
import cpen221.mp2.models.PlanetStatus;
import cpen221.mp2.util.Heap;

import java.util.*;

/**
 * An instance implements the methods needed to complete the mission.
 * Hunt finds Kamino using the least amount of fuel possible
 * Gather collects as much spice as possible before returning to Earth
 */
public class MillenniumFalcon implements Spaceship {
    long startTime = System.nanoTime(); // start time of rescue phase

    @Override
    public void hunt(HunterStage state){

        PlanetStatus[] nStats;
        double maxSignal = 0;
        int maxId = 0;

        while(!state.onKamino()) {
            nStats = state.neighbors();

            for(PlanetStatus s : nStats){
                if(s.signal() >= maxSignal){
                    maxId = s.id();
                    maxSignal = s.signal();
                }
            }

            state.moveTo(maxId);

        }
    }

    @Override
    public void gather(GathererStage state) {

        ImGraph graph = state.planetGraph();
        Planet kamino = state.currentPlanet();
        Planet earth = state.earth();
        Set<Planet> allPlanets = state.planets();

        Set<Planet> visited = new HashSet<>();
        Planet mostEfficient = earth;
        TreeMap<Double, Planet> scores = new TreeMap<>();
        allPlanets.remove(kamino);
        allPlanets.remove(earth);
        double totalSpice = 0;
        for(Planet p : allPlanets){
            double score = 0;
            score += p.spice();
            totalSpice += score;
            scores.put(score,p);
        }

        Map descendingScores = scores.descendingMap();

        ArrayList<Planet> bestPlanets = new ArrayList<>(descendingScores.values());

        while(state.fuelRemaining() > 0){
            for(Planet p: bestPlanets){
                if(p.equals(state.currentPlanet()) || visited.contains(p))
                    continue;
                double distToEarth = graph.pathLength(graph.shortestPath(p,earth));
                double distFromHere = graph.pathLength(graph.shortestPath(state.currentPlanet(),p));
                if(distToEarth + distFromHere <= state.fuelRemaining()){
                    moveOnPath(graph.shortestPath(state.currentPlanet(), p), state, visited);
                }else{
                    moveOnPath(graph.shortestPath(state.currentPlanet(), state.earth()), state, new HashSet<>());
                    return;
                }
            }
        }



    }

    private void moveOnPath(List<Planet> path, GathererStage state, Set<Planet> visited){
        for(int i = 1; i < path.size(); i++){
            state.moveTo(path.get(i));
            visited.add(path.get(i));
        }
    }
}


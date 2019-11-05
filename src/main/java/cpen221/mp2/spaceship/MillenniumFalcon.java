package cpen221.mp2.spaceship;

import cpen221.mp2.controllers.GathererStage;
import cpen221.mp2.controllers.HunterStage;
import cpen221.mp2.controllers.Spaceship;
import cpen221.mp2.graph.ImGraph;
import cpen221.mp2.models.Link;
import cpen221.mp2.models.Planet;
import cpen221.mp2.models.PlanetStatus;
import cpen221.mp2.util.Heap;

import java.util.*;

/**
 * An instance implements the methods needed to complete the mission.
 */
public class MillenniumFalcon implements Spaceship {
    long startTime = System.nanoTime(); // start time of rescue phase

    @Override
    public void hunt(HunterStage state){

        PlanetStatus[] nStats;

        double maxSignal = 0;
        int maxId = 0;
        Set<Integer> deadEnds = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> blackList = new HashSet<>();

        while(!state.onKamino()) {
            visited.add(state.currentID());
            nStats = state.neighbors();
            ArrayList<PlanetStatus> stats = new ArrayList<>();
            for(PlanetStatus ps: nStats){
                if(!deadEnds.contains(ps.id())){
                    stats.add(ps);
                }
            }
            if(stats.size() == 1){
                deadEnds.add(state.currentID());
                state.moveTo(stats.get(0).id());
            }
            else {
                maxId = 0;
                maxSignal = 0;
                for (PlanetStatus s : nStats) {
                    if (s.signal() >= maxSignal && !deadEnds.contains(s.id()) && !visited.contains(s.id()) && !blackList.contains(s.id())) {
                        maxId = s.id();
                        maxSignal = s.signal();
                    }
                }
                if(maxSignal == 0){
                    deadEnds.add(state.currentID());
                    blackList.add(state.currentID());
                    if(stats.isEmpty()){
                        hunt(state, blackList);
                        return;
                    }
                    else {
                        state.moveTo(stats.get(0).id());
                    }
                }
                else{
                    state.moveTo(maxId);
                }
            }
        }
    }

    public void hunt(HunterStage state, Set<Integer> blackList){

        PlanetStatus[] nStats;

        double maxSignal = 0;
        int maxId = 0;
        Set<Integer> deadEnds = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        while(!state.onKamino()) {
            visited.add(state.currentID());
            nStats = state.neighbors();
            ArrayList<PlanetStatus> stats = new ArrayList<>();
            for(PlanetStatus ps: nStats){
                if(!deadEnds.contains(ps.id())){
                    stats.add(ps);
                }
            }
            if(stats.size() == 1){
                deadEnds.add(state.currentID());
                state.moveTo(stats.get(0).id());
            }
            else {
                maxId = 0;
                maxSignal = 0;
                for (PlanetStatus s : nStats) {
                    if (s.signal() >= maxSignal && !deadEnds.contains(s.id()) && !visited.contains(s.id() )) {
                        maxId = s.id();
                        maxSignal = s.signal();
                    }
                }
                if(maxSignal == 0){
                    deadEnds.add(state.currentID());
                    if(stats.isEmpty()){
                        hunt(state, blackList);
                        return;
                    }
                    else {
                        state.moveTo(stats.get(0).id());
                    }
                }
                else{
                    state.moveTo(maxId);
                }
            }
        }
    }

    @Override
    public void gather(GathererStage state) {

        ImGraph graph = state.planetGraph();
        Planet kamino = state.currentPlanet();
        Planet earth = state.earth();
        Set<Planet> allPlanets = state.planets();
        Set<Planet> visited = new HashSet<>();
        TreeMap<Double, Planet> scores = new TreeMap<>();

        allPlanets.remove(kamino);
        allPlanets.remove(earth);

        for(Planet p : allPlanets){
            double score = 0;
            score += p.spice();
            scores.put(score,p);
        }

        Map descendingScores = scores.descendingMap();
        ArrayList<Planet> bestPlanets = new ArrayList<>(descendingScores.values());

        while(state.fuelRemaining() > 0){
            visited.add(state.currentPlanet());
            for(Planet p: bestPlanets){
                if(p.equals(state.currentPlanet())|| visited.contains(p))
                    continue;
                double distToEarth = graph.pathLength(graph.shortestPath(p,earth));
                double distFromHere = graph.pathLength(graph.shortestPath(state.currentPlanet(),p));
                if(distToEarth + distFromHere <= state.fuelRemaining()){
                    moveOnPath(graph.shortestPath(state.currentPlanet(), p), state, visited);
                }else{
                    moveOnPath(graph.shortestPath(state.currentPlanet(), state.earth()), state, visited);
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


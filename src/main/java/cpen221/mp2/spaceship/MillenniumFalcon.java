package cpen221.mp2.spaceship;

import cpen221.mp2.controllers.GathererStage;
import cpen221.mp2.controllers.HunterStage;
import cpen221.mp2.controllers.Spaceship;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.ImGraph;
import cpen221.mp2.models.GameModel;
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

        ImGraph<Planet, Link> graph = state.planetGraph();
        Set<Planet> allPlanets = state.planets();
        Set<Planet> visited = new HashSet<>();
        List<Link> minTree = graph.minimumSpanningTree();
        Set<Planet> canVisit = new HashSet<>();
        Map<Planet,Link> neighbors = new HashMap<>();
        boolean goingBack = true;

        while(!state.currentPlanet().equals(state.earth())){
            visited.add(state.currentPlanet());
            for(Planet p : allPlanets){

            }


            if(goingBack){
                state.moveTo((Planet) graph.shortestPath(state.currentPlanet(),state.earth()).get(1));
            }
        }


    }

}

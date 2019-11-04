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
        Set<Planet> allPlanets = state.planets();
        Object[] planetList = allPlanets.toArray();

        Set<Planet> visited = new HashSet<>();
        List<Link> minTree = graph.minimumSpanningTree();
        Set<Planet> canVisit = new HashSet<>();
        boolean goingBack = false;
        boolean onTheWay = false;

        Planet mostEfficient = state.earth();
        double efficiency = 0;

        while (!(state.currentPlanet().equals(state.earth()) && goingBack) ){

            visited.add(state.currentPlanet());
            efficiency = 0;


            if(state.currentPlanet().equals(mostEfficient)){
                onTheWay = false;
            }

            canVisit = graph.search(state.currentPlanet(), graph.diameter()/100000);

            System.out.println(canVisit.toString());

            if(!onTheWay && !goingBack) {
                for (int i = 0; i < canVisit.size(); i++) {
                    Planet temp = (Planet) planetList[i];

                    int tempSpice = 0;
                    List<Planet> spiceList =  graph.shortestPath(state.currentPlanet(),temp);
                    for(Planet p : spiceList){
                        tempSpice += p.spice();
                    }

                    double fuelToTemp = graph.pathLength(spiceList);
                    double tempEfficiency = tempSpice / fuelToTemp;

                    double fuelToEarth = graph.pathLength(graph.shortestPath(temp, state.earth()));

                    if (tempEfficiency > efficiency) {
                        if (fuelToTemp + fuelToEarth <= state.fuelRemaining()) {
                                onTheWay = true;
                                efficiency = tempEfficiency;
                                mostEfficient = temp;
                        }
                    }

                    if(!onTheWay){
                        goingBack = true;
                    }
                }
                System.out.println(mostEfficient);
            }else{
                state.moveTo((Planet) graph.shortestPath(state.currentPlanet(), mostEfficient).get(1));
            }

            if (goingBack) {
                state.moveTo((Planet) graph.shortestPath(state.currentPlanet(), state.earth()).get(1));
            }


        }

    }
}


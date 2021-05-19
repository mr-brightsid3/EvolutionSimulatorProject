package agh.cs.oop.Objects;

import agh.cs.oop.Enums.MoveDirection;
import agh.cs.oop.Interfaces.IEngine;
import agh.cs.oop.Interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SimulationEngine implements IEngine {

    public List<MoveDirection> directionsList;
    public List<Animal> animalList;
    public IWorldMap tmpMap;
    public int animalCount;

    public SimulationEngine(MoveDirection[] directions, RectangularMap map, Vector2d[] positions){
        for (Vector2d position : positions) {
            map.placeAnimal(new Animal(map, position));
            animalCount += 1;
        }
        directionsList = Arrays.asList(directions);
        tmpMap = map;
        //TODO: not sure if it is correct! same in second constructor
        animalList = new ArrayList<>(map.animals.values());

    }
//TODO: get to know if possible and how I can merge these two constructors
    public SimulationEngine(MoveDirection[] directions, Grassland map, Vector2d[] positions){
        for (Vector2d position : positions) {
            map.placeAnimal(new Animal(map, position));
            animalCount += 1;
        }
        directionsList = Arrays.asList(directions);
        tmpMap = map;
        animalList = new ArrayList<>(map.animals.values());

    }

    @Override
    public void run() {
        Iterator<Animal> animalIterator = animalList.iterator();
        Iterator<MoveDirection> directionIterator = directionsList.iterator();
        while (directionIterator.hasNext()) {
            while (animalIterator.hasNext()) {
                animalIterator.next().move(directionIterator.next(), tmpMap);
            }
            animalIterator = animalList.iterator();

        }


    }
}

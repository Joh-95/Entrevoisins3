package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    // Favorites
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> mFavorites = new ArrayList<>();

        for(int i = 0; i < neighbours.size(); i++) {
            Neighbour neighbour = neighbours.get(i);
            if (neighbour.isFav()) {
                mFavorites.add(neighbour);
            }
        }
        return mFavorites;
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        for(int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).equals(neighbour)) {
                neighbours.get(i).setFav(false);
            }
        }
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        for(int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).equals(neighbour)) {
                neighbours.get(i).setFav(true);
            }
        }
    }

}


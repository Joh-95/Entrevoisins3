package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighbourWithSuccess() {
        List<Neighbour> listFavoriteNeighbours = service.getFavoriteNeighbours();
        assertTrue(listFavoriteNeighbours.isEmpty());
    }

    @Test
    public void addFavoriteNeighbourWithSuccess() {
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        favoriteToAdd.setFav(true);
        service.addFavoriteNeighbour(favoriteToAdd);
        assertTrue(service.getFavoriteNeighbours().contains(favoriteToAdd));
    }

    @Test
    public void deleteFavoriteNeighbourWithSuccess() {
        List<Neighbour> listFavoriteNeighbours = service.getFavoriteNeighbours();
        Neighbour favoriteToDelete = service.getNeighbours().get(0);
        service.addFavoriteNeighbour(favoriteToDelete);
        favoriteToDelete.setFav(false);
        assertFalse(listFavoriteNeighbours.contains(favoriteToDelete));
    }
}

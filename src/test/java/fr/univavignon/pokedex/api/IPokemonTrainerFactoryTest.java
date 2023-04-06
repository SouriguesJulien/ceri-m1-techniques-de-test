package fr.univavignon.pokedex.api;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach; 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {

    IPokemonTrainerFactory iPokemonTrainerFactory;
    IPokedexFactory iPokedexFactory;
    IPokedex iPokedex;
    PokemonTrainer pokemonTrainer;

    @BeforeEach
    public void setUp() {
        iPokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        iPokedexFactory = Mockito.mock(IPokedexFactory.class);
        iPokedex = Mockito.mock(IPokedex.class);
        pokemonTrainer = new PokemonTrainer("Trainer", Team.MYSTIC, iPokedex);
        when(iPokemonTrainerFactory.createTrainer("Trainer", Team.MYSTIC, iPokedexFactory)).thenReturn(pokemonTrainer);
    }

    @Test
    public void getTrainerSucceeded() {
        assertEquals(pokemonTrainer, iPokemonTrainerFactory.createTrainer("Trainer", Team.MYSTIC, iPokedexFactory));
    }

}
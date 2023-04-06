package fr.univavignon.pokedex.api;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;    

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {

    IPokedexFactory pokedexFactory;
    IPokemonFactory pokemonFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokedex iPokedex;

    @BeforeEach
    public void setUp() {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        iPokedex = Mockito.mock(IPokedex.class);
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    public void createPokedexSucceeded() {
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(iPokedex);
        assertEquals(iPokedex, pokedexFactory.createPokedex(metadataProvider, pokemonFactory));
    }

}
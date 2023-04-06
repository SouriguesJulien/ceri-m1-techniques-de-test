package fr.univavignon.pokedex.api;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach; 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;
    Pokemon pokemon;

    @BeforeEach
    public void setUp() {
        System.out.println("!!!!!!!!!!!ICI!!!!!!!!!!!");
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

        // Mock for createPokemon()
        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenAnswer(mockData ->  {
            int index = mockData.getArgument(0);
            if(index >= 0 && index <= 150) {
                return pokemon;
            }
            throw new PokedexException("Invalid index");
            
        });
    }

    @Test
    public void getPokemonSucceeded() {
        assertEquals(pokemon, pokemonFactory.createPokemon(0,613,64,4000,4));
    }

    @Test
    public void ThrowPokedexException() {
        assertThrows(PokedexException.class, () -> pokemonFactory.createPokemon(-1,613,64,4000,4));
        assertThrows(PokedexException.class, () -> pokemonFactory.createPokemon(152,613,64,4000,4));
    }

}
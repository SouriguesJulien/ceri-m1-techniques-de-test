package fr.univavignon.pokedex.api;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach; 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IPokedexTest{

    ArrayList<Pokemon> listOfPokemons;
    Pokemon bulbizarre;
    Pokemon aquali;
    IPokedex iPokedex;

    @BeforeEach
    public void setUp() throws PokedexException {
        listOfPokemons = new ArrayList<>();
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        iPokedex = Mockito.mock(IPokedex.class);

        // Mock for size()
        when(iPokedex.size()).thenAnswer(mockData -> listOfPokemons.size());

        // Mock for AddPokemeon()
        when(iPokedex.addPokemon(any(Pokemon.class))).thenAnswer(mockData ->  {
            listOfPokemons.add(mockData.getArgument(0));
            return listOfPokemons.size() - 1;
        });

        // Mock for getPokemon()
        when(iPokedex.getPokemon(any(Integer.class))).thenAnswer(mockData -> {
            int index = mockData.getArgument(0);
            if(index < 0 || index > listOfPokemons.size() - 1) {
                throw new PokedexException("ERROR : Index Invalid");
            }
            return listOfPokemons.get(mockData.getArgument(0));
        });

        // Mock for getPokemons()
        when(iPokedex.getPokemons()).thenReturn(listOfPokemons);

        // Mock for getPokemons() with sort
        when(iPokedex.getPokemons(any())).thenAnswer(mockData -> {
            listOfPokemons.sort(mockData.getArgument(0));
            return listOfPokemons;
        });
    }

    @Test
    public void addPokemonSucceeded() {
        int index = iPokedex.addPokemon(bulbizarre);
        assertEquals(0, index);
    }

    @Test
    public void getPokemonSucceeded() throws PokedexException {
        iPokedex.addPokemon(bulbizarre);
        assertEquals(0, iPokedex.getPokemon(0).getIndex());
    }

    @Test
    public void getSizeSucceeded() {
        assertEquals(0, iPokedex.size());
        iPokedex.addPokemon(aquali);
        iPokedex.addPokemon(bulbizarre);
        assertEquals(2, iPokedex.size());
    }

    @Test
    public void getListOfPokemonSucceeded(){
        iPokedex.addPokemon(aquali);
        List<Pokemon> list = iPokedex.getPokemons();
        assertEquals(listOfPokemons, list);
    }

    @Test
    public void sortListSucceeded(){
        Comparator<Pokemon> comparator = (p1, p2) -> {
            if (p1.getIndex() < p2.getIndex()){
                return -1;
            }
            return 0;
        };
        iPokedex.addPokemon(aquali);
        iPokedex.addPokemon(bulbizarre);
        List<Pokemon> list = iPokedex.getPokemons(comparator);
        assertEquals(bulbizarre.getIndex(), list.get(0).getIndex());
    }

    @Test
    public void ThrowPokedexException() {
        assertThrows(PokedexException.class, () -> iPokedex.getPokemon(152));
        assertThrows(PokedexException.class, () -> iPokedex.getPokemon(-1));
    }

}
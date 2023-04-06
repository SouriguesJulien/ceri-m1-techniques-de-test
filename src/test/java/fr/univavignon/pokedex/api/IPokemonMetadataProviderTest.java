package fr.univavignon.pokedex.api;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach; 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    PokemonMetadata bulbizarreMetadata;
    PokemonMetadata aqualiMetadata;
    IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() throws PokedexException {
        bulbizarreMetadata = new PokemonMetadata(0,"Bulbizarre",126,126,90);
        aqualiMetadata = new PokemonMetadata(133,"Aquali",186,168,260);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);

        when(metadataProvider.getPokemonMetadata(0)).thenReturn(bulbizarreMetadata);
        when(metadataProvider.getPokemonMetadata(133)).thenReturn(aqualiMetadata);
    }

    @Test
    public void GetMetadataSucceeded() throws PokedexException {
        PokemonMetadata testMetaDataBulbizarre = metadataProvider.getPokemonMetadata(0);
        assertEquals(bulbizarreMetadata, testMetaDataBulbizarre);

        PokemonMetadata testMetaDataAquali = metadataProvider.getPokemonMetadata(133);
        assertEquals(aqualiMetadata, testMetaDataAquali);
    }
}
package tw.com.ispan.service;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.service.PokiApiClientService.Pokemon;

@SpringBootTest
public class PokeApiTemplateServiceTests {
    @Autowired
    private PokeApiTemplateService pokeApiTemplateService;

    @Autowired
    private PokiApiClientService pokiApiClientService;

    @Test
    public void testPokemon2() {
        Pokemon pokemon = pokiApiClientService.pokemon(1);
        System.out.println("pokemon="+pokemon);
    }

    @Test
    public void testPokemon1() {
        String json = pokeApiTemplateService.pokemon(1);
        JSONObject obj = new JSONObject(json);
        String name = obj.getJSONArray("forms").getJSONObject(0).getString("name");
        System.out.println("name="+name);
    }
}

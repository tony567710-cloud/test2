package tw.com.ispan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class PokiApiClientService {
    private RestClient client = RestClient.create("https://pokeapi.co/api/v2");

    public Pokemon pokemon(Integer id) {
        if(id!=null) {
            Pokemon body = client.get().uri("/pokemon/"+id).retrieve().body(Pokemon.class);
            return body;
        }
        return null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
	public record Pokemon(
			@JsonProperty("id") Integer id,
			@JsonProperty("name") String name,
			@JsonProperty("base_experience") Integer baseExperience,
			@JsonProperty("height") Integer height,
			@JsonProperty("weight") Integer weight,
			@JsonProperty("abilities") List<Abilities> abilities) {
		@JsonIgnoreProperties(ignoreUnknown = true)
		public record Abilities(
				@JsonProperty("is_hidden") Boolean isHidden,
				@JsonProperty("slot") Integer slot,
				@JsonProperty("ability") Ability ability) {
            @JsonIgnoreProperties(ignoreUnknown = true)
            public record Ability(
                @JsonProperty("name") String name,
                @JsonProperty("url") String url) {

            }
		}
	}
}

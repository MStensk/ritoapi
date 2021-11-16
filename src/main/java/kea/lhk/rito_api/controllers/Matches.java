package kea.lhk.rito_api.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import kea.lhk.rito_api.repository.MatchesRepository;
import kea.lhk.rito_api.repository.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
public class Matches {

    @Autowired
    MatchesRepository matches;

    @Autowired
    SummonerRepository summoner;


    @GetMapping("/matches/import")
    public String importMatches(){

        //TODO få puuid til at finde match id?=??=?

        //api output er et json array og ikke json object, unlike sumonners
        for(int i = 0;i<matches.findAllPuuid().size();i++){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Match> matchId= objectMapper.readValue(new URL(matches.url +
                        matches.findAllPuuid().get(i)+matches.startAndCount + matches.key),new TypeReference<List<Match>>(){});
                matches.saveAll(matchId);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "Diid it work?";
    }

}

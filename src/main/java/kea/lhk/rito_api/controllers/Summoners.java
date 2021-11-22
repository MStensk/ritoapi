package kea.lhk.rito_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.lhk.rito_api.models.Summoner;
import kea.lhk.rito_api.repository.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Summoners {


    @Autowired
    SummonerRepository summoners;



    @GetMapping("/summoners/import")
    public String importSummonersIntoDatabase(){
        for(int i = 0; i < summoners.usernames.length; i++) {

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Summoner summoner = objectMapper.readValue(new URL(summoners.url +
                        summoners.usernames[i] + summoners.key),Summoner.class);
                summoners.save(summoner);

            } catch (IOException e) {
                e.printStackTrace();
                return "error loading into database";
            }
        }
        return "characters been loaded into database";
    }
}

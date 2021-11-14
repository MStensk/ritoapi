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

@Controller
public class Summoners {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    SummonerRepository summoners;





    @GetMapping("/summoners")
    @ResponseBody
    public List<Summoner> getSummoners(){
        String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
        String key = "?api_key=RGAPI-2a42aad7-8edd-45b1-8ee8-c22dfd498b83";
        String[] usernames = {"erificeret", "dawho","weedsexlover",
                "dinger%20of%20dongs","bigfatbich","arnearnearnearne","death%20by%20arne",
                "koefoedskrrt","w0rst%20t34m","Moo%20Cow"};

        List<Summoner> summonerLists = new ArrayList<>();
        for(int i = 0; i < usernames.length; i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Summoner summoner = objectMapper.readValue(new URL(url + usernames[i] + key),Summoner.class);
                summoners.save(summoner);
                summonerLists.add(summoner);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return summonerLists;
    }
}

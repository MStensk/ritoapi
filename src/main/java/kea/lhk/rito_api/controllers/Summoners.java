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

@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)
@RestController
public class Summoners {

    @Autowired
    SummonerRepository summoner;

    @GetMapping("/summoners")
    public List<Summoner> getAllSummoners(){
        return summoner.findAll();
    }

    @GetMapping("/summoners/{summonerId}")
    public Summoner getSummonerById(@PathVariable String summonerId){
        return summoner.findById(summonerId).get();
    }

    @DeleteMapping("/summoners/{id}")
    public void deleteSummoner(@PathVariable String id){
        summoner.deleteById(id);
    }

    @PatchMapping("/summoners/{id}")
    public String updateSummonerInfo(@PathVariable String id, @RequestBody Summoner summonerToPatch){
        return summoner.findById(id).map(foundSummoner ->{
            if(summonerToPatch.getAccountId()!=null)foundSummoner.setAccountId(summonerToPatch.getAccountId());
            if(summonerToPatch.getName()!=null)foundSummoner.setName(summonerToPatch.getName());
            if(summonerToPatch.getProfileIconId()!=-1)foundSummoner.setProfileIconId(summonerToPatch.getProfileIconId());
            if(summonerToPatch.getPuuid()!=null)foundSummoner.setPuuid(summonerToPatch.getPuuid());
            if(summonerToPatch.getRevisionDate()!=-1)foundSummoner.setRevisionDate(summonerToPatch.getRevisionDate());
            if(summonerToPatch.getSummonerLevel()!=-1)foundSummoner.setSummonerLevel(summonerToPatch.getSummonerLevel());
            summoner.save(foundSummoner);
            return "summoner was patched";
        }).orElse("summoner was not patched");
    }

    @GetMapping("/summoners/import")
    public String importSummonersIntoDatabase(){
        for(int i = 0; i < summoner.usernames.length; i++) {

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Summoner summonerToSave = objectMapper.readValue(new URL(summoner.url +
                        summoner.usernames[i] + summoner.key),Summoner.class);
                summoner.save(summonerToSave);

            } catch (IOException e) {
                e.printStackTrace();
                return "error loading into database";
            }
        }
        return "characters been loaded into database";
    }

    @PostMapping("/summoners")
    public Summoner addSummoner(@RequestBody Summoner newSummoner){
        return summoner.save(newSummoner);
    }
}

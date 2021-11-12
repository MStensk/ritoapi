package kea.lhk.rito_api.controllers;

import kea.lhk.rito_api.models.Summoner;
import kea.lhk.rito_api.repository.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Summoners {

    @Autowired
    SummonerRepository summoners;

    @PostMapping("/summoners")
    public Summoner addSummoner(@RequestBody Summoner newChampion) {
        // don't allow the client to overwrite the id
        newChampion.setId(null);
        return summoners.save(newChampion);
    }

}

package kea.lhk.rito_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kea.lhk.rito_api.models.Champion;
import kea.lhk.rito_api.models.Summoner;
import kea.lhk.rito_api.repository.ChampionRepository;
import kea.lhk.rito_api.repository.MatchesRepository;
import kea.lhk.rito_api.repository.SummonerRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
public class Champions {

    @Autowired
    ChampionRepository champions;

    @GetMapping("/champions/import")
    public String importSummonersIntoDatabase(){


        String docChamps = null;
        try {
            docChamps = Jsoup.connect(champions.url).ignoreContentType(true).get().toString();

            for(int b = 0; b<30; b++) {
                Champion champion = new Champion();

                String champName = docChamps.substring(docChamps.indexOf("\"name\":")+8,
                        docChamps.indexOf("\"title\":")-2);

                System.out.println(champName);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "characters been loaded into database";
    }
}

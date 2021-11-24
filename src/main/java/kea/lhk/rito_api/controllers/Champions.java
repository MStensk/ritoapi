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

            try {
                Document document = Jsoup.connect(champions.url).ignoreContentType(true).get();

                Elements elements = document.select("body");
                for (Element element: elements) {
                    String champs = element.toString().substring(element.toString().indexOf("{\"type\":")+2,
                            element.toString().indexOf("}}}}")-1);
                    champs =champs.replace("\"","");
                    String[] champis = champs.split(",");
                    for(int i = 0; i<1; i++) {
                        String docChamps = Jsoup.connect(champions.url).ignoreContentType(true).get().toString();
                        docChamps = docChamps.substring(docChamps.indexOf("}};")+2);

                        for(int b = 0; b<30; b++) {
                            Champion champion = new Champion();

                            String champName = docChamps.substring(docChamps.indexOf("\"name\":")+8,
                                    docChamps.indexOf("\"title\":")-2);

                            System.out.println(champName);
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "error loading into database";
            }

        return "characters been loaded into database";
    }
}

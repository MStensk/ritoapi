package kea.lhk.rito_api.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import kea.lhk.rito_api.repository.MatchesRepository;
import kea.lhk.rito_api.repository.SummonerRepository;
import org.hibernate.jpa.boot.internal.PersistenceXmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
public class Matches {

    @Autowired
    MatchesRepository matches;

    @Autowired
    SummonerRepository summoner;

    private static final String BASE_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/";
    private static final String APIKEY = "?api_key=RGAPI-8923c021-46fc-46c4-8539-a6a83b8d23b1";


    @GetMapping("/matches/import")
    public String importMatches(){

        //finder alle summoners
        for (Summoner summoner : summoner.findAll()) {

            try {
                //bruger Jsoup til at fetch riots api
               Document document = Jsoup.connect(matches.url + summoner.getPuuid()+
                        matches.startAndCount + matches.key).ignoreContentType(true).get();

                Elements elements = document.select("body");
                for (Element element: elements) {
                    String eri = element.toString().substring(element.toString().indexOf("[")+1,
                            element.toString().indexOf("]")-1);
                    eri=eri.replace("\"","");
                    String[] eris = eri.split(",");

                    for (String matchid : eris) {
                        String document1 = Jsoup.connect(BASE_URL + matchid + APIKEY).ignoreContentType(true).get().toString();
                        document1 = document1.substring(document1.indexOf("\"assists\":")+10);
                        for (int i = 0; i < 10; i++) {
                            Match match = new Match();

                            match.setId(matchid);
                            String summonerName = document1.substring(document1.indexOf("\"summonerName\":")+16
                                    ,document1.indexOf("\",\"teamEarlySurrendered\":"));
                            match.setSummonerName(summonerName);

                            //Virker lortet?
                            int kills = Integer.parseInt(document1.substring(document1.indexOf("\"kills\":")+8,document1.indexOf(",\"lane\"")));
                            match.setKills(kills);


                            int deaths = Integer.parseInt(document1.substring(document1.indexOf("\"deaths\":")+9,document1.indexOf(",\"detectorWardsPlaced\"")));
                            match.setDeaths(deaths);

                            document1 = document1.substring(document1.indexOf("\"win\":")+6);
                            boolean win = Boolean.parseBoolean(document1.substring(0,document1.indexOf("}")));
                            match.setWin(win);

                            matches.save(match);

                            document1 = document1.substring(document1.indexOf("\"assists\":")+10);
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "Diid it work?";
    }

}

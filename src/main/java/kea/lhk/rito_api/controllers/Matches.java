package kea.lhk.rito_api.controllers;

import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import kea.lhk.rito_api.repository.MatchesRepository;
import kea.lhk.rito_api.repository.SummonerRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class Matches {

    @Autowired
    MatchesRepository match;

    @Autowired
    SummonerRepository summoner;

    private static final String BASE_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/";
    private static final String APIKEY = "?api_key=RGAPI-8923c021-46fc-46c4-8539-a6a83b8d23b1";


    //TODO add matchId eller gameId så man kan se hvem der har været inde i samme match
    @GetMapping("/matches/import")
    public String importMatches(){

        //finder alle summoners
        for (Summoner summoner : summoner.findAll()) {

            try {
                //bruger Jsoup til at fetch riots api, hvor man kan få matchIds
               Document document = Jsoup.connect(match.url + summoner.getPuuid()+
                        match.startAndCount + match.key).ignoreContentType(true).get();

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
                            if(i==9){
                                boolean win = Boolean.parseBoolean(document1.substring(0,document1.indexOf("}")));
                                match.setWin(win);
                            }


                            this.match.save(match);

                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "Diid it work?";
    }

    @GetMapping("/matches")
    public List<Match> findAllMatches(){
        return match.findAll();
    }

    @GetMapping("/matches/{id}")
    public Match findMatchById(@PathVariable String id){
        return match.findById(id).get();
    }

    @DeleteMapping("/matches/{id}")
    public void deleteById(@PathVariable String id){
        match.deleteById(id);
    }

    @PatchMapping("/matches/{id}")
    public String patchById(@PathVariable String id, @RequestBody Match matchToPatch){
        return match.findById(id).map(foundMatch -> {
            if(matchToPatch.getDeaths()!=-1)foundMatch.setDeaths(matchToPatch.getDeaths());
            if(matchToPatch.getKills()!=-1)foundMatch.setKills(matchToPatch.getKills());
            if(matchToPatch.getSummonerName()!=null)foundMatch.setSummonerName(matchToPatch.getSummonerName());
            if(matchToPatch.isWin()==false)foundMatch.setWin(matchToPatch.isWin());
            match.save(foundMatch);
            return "match has been patched";
        }).orElse("match has not been patched");
    }

    @PostMapping("/matches")
    public Match addMatch(@RequestBody Match newMatch){
        return match.save(newMatch);
    }
}

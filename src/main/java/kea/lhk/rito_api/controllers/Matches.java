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
    private static final String APIKEY = "?api_key=RGAPI-2ff57b62-3094-4e64-893c-348a47560682";


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

                    //iterating through each match_id
                    for (String matchid : eris) {
                        String document1 = Jsoup.connect(BASE_URL + matchid + APIKEY).ignoreContentType(true).get().toString();

                        //iterating through each player in the match
                        for (int i = 0; i < 10; i++) {
                            Match match1 = new Match();

                            match1.setMatchId(matchid);
                            String summonerName = document1.substring(document1.indexOf("\"summonerName\":")+16
                                    ,document1.indexOf("\",\"teamEarlySurrendered\":"));
                            match1.setSummonerName(summonerName);

                            //Virker lortet?
                            int kills = Integer.parseInt(document1.substring(document1.indexOf("\"kills\":")+8,
                                    document1.indexOf(",\"lane\"")));
                            match1.setKills(kills);


                            int deaths = Integer.parseInt(document1.substring(document1.indexOf("\"deaths\":")+9,
                                    document1.indexOf(",\"detectorWardsPlaced\"")));
                            match1.setDeaths(deaths);

                            document1 = document1.substring(document1.indexOf("\"win\":")+6);
                            boolean win = Boolean.parseBoolean(document1.substring(0,document1.indexOf("}")));
                            match1.setWin(win);

                           // String puuid = document1.substring(document1.indexOf("\"puuid\":")+9,document1.indexOf("\",\"quadraKills"));
                           // match1.setPuuid(puuid);

                            match.save(match1);

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

    @GetMapping("/matches")
    public List<Match> findAllMatches(){
        return match.findAll();
    }

    @GetMapping("/matches/{id}")
    public Match findMatchById(@PathVariable long id){
        return match.findById(id).get();
    }

    @DeleteMapping("/matches/{id}")
    public void deleteById(@PathVariable long id){
        match.deleteById(id);
    }

    @PatchMapping("/matches/{id}")
    public String patchById(@PathVariable long id, @RequestBody Match matchToPatch){
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

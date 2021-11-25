package kea.lhk.rito_api.repository;

import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Match,Long> {

    String key = "&api_key=RGAPI-2ff57b62-3094-4e64-893c-348a47560682";
    String startAndCount = "/ids?start=0&count=2";
    String url = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/";



}

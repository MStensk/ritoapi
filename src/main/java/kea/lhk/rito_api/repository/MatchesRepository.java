package kea.lhk.rito_api.repository;

import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Match,Long> {

    String key = "&api_key=RGAPI-6dff0c4e-8247-4f8a-bc8a-1d43f788cd68";
    String startAndCount = "/ids?start=0&count=20";
    String url = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/";

    @Query(value = "SELECT puuid FROM summoners",nativeQuery = true)
    List<String> findAllPuuid();

}

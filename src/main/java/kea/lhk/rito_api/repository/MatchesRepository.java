package kea.lhk.rito_api.repository;

import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Match,Long> {

    String key = "&api_key=RGAPI-e550d861-277c-4873-9c83-ff8dcfd26f5f";
    String startAndCount = "/ids?start=0&count=20";
    String url = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/";

    @Query(value = "SELECT puuid FROM summoners",nativeQuery = true)
    List<Match> findAllPuuid();
}

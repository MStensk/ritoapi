package kea.lhk.rito_api.repository;

import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner,Long> {

    String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    String key = "?api_key=RGAPI-6dff0c4e-8247-4f8a-bc8a-1d43f788cd68";
    String[] usernames = {"erificeret", "dawho","weedsexlover",
            "dinger%20of%20dongs","bigfatbich","arnearnearnearne","death%20by%20arne",
            "koefoedskrrt","w0rst%20t34m","Moo%20Cow"};

    @Query(value = "SELECT puuid FROM summoners",nativeQuery = true)
    List<Summoner> findAllPuuid();


}

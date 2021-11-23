package kea.lhk.rito_api.repository;

import kea.lhk.rito_api.models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner,String> {

    String url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    String key = "?api_key=RGAPI-8923c021-46fc-46c4-8539-a6a83b8d23b1";
    String[] usernames = {"erificeret","weedsexlover","death%20by%20arne","dinger%20of%20dongs",
            "kristian%20stoltz","Dawho","denhvem","arnearnearnearne",
            "bigfatbich", "act%20like%20baws"};



}

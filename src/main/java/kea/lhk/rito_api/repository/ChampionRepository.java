package kea.lhk.rito_api.repository;

import kea.lhk.rito_api.models.Champion;
import kea.lhk.rito_api.models.Match;
import kea.lhk.rito_api.models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionRepository extends JpaRepository<Champion,Long> {
    String url = "https://ddragon.leagueoflegends.com/cdn/11.18.1/data/en_US/champion.json";
    String key = "?api_key=RGAPI-2ff57b62-3094-4e64-893c-348a47560682";



}

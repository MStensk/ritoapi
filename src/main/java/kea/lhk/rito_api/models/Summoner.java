package kea.lhk.rito_api.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Table(name = "summoners")
@Entity
@Getter @Setter
public class Summoner {

    @Id
    @Column
    private String id;

    @Column
    private String accountId;

    @Column
    private String puuid;

    @Column
    private String name;

    @Column
    private int profileIconId;
  
    @Column
    private long revisionDate;

    @Column
    private int summonerLevel;






}

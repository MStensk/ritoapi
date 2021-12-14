package kea.lhk.rito_api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;



@Table(name = "summoners")
@Entity

public class Summoner {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    @Id
    @Column(nullable = false)
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

    /*@JsonIgnore
    @OneToMany(mappedBy = "summoner",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Match> match;*/


}

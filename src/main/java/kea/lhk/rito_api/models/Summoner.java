package kea.lhk.rito_api.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Data
@Table(name = "summoners")
@Entity
@Getter @Setter
public class Summoner {

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

    @JsonIgnore
    @OneToMany(mappedBy = "summoner",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Match> match;


}

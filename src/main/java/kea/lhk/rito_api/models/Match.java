package kea.lhk.rito_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Table(name = "matches")
@Entity
@Getter @Setter
public class Match {

    @Id
    @Column(nullable = false)
    private String id;

    @Column
    private String summonerName;

    @Column
    private boolean win;

    @Column
    private int kills;

    @Column
    private int deaths;

    @ManyToOne
    @JoinColumn(name = "puuid")
    @JsonIgnore
    private Summoner summoner;

}

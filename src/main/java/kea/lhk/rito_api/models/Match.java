package kea.lhk.rito_api.models;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column
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
    private Summoner summoner;

}

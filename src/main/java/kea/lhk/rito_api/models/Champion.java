package kea.lhk.rito_api.models;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Table(name = "champions")
@Entity
public class Champion {
    public Long getChampionId() {
        return championId;
    }

    public void setChampionId(Long championId) {
        this.championId = championId;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getChampionTitle() {
        return championTitle;
    }

    public void setChampionTitle(String championTitle) {
        this.championTitle = championTitle;
    }

    public String getChampionDesc() {
        return championDesc;
    }

    public void setChampionDesc(String championDesc) {
        this.championDesc = championDesc;
    }

    @Id

    @Column
    private Long championId;
    @Column
    private String championName;
    @Column
    private String championTitle;
    @Column
    private String championDesc;


}

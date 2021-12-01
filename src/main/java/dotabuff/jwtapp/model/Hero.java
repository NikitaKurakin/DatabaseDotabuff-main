package dotabuff.jwtapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "heroes")
public class Hero
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "matchcount")
    private int matchcount;

    @Column(name = "wincount")
    private int wincount;

    @Column(name = "lostcount")
    private int lostcount;

    @Column(name = "time")
    private int time;

    @Column(name = "kills")
    private int kills;

    @Column(name = "deaths")
    private int deaths;

    @Column(name = "assistances")
    private int assistances;
}

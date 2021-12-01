package dotabuff.jwtapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "matches")
@Data
public class Match
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_player")
    private Player playerId;

    @OneToOne
    @JoinColumn(name = "id_hero")
    private Hero heroId;

    @Column(name = "winloss")
    private boolean result;

    @Column(name = "time")
    private int time;

    @Column(name = "kills")
    private int kills;

    @Column(name = "deaths")
    private int deaths;

    @Column(name = "assistances")
    private int assistances;
}

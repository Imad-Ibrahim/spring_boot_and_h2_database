package sd4.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Entrant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="venue_ID")
    private Integer venueID;

    private String logo;

    @NotBlank(message = "{entrant.hostCityNotEmpty}")
    @Column(name="host_City")
    private String hostCity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="date_Of_Final")
    private Date dateOfFinal;

    @NotBlank(message = "{entrant.hostCountryNotEmpty}")
    @Column(name="hostCountry")
    private String hostCountry;

    @NotBlank(message = "{entrant.sectionNotEmpty}")
    private String section;

    @NotBlank(message = "{entrant.artistNotEmpty}")
    private String artist;

    @NotBlank(message = "{entrant.songNotEmpty}")
    private String song;

    @NotBlank(message = "{entrant.artistCountryNotEmpty}")
    @Column(name="artist_Country")
    private String artistCountry;

    @NotBlank(message = "{entrant.runningOrderNotEmpty}")
    @Column(name="running_Order")
    private String runningOrder;

    //@NotBlank(message = "{entrant.totalPointsNotEmpty}")
    @Column(name="total_Points")
    private Integer totalPoints;

    @NotBlank(message = "{entrant.rankNotEmpty}")
    private String rank;

    @NotBlank(message = "{entrant.qualifiedNotEmpty}")
    private String qualified;
}

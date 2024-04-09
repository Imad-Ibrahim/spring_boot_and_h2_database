package sd4.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
@Entity
public class EntrantVenueJoin {

    @Id
    @Column(insertable = false, updatable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id",referencedColumnName="ID", updatable = false, insertable = false)
    private Entrant entrant;

    @ManyToOne(optional=false)
    @JoinColumn(name="venueID",referencedColumnName="venue_ID", updatable = false, insertable = false)
    private Venue venue;


    public EntrantVenueJoin(Integer id, Entrant entrant, Venue venue) {
        this.id = id;
        this.entrant = entrant;
        this.venue = venue;
    }
}

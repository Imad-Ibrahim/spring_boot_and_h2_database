package sd4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="venue_ID")
    Integer venueID;
    String name;
    Integer capacity;
    String note;

    @ManyToOne
    @JoinColumn(name = "venueID")
    private Entrant entrant;
}
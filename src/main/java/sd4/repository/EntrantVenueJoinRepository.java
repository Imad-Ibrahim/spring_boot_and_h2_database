package sd4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sd4.model.EntrantVenueJoin;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntrantVenueJoinRepository extends CrudRepository<EntrantVenueJoin, Integer> {
    @Query("SELECT new EntrantVenueJoin(e.id, e, v) FROM Entrant e INNER JOIN Venue v ON e.venueID = v.venueID")
    List<EntrantVenueJoin> getEntrantVenueJoinList();

    @Query("SELECT new EntrantVenueJoin(e.id, e, v) FROM Entrant e INNER JOIN Venue v ON e.venueID = v.venueID WHERE e.id = :id")
    Optional<EntrantVenueJoin> getEntrantVenueJoinByID(@Param("id") Integer id);

    @Query("SELECT new EntrantVenueJoin(e.id, e, v) FROM Entrant e INNER JOIN Venue v ON e.venueID = v.venueID WHERE e.hostCountry LIKE %:hostCountry%  AND e.dateOfFinal BETWEEN :startDate AND :endDate  AND v.capacity > :capacity AND e.totalPoints BETWEEN :totalPointsFrom AND :totalPointsTo ORDER BY e.totalPoints")
    List<EntrantVenueJoin> getEntrantVenueJoinFullSearch(@Param("hostCountry") String hostCountry, @Param("startDate") Date startDate,  @Param("endDate") Date endDate, @Param("capacity") Integer capacity,  @Param("totalPointsFrom") Integer totalPointsFrom, @Param("totalPointsTo") Integer totalPointsTo);
}

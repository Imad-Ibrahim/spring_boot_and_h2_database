package sd4.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sd4.model.Entrant;

import java.util.Date;
import java.util.List;

@Repository
public interface EntrantRepository extends CrudRepository<Entrant, Integer> {
    List<Entrant> findAllByHostCountryContainingIgnoreCaseAndDateOfFinalBetweenOrderByTotalPoints(String country, Date dateFrom, Date dateTo);

    //List<Entrant> findAllByHostCountryContainingIgnoreCaseOrderByTotalPoints(String country);
    List<Entrant> findAllByHostCountryStartingWithOrderByTotalPoints(String country);

    @Query("SELECT e FROM Entrant e WHERE e.artistCountry LIKE  %:artistCountry% AND (e.section = :firstSemiFinal OR e.section = :secondSemiFinal OR e.section = :Final) AND e.dateOfFinal BETWEEN :dateFrom AND :dateTo ORDER BY e.totalPoints DESC LIMIT 10")
    List<Entrant> getEntrantByArtistCountryAndDateRangAndSection(@Param("artistCountry") String artistCountry,
                                           @Param("dateFrom") Date dateFrom,
                                           @Param("dateTo") Date dateTo,
                                           @Param("firstSemiFinal") String firstSemiFinal,
                                           @Param("secondSemiFinal") String secondSemiFinal,
                                           @Param("Final") String Final);

    @Query("SELECT e FROM Entrant e WHERE e.dateOfFinal= (SELECT MAX(e2.dateOfFinal) FROM Entrant e2) ORDER BY e.totalPoints DESC LIMIT 10")
    List<Entrant> getMostRecent();
}
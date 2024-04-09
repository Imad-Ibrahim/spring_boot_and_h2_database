package sd4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd4.model.Entrant;
import sd4.model.EntrantVenueJoin;
import sd4.repository.EntrantRepository;
import sd4.repository.EntrantVenueJoinRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EntrantService {

    @Autowired
    private EntrantRepository entrantRepository;

    public List<Entrant> findAllByHostCountryContainingIgnoreCaseAndDateOfFinalBetweenOrderByTotalPoints(String country, Date dateFrom, Date dateTo) {
        return (List<Entrant>) entrantRepository.findAllByHostCountryContainingIgnoreCaseAndDateOfFinalBetweenOrderByTotalPoints(country, dateFrom, dateTo);
    }

    public List<Entrant> findAll() {
        return (List<Entrant>) entrantRepository.findAll();
    }

    public Optional<Entrant> findOne(Integer id) {
        return entrantRepository.findById(id);
    }

    public List<Entrant> findAllByHostCountryStartingWithOrderByTotalPoints(String hostCountry) {
        return entrantRepository.findAllByHostCountryStartingWithOrderByTotalPoints(hostCountry);
    }

    public List<Entrant> getEntrantByArtistCountryAndDateRangAndSection(String artistCountry, Date dateFrom, Date dateTo, String firstSemiFinal, String secondSemiFinal, String Final) {
        return entrantRepository.getEntrantByArtistCountryAndDateRangAndSection(artistCountry, dateFrom, dateTo, firstSemiFinal, secondSemiFinal, Final);
    }

    public List<Entrant> getMostRecent() {
        return entrantRepository.getMostRecent();
    }

    public void saveEntrant(Entrant entrant) {
        entrantRepository.save(entrant);
    }
}

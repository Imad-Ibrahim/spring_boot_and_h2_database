package sd4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd4.model.EntrantVenueJoin;
import sd4.repository.EntrantVenueJoinRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EntrantVenueJoinService {

    @Autowired
    private EntrantVenueJoinRepository entrantVenueJoinRepository;

    public List<EntrantVenueJoin> findAll() {
        return (List<EntrantVenueJoin>) entrantVenueJoinRepository.getEntrantVenueJoinList();
    }

    public Optional<EntrantVenueJoin> getEntrantVenueJoinByID(Integer id) {
        return entrantVenueJoinRepository.getEntrantVenueJoinByID(id);
    }

    public List<EntrantVenueJoin> getEntrantVenueJoinFullSearch(String country, Date dFrom, Date dTo, Integer capacity, Integer totalPointsFrom, Integer totalPointsTo) {
        return entrantVenueJoinRepository.getEntrantVenueJoinFullSearch(country, dFrom, dTo, capacity, totalPointsFrom, totalPointsTo);
    }

}

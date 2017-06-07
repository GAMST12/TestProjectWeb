package ua.skillsup.webproject.dao;

import ua.skillsup.webproject.model.KindSportDto;
import ua.skillsup.webproject.model.TournamentDto;

import java.util.List;

public interface TournamentDao {
    List<TournamentDto> findAll();
    TournamentDto findById(long id);
    TournamentDto findByTournament(String tournament);
    List<TournamentDto> findByKindSport(KindSportDto kindSportDto);
    long create(TournamentDto tournamentDto);
    void update(TournamentDto tournamentDto);
    void delete(TournamentDto tournamentDto);

}

package ua.skillsup.webproject.dao;

import ua.skillsup.webproject.dao.entity.Tournament;
import ua.skillsup.webproject.model.ItemDto;
import ua.skillsup.webproject.model.TeamDto;
import ua.skillsup.webproject.model.TournamentDto;
import ua.skillsup.webproject.model.filter.ItemFilter;

import java.util.List;

public interface ItemDao {

    List<ItemDto> findAll();
    ItemDto findById(long id);
    List<ItemDto> findByTournament(TournamentDto tournamentDto);
    List<ItemDto> findByHomeTeam(TeamDto homeTeamDto);
    List<ItemDto> findByAwayTeam(TeamDto awayTeamDto);
    List<ItemDto> findByFilter(ItemFilter itemFilter);
    long create(ItemDto itemDto);
    void update(ItemDto itemDto);
    void delete(ItemDto itemDto);

}

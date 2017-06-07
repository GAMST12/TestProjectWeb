package ua.skillsup.webproject.dao;

import ua.skillsup.webproject.model.KindSportDto;
import ua.skillsup.webproject.model.TeamDto;

import java.util.List;

public interface TeamDao {
    List<TeamDto> findAll();
    TeamDto findById(long id);
    TeamDto findByTeamCityCountrySex(String team, String city, String country, String sex);
    List<TeamDto> findByTeam(String team);
    List<TeamDto> findByCity(String city);
    List<TeamDto> findByCountry(String country);
    List<TeamDto> findByKindSport(KindSportDto kindSportDto);
    long create(TeamDto teamDto);
    void update(TeamDto teamDto);
    void delete(TeamDto teamDto);
}

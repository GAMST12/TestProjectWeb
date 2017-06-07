package ua.skillsup.webproject.dao;

import ua.skillsup.webproject.model.KindSportDto;

import java.util.List;

public interface KindSportDao {
        List<KindSportDto> findAll();
        KindSportDto findById(long id);
        KindSportDto findBySport(String sport);
        long create(KindSportDto kindSportDto);
        void update(KindSportDto kindSportDto);
        void delete(KindSportDto kindSportDto);
}

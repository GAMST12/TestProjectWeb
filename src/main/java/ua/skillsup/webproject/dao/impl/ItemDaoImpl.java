package ua.skillsup.webproject.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.webproject.converter.EntityDtoConverter;
import ua.skillsup.webproject.dao.ItemDao;
import ua.skillsup.webproject.dao.entity.Item;
import ua.skillsup.webproject.model.ItemDto;
import ua.skillsup.webproject.model.TeamDto;
import ua.skillsup.webproject.model.TournamentDto;
import ua.skillsup.webproject.model.filter.ItemFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ua.skillsup.webproject.converter.EntityDtoConverter.convert;

@Repository
public class ItemDaoImpl implements ItemDao{
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        List<Item> list = sessionFactory.getCurrentSession()
                .createQuery("from Item").list();
        return list.stream()
                .map(EntityDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemDto findById(long id) {
        Item item = (Item) sessionFactory.getCurrentSession()
                .createQuery("select i from Item i where i.id = :id").setLong("id", id).uniqueResult();
        return convert(item);

    }

    @Transactional(readOnly = true)
    public List<ItemDto> findByTournament(TournamentDto tournamentDto) {
        List<Item> list =  sessionFactory.getCurrentSession()
                .createQuery("select i from Item as i " +
                        "join i.tournament as tournament  " +
                        "with tournament.id = :tournamentId").setLong("tournamentId", tournamentDto.getId()).list();
        return list.stream()
                .map(EntityDtoConverter::convert)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ItemDto> findByHomeTeam(TeamDto homeTeamDto) {
        List<Item> list =  sessionFactory.getCurrentSession()
                .createQuery("select i from Item as i " +
                        "join i.homeTeam as team  " +
                        "with team.id = :homeTeamId").setLong("homeTeamId", homeTeamDto.getId()).list();
        return list.stream()
                .map(EntityDtoConverter::convert)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ItemDto> findByAwayTeam(TeamDto awayTeamDto) {
        List<Item> list =  sessionFactory.getCurrentSession()
                .createQuery("select i from Item as i " +
                        "join i.awayTeam as team  " +
                        "with team.id = :awayTeamId").setLong("awayTeamId", awayTeamDto.getId()).list();
        return list.stream()
                .map(EntityDtoConverter::convert)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ItemDto> findByFilter(ItemFilter itemFilter) {
        LocalDate dateFrom = itemFilter.getDateItemFrom();
        LocalDate dateTo = itemFilter.getDateItemTo();
        Boolean finished = itemFilter.isFinished();

        List<Item> list =  sessionFactory.getCurrentSession()
                .createQuery("select i from Item i where i.itemDate > :dateFrom " +
                        "and i.itemDate <= :dateTo " +
                        "and i.finished = :finished").
                        setParameter("dateFrom", dateFrom).
                        setParameter("dateTo", dateTo).
                        setBoolean("finished", finished).list();
        return list.stream()
                .map(EntityDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public long create(ItemDto itemDto) {
        Item item = EntityDtoConverter.convert(itemDto);
        Session session = sessionFactory.getCurrentSession();
        try {
            session.persist(item);
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }
        return item.getId();

    }

    @Transactional(readOnly = false)
    public void update(ItemDto itemDto) {
        Item item = EntityDtoConverter.convert(itemDto);
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(item);
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }

    }

    @Transactional(readOnly = false)
    public void delete(ItemDto itemDto) {
        Item item = EntityDtoConverter.convert(itemDto);
        try {
            sessionFactory.getCurrentSession().delete(item);
        } catch (ConstraintViolationException e) {
            System.out.println(e);
        }

    }
}

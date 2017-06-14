package ua.skillsup.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.skillsup.webproject.dao.KindSportDao;
import ua.skillsup.webproject.dao.TeamDao;
import ua.skillsup.webproject.model.TeamDto;
import ua.skillsup.webproject.model.additional.AddingTeam;
import ua.skillsup.webproject.model.enumeration.Sex;
import ua.skillsup.webproject.model.message.ResponseMessage;
import ua.skillsup.webproject.service.BetService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class TeamController {

    @Autowired
    private KindSportDao kindSportDao;
    @Autowired
    private BetService betService;


    @RequestMapping(value = "/addteam", method = RequestMethod.GET)
    public String getAddTeam(Model model, HttpServletRequest request) {
        model.addAttribute("kindSportDto", kindSportDao.findAll());
        model.addAttribute("sexList", Sex.getAllSex());
        return "/addteam";
    }


    @RequestMapping(value = "/team", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addTeam(@RequestBody AddingTeam addingTeam) {
        return betService.addTeam(addingTeam.getTeam(), addingTeam.getCity(), addingTeam.getCountry(),
                addingTeam.getSex(), addingTeam.getKindSport());
    }
}

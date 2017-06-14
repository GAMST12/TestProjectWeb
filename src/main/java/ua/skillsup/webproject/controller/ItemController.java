package ua.skillsup.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.skillsup.webproject.dao.*;
import ua.skillsup.webproject.model.ItemDto;
import ua.skillsup.webproject.model.additional.AddingItem;
import ua.skillsup.webproject.model.message.ResponseMessage;
import ua.skillsup.webproject.service.BetService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ItemController {

    @Autowired
    private TournamentDao tournamentDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private BetService betService;


    @RequestMapping(value = "/additem", method = RequestMethod.GET)
    public String getAddItem(Model model, HttpServletRequest request) {
        model.addAttribute("tournamentDto", tournamentDao.findAll());
        model.addAttribute("teamDto", teamDao.findAll());
        return "/additem";
    }


    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addItem(@RequestBody AddingItem addingItem) {
        return betService.addItem(addingItem.getDate(), addingItem.getTournamentId(),
                addingItem.getHomeTeamId(), addingItem.getAwayTeamId(), addingItem.getKoefWin1(),
                addingItem.getKoefDraw(), addingItem.getKoefWin2());

    }
}

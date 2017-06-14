package ua.skillsup.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.skillsup.webproject.dao.*;
import ua.skillsup.webproject.filter.AuthFilter;
import ua.skillsup.webproject.model.BetDto;
import ua.skillsup.webproject.model.ItemDto;
import ua.skillsup.webproject.model.UserDto;
import ua.skillsup.webproject.model.additional.AddingBet;
import ua.skillsup.webproject.model.enumeration.Event;
import ua.skillsup.webproject.model.filter.ItemFilter;
import ua.skillsup.webproject.model.message.ResponseMessage;
import ua.skillsup.webproject.service.BetService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Controller
public class BetController {

    @Autowired
    private BetService betService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ItemDao itemDao;


    @RequestMapping(value = "/betting", method = RequestMethod.GET)
    public String bettingPage(Model model, HttpServletRequest request) {
        ItemFilter itemFilter =  new ItemFilter();
        itemFilter.setDateItemFrom(LocalDate.now());
        model.addAttribute("userDto", userDao.findByLogin((String) request.getSession().getAttribute(AuthFilter.AUTH_ATTR_LOGIN)));
        model.addAttribute("itemDto", itemDao.findByFilter(itemFilter));
        return "/betting";
    }


    @RequestMapping(value = "/bet", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addBet(@RequestBody AddingBet addingBet,
                                  HttpServletRequest request) {
        return betService.addBet((String)request.getSession().getAttribute(AuthFilter.AUTH_ATTR_LOGIN),
                addingBet.getItemId(), addingBet.getEvent(), addingBet.getKoef(), addingBet.getSum());
    }

}

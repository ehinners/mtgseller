package eu.wtc.mtgseller.controller;

import eu.wtc.mtgseller.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CardController
{
    private CardService cardService;

    @Autowired
    public CardController(CardService cs)
    {
        this.cardService = cs;
    }

    @RequestMapping("/list")
    public String showCardList(Model model)
    {
        model.addAttribute("cardList",cardService.getCardList());
        return "card-list";
    }
}

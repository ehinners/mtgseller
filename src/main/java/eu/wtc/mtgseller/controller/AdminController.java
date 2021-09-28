package eu.wtc.mtgseller.controller;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.service.CardInventoryService;
import eu.wtc.mtgseller.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    private CardService cardService;
    private CardInventoryService cardInventoryService;

    @Autowired
    public AdminController(CardService cs, CardInventoryService cis)
    {
        this.cardService = cs;
        this.cardInventoryService = cis;
    }

    @RequestMapping("")
    public String ShowAdminList(Model model)
    {
        List<MtgCard> cardsInInventory = new ArrayList<>();
        for(CardListing listing : cardInventoryService.getListingList())
        {
            cardsInInventory.add(cardService.getMtgCard(listing.getCardId()));
        }
        model.addAttribute("cardInventoryList", cardInventoryService.getListingList());
        model.addAttribute("cardList",cardsInInventory);
        return "supersecretadminpage3927";
    }

    @RequestMapping(value ="AddCard", method = RequestMethod.GET)
    public String ShowAdminAdder(Model model)
    {
        return "supersecretadminNewCardpage7752";
    }

    @RequestMapping(value ="AddCard", method = RequestMethod.POST)
    public String processAddCard(@RequestParam String newName, String newSet, String newImageLocation, String initialAvailable, String newPrice)
    {
        System.out.println(newName + "" + newSet + "" +newImageLocation + "" +initialAvailable + "" +newPrice);
        return "redirect:";
    }


    @RequestMapping(value ="UpdateCard", method = RequestMethod.GET)
    public String ShowAdminDetail(Model model)
    {
        return "supersecretadmindetailpage9888";
    }

    @RequestMapping(value ="UpdateCard", method = RequestMethod.POST)
    public String processUpdateCard(@RequestParam String newName, String newSet, String newImageLocation, String initialAvailable, String newPrice)
    {
        System.out.println(newName + "" + newSet + "" +newImageLocation + "" +initialAvailable + "" +newPrice);
        return "redirect:";
    }
}

package eu.wtc.mtgseller.controller;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.StateTax;
import eu.wtc.mtgseller.service.BasicCardInventoryService;
import eu.wtc.mtgseller.service.CardInventoryService;
import eu.wtc.mtgseller.service.CardService;
import eu.wtc.mtgseller.service.StateTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CardController
{
    private CardService cardService;
    private CardInventoryService cardInventoryService;
    private StateTaxService stateTaxService;

    @Autowired
    public CardController(CardService cs, CardInventoryService cis, StateTaxService sts)
    {
        this.cardService = cs;
        this.cardInventoryService = cis;
        this.stateTaxService = sts;
    }

    @RequestMapping("/list")
    public String showCardList(Model model)
    {
        List<MtgCard> cardsInInventory = new ArrayList<>();
        for(CardListing listing : cardInventoryService.getListingList())
        {
            cardsInInventory.add(cardService.getMtgCard(listing.getCardId()));
        }
        model.addAttribute("cardInventoryList", cardInventoryService.getListingList());
        model.addAttribute("cardList",cardsInInventory);
        model.addAttribute("taxList", stateTaxService.getStateTaxList());
        return "card-list";
    }


}

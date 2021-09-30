package eu.wtc.mtgseller.controller;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.MtgOrder;
import eu.wtc.mtgseller.service.CardInventoryService;
import eu.wtc.mtgseller.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
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

    static MtgCard MapCard(int newID, String newName, String newSet, String newImageLocation, String newPrice, String condition)
    {
        double tempPrice = 0.00;
        MtgCard newCard = new MtgCard();

        newCard.setId(newID);
        newCard.setCardName(newName);
        newCard.setSetName(newSet);
        newCard.setCardImageFileName(newImageLocation);
        newCard.setCondition(condition);

        try {
            tempPrice = Double.parseDouble(newPrice);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Exception thrown parsing new price");
        }
        newCard.setCostUSD(tempPrice);

        return newCard;

    }

    static CardListing MapListing(int listingID, int cardId, String initialAvailable)
    {

        int tempCount = 0;

        CardListing newInventory = new CardListing();

        try {
            tempCount = Integer.parseInt(initialAvailable);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Exception thrown parsing new count");
        }

        newInventory.setId(listingID);
        newInventory.setCardId(cardId);
        newInventory.setCount(tempCount);

        return newInventory;

    }

    @RequestMapping(value ="AddCard", method = RequestMethod.POST)
    public String processAddCard(@RequestParam String newName, String newSet, String newImageLocation, String initialAvailable, String newPrice, String condition)
    {
        int cardID = 0;
        for (MtgCard card : cardService.getCardList())
        {
            if(card.getId()>=cardID)
            {
                cardID = card.getId() + 1;
            }
        }
        System.out.println("CardID: " + cardID + " Name: "+ newName + " Set: " + newSet + " IMGLOC: " +newImageLocation + " Count: " +initialAvailable + " CND: "+ condition + " Price: " +newPrice);

        MtgCard newCard = AdminController.MapCard(cardID, newName, newSet, newImageLocation, newPrice, condition);

        int listingID = 0;
        for(CardListing listing : cardInventoryService.getListingList())
        {
            if(listing.getId() >= listingID)
            {
                listingID = listing.getId() + 1;
            }
        }

        CardListing newInventory = AdminController.MapListing(listingID, cardID, initialAvailable);

        cardService.saveCard(newCard);
        cardInventoryService.setCardListing(newInventory);

        return "redirect:";
    }


    @RequestMapping(value ="UpdateCard", method = RequestMethod.GET)
    public String ShowAdminDetail(Model model, @RequestParam("id") int cardID)
    {
        model.addAttribute("product", cardService.getMtgCard(cardID));
        List<CardListing>  listList = cardInventoryService.getListingList();
        CardListing tempListing = new CardListing();
        for(CardListing listing : listList)
        {
            if(listing.getCardId() == cardID)
            {
                tempListing = listing;
            }
        }
        model.addAttribute("listing", tempListing);
        return "supersecretadmindetailpage9888";
    }

    @RequestMapping(value ="UpdateCard", method = RequestMethod.POST)
    public String processUpdateCard(@ModelAttribute MtgCard product, @RequestParam String newName, String newSet, String newImageLocation, String initialAvailable, String newPrice, String condition)
    {
        int cardID = product.getId();

        System.out.println("CardID: " + cardID + " Name: "+ newName + " Set: " + newSet + " IMGLOC: " +newImageLocation + " Count: " +initialAvailable + " CND: "+ condition + " Price: " +newPrice);


        MtgCard newCard = AdminController.MapCard(cardID, newName, newSet, newImageLocation, newPrice, condition);


        int listingID = 0;
        for(CardListing listing : cardInventoryService.getListingList())
        {
            if(listing.getCardId() == newCard.getId())
            {
                listingID = listing.getId();
            }
        }

        CardListing newInventory = AdminController.MapListing(listingID, cardID, initialAvailable);



        cardService.saveCard(newCard);
        cardInventoryService.setCardListing(newInventory);

        return "redirect:";
    }
}

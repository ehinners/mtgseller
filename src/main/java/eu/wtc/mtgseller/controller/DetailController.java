package eu.wtc.mtgseller.controller;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.MtgOrder;
import eu.wtc.mtgseller.service.CardInventoryService;
import eu.wtc.mtgseller.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/list")
public class DetailController
{
    private CardService cardService;
    private CardInventoryService cardInventoryService;
    private double stateTax = 0.0765;

    static List<MtgOrder> cart = new ArrayList<>();

    @Autowired
    public DetailController(CardService cs, CardInventoryService cis)
    {
        this.cardService = cs;
        this.cardInventoryService = cis;
    }

    @RequestMapping(value ="update", method = RequestMethod.GET)
    public String ShowItemDetail(Model model, @RequestParam("id") int cardId)
    {
        model.addAttribute("product", cardService.getMtgCard(cardId));
        model.addAttribute("listing", cardInventoryService.getCardListingByCardID(cardId));
        return "item-detail";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String processAddToCart(@RequestParam int count, @RequestParam("id") int cardId)
    {
        MtgOrder tempOrder = new MtgOrder();
        boolean idFound = false;

        for(MtgOrder order : cart)
        {
            //if(order.getCard().getId() == cardId)
            if(order.getCardId() == cardId)
            {
                order.setQuantityChosen(count);
                tempOrder = order;
                idFound = true;
            }
        }

        if(!idFound)
        {
            tempOrder.setCardId(cardId);
            //tempOrder.setCard(cardService.getMtgCard(cardId));
            tempOrder.setQuantityChosen(count);
            cart.add(tempOrder);
        }


        if(tempOrder.getQuantityChosen()<=0)
        {
            cart.remove(tempOrder);
        }

        ///////////////////
        System.out.println("Cart Contains:");
        for(MtgOrder order : cart)
        {
            System.out.println("Card Id Of "+order.getCardId()+" x "+ order.getQuantityChosen());
        }
        //////////////////
        return "redirect:";
    }

    @RequestMapping(value="checkout")
    public String showCart(Model model)
    {
        MtgCard tempCard = new MtgCard();
        List<MtgCard> selectedCards = new ArrayList<>();
        double subtotal = 0.00;

        int tempId = 0;

        for(MtgOrder mo : cart)
        {
            tempId = mo.getCardId();
            if(!cardService.cardExists(tempId))
            {
                return "inventoryExceededRedirect";
            }
        }

        for(MtgOrder order:cart)
        {
            if(cardService.cardExists(order.getCardId()))
            {
                tempCard = cardService.getMtgCard(order.getCardId());
                selectedCards.add(cardService.getMtgCard(order.getCardId()));
                subtotal += order.getQuantityChosen()*tempCard.getCostUSD();
            }
            else
            {
                cart.remove(order);
            }
        }
        double tax = stateTax*subtotal;
        double total = subtotal+tax;
        model.addAttribute("cart", cart);
        model.addAttribute("selectedCards", selectedCards);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("tax", tax);
        model.addAttribute("total", total);
        return "checkout";
    }

    @RequestMapping("/confirm")
    public String updateListingsFromConfirmation()
    {
        int newCount;
        boolean inventoryExceeded = false;
        CardListing tempListing = new CardListing();
        int tempId = 0;

        int deubug = 0;
        System.out.println("Substep 1");

        for(MtgOrder mo : cart)
        {
            tempId = mo.getCardId();
            if(cardService.cardExists(tempId))
            {
                System.out.print(deubug + " ");
                tempListing = cardInventoryService.getCardListingByCardID(tempId);
                System.out.print("a");
                //System.out.println("listing amount :" + tempListing.getCount());
                System.out.println("chosen in cart: " + mo.getQuantityChosen());
                newCount = tempListing.getCount() - mo.getQuantityChosen();
                System.out.print("b");
                if(newCount < 0)
                {
                    inventoryExceeded = true;
                    System.out.print("c");
                }
                System.out.print("d.1");
            }
            else
            {
                inventoryExceeded = true;
                System.out.print("d.2");
            }
            deubug++;
        }

        System.out.println("Substep 2");

        if(inventoryExceeded)
        {
            System.out.println("Substep 3 a");
            return "inventoryExceededRedirect";
        }
        else
        {
            System.out.println("Substep 2 b");
            for(MtgOrder mo : cart)
            {
                tempId = mo.getCardId();
                tempListing = cardInventoryService.getCardListingByCardID(tempId);
                newCount = tempListing.getCount() - mo.getQuantityChosen();
                cardInventoryService.updateListing(tempId, newCount);
            }
            System.out.println("Substep 3");

            cart.clear();
        }
        System.out.println("Substep 4");
        return "redirect:";
    }
}

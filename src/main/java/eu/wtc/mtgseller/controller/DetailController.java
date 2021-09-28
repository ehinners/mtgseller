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
        List<CardListing> listList = new ArrayList<>();
        listList = cardInventoryService.getListingList();
        CardListing matchList = new CardListing();
        for(CardListing cl : listList)
        {
            if(cl.getCardId()==cardId)
            {
                matchList = cl;
            }
        }

        model.addAttribute("product", cardService.getMtgCard(cardId));
        model.addAttribute("listing", matchList);
        return "item-detail";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String processAddToCart(@RequestParam int count, @RequestParam("id") int cardId)
    {
        MtgOrder tempOrder = new MtgOrder();
        boolean idFound = false;

        for(MtgOrder order : cart)
        {
            if(order.getCard().getId() == cardId)
            {
                order.setQuantityChosen(count);
                tempOrder = order;
                idFound = true;
            }
        }

        if(!idFound)
        {
            tempOrder.setCard(cardService.getMtgCard(cardId));
            tempOrder.setQuantityChosen(count);
            cart.add(tempOrder);
        }
        else if(tempOrder.getQuantityChosen()<=0)
        {
            cart.remove(tempOrder);
        }

        ///////////////////
        System.out.println("Cart Contains:");
        for(MtgOrder order : cart)
        {
            System.out.println(order.getCard().getCardName()+" x "+ order.getQuantityChosen());
        }
        //////////////////
        return "redirect:";
    }

    @RequestMapping(value="checkout")
    public String showCart(Model model)
    {
        double subtotal = 0.00;
        for(MtgOrder order:cart)
        {
            subtotal += order.getQuantityChosen()*order.getCard().getCostUSD();
        }
        double tax = stateTax*subtotal;
        double total = subtotal+tax;
        model.addAttribute("cart", cart);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("tax", tax);
        model.addAttribute("total", total);
        return "checkout";
    }
}

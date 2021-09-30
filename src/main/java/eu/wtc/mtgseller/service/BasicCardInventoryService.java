package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgOrder;
import eu.wtc.mtgseller.repo.CardInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasicCardInventoryService implements CardInventoryService
{
    private CardInventoryRepository cardInventoryRepository;

    @Autowired
    public BasicCardInventoryService(CardInventoryRepository cir)
    {
        this.cardInventoryRepository = cir;
    }

    @Override
    public CardListing getListing(int listingId)
    {
        Optional<CardListing> l = cardInventoryRepository.findById(listingId);
        if(l.isPresent())
        {
            return l.get();
        }
        return null;
    }

    @Override
    public List<CardListing> getListingList()
    {
        List<CardListing> list = new ArrayList<>();
        cardInventoryRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void setCardListing(CardListing cardListing)
    {
        cardInventoryRepository.save(cardListing);
    }

    @Override
    public void updateListing(int cardID, int newCount)
    {
        if(newCount>=0)
        {
            CardListing cl = cardInventoryRepository.findCardListingByCardId(cardID);
            cl.setCount(newCount);
            cardInventoryRepository.save(cl);
        }
    }
}

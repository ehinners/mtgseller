package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.MtgOrder;

import java.util.List;

public interface CardInventoryService
{
    CardListing getListing(int listingId);

    List<CardListing> getListingList();

    public void updateListing(int cardID, int newCount);

    void setCardListing(CardListing cardListing);
}

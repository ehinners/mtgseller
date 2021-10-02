package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.MtgOrder;

import java.util.List;

public interface CardInventoryService
{
    CardListing getListing(int listingId);

    List<CardListing> getListingList();

    CardListing getCardListingByCardID(int cardID);

    public void updateListing(int cardID, int newCount);

    void setCardListing(CardListing cardListing);

    void deleteCardListing(int listingID);

    void deleteCardListingByCardID(int cardID);
}

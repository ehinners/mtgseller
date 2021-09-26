package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;

import java.util.List;

public interface CardInventoryService
{
    CardListing getListing(int listingId);

    List<CardListing> getListingList();
}

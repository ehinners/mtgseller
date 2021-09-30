package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.MtgCard;

import java.util.List;

public interface CardService
{
    MtgCard getMtgCard(int mtgCardId);

    List<MtgCard> getCardList();

    void saveCard(MtgCard mtgCard);
}

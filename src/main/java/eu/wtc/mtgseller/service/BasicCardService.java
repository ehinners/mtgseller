package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.repo.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasicCardService implements CardService
{
    private CardRepository cardRepository;

    @Autowired
    public BasicCardService(CardRepository cr)
    {
        this.cardRepository = cr;

    }

    @Override
    public MtgCard getMtgCard(int mtgCardId)
    {
        Optional<MtgCard> c = cardRepository.findById(mtgCardId);
        if(c.isPresent())
        {
            return c.get();
        }
        return null;
    }

    @Override
    public List<MtgCard> getCardList()
    {
        List<MtgCard> list = new ArrayList<>();
        cardRepository.findAll().forEach(list::add);
        //yes
        return list;
    }
}

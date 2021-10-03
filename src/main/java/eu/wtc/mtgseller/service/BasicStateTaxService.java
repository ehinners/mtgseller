package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.StateTax;
import eu.wtc.mtgseller.repo.CardRepository;
import eu.wtc.mtgseller.repo.StateTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicStateTaxService implements StateTaxService
{
    private StateTaxRepository stateTaxRepository;
    private StateTax primaryTax;

    @Autowired
    public BasicStateTaxService(StateTaxRepository str)
    {
        this.stateTaxRepository = str;

    }

    @Override
    public List<StateTax> getStateTaxList()
    {
        List<StateTax> list = new ArrayList<>();
        stateTaxRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public StateTax getPrimary()
    {
        return primaryTax;
    }

    @Override
    public void setPrimary(String stateInitials)
    {
        for(StateTax tax : getStateTaxList())
        {
            if(stateInitials.toUpperCase().equals(tax.getState_initials()))
            {
                primaryTax = tax;
            }
        }
    }
}

package eu.wtc.mtgseller.service;

import eu.wtc.mtgseller.entity.StateTax;

import java.util.List;

public interface StateTaxService
{
    List<StateTax> getStateTaxList();

    StateTax getPrimary();

    void setPrimary(String stateInitials);
}

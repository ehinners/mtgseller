package eu.wtc.mtgseller.repo;

import eu.wtc.mtgseller.entity.MtgCard;
import eu.wtc.mtgseller.entity.StateTax;
import org.springframework.data.repository.CrudRepository;

public interface StateTaxRepository extends CrudRepository<StateTax, Integer>
{
}

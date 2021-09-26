package eu.wtc.mtgseller.repo;

import eu.wtc.mtgseller.entity.CardListing;
import eu.wtc.mtgseller.entity.MtgCard;
import org.springframework.data.repository.CrudRepository;

public interface CardInventoryRepository extends CrudRepository<CardListing, Integer>
{

}

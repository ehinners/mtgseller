package eu.wtc.mtgseller.repo;

import eu.wtc.mtgseller.entity.MtgCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CardRepository extends CrudRepository<MtgCard, Integer>
{

}

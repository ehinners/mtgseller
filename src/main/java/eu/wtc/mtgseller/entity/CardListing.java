package eu.wtc.mtgseller.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name="card_inventory")
public class CardListing
{
    @Id
    @Column(name="ci_id")
    private int id;

    @Column(name="card_id")
    private int cardId;

    @Column(name="count")
    private int count;
}

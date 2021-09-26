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
@Table(name="mtgcard")
public class MtgCard
{
    @Id
    @Column(name="card_id")
    private int id;

    @Column(name = "quality")
    private String quality;
    // this should be changed to enum

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "cost_usd")
    private double costUSD;

    @Column(name = "set_name")
    private String setName;

    @Column(name = "card_image_file_name")
    private String cardImageFileName;

    /*
        Card Name
        Card Image (Depends on Name, Printing And Set)
        Card Set
        Card Printing (Special treatments, alternate arts, useful for basic Lands)
        Card Price (USD) (Depends on everything except number owned)
        Card Condition(D HP MP LP NM)
        (Damaged, Heavily Played, Moderately Played, Lightly Played, Near Mint)
        Foil

        Number Owned


        GUARANTEED TO BE 1 TO 1
        Card Price, Card Condition, and Foil
        maybe rename to Unit Price, Unit Condition, and Unit Foil

        Avoid:
         - Update Anomaly (When one piece of data must be manually updated in multiple places)
         - Insertion Anomaly (When data cannot be entered)
         - Deletion Anomaly (Loss of unrelated data lost if other piece of data is deleted)
    */
}

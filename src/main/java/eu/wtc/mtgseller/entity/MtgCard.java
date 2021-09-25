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

    @Column(name = "number_Have")
    private int numHave;
}

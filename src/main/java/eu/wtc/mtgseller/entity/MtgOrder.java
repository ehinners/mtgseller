package eu.wtc.mtgseller.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MtgOrder
{
    private MtgCard card;
    private int quantityChosen;
}

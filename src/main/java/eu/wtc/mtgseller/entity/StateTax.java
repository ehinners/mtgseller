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
@Table(name="state_tax")
public class StateTax
{
    @Id
    @Column(name="st_id")
    private int id;

    @Column(name="state_initials")
    private String state_initials;

    @Column(name="multiplier")
    private double multiplier;
}

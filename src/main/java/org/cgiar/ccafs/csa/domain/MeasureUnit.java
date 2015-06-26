package org.cgiar.ccafs.csa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the units database table.
 */
@Entity
@Table(name = "units")
public class MeasureUnit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String symbol;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        try {
            if (symbol.equals(" ") || symbol.equals("r2") || Integer.valueOf(symbol) > -1) {
                throw new IllegalArgumentException("Incorrect unit: " + symbol);
            }
        } catch (NumberFormatException n) {
            this.symbol = symbol;
        }
    }
}

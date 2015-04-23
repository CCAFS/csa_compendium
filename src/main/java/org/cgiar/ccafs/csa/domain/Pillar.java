package org.cgiar.ccafs.csa.domain;

/**
 * @author CARDILA
 */
public enum Pillar {
    PRODUCTION, ADAPTATION, MITIGATION;

    public static Pillar getByName(String name) {
        if (PRODUCTION.toString().equalsIgnoreCase(name)) return PRODUCTION;
        else if (ADAPTATION.toString().equalsIgnoreCase(name)) return ADAPTATION;
        else if (MITIGATION.toString().equalsIgnoreCase(name)) return MITIGATION;
        else throw new IllegalArgumentException("This must to be of the supported enumeration types");
    }
}



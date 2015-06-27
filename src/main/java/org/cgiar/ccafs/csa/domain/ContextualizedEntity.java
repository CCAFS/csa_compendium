package org.cgiar.ccafs.csa.domain;

import java.util.Set;

/**
 * Super class for those entities that maps attributes from context tables
 */
public interface ContextualizedEntity {

    Set<ContextValue> getContextValues();

}
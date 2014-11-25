package org.cgiar.ccafs.csa.domain;

import java.util.List;

/**
 * Super class for those entities that maps attributes from context tables
 * 
 */
public interface ContextualizedEntity  {		
	
	List<ContextValue> getContextValues();	

}
package org.cliffsun.individualproject.cmiblues;

import java.util.HashMap;
import java.util.Map;

import org.cliffsun.individualproject.grammar.AbstractTonesGrammar;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.grammar.ProductionRule;

public class GrammarRuleUsage {

	Map<ProductionRule, Integer> grammarUsageMap;
	
	public GrammarRuleUsage(AbstractTonesGrammar abstractToneGrammar) {
		grammarUsageMap = new HashMap<ProductionRule, Integer>();
		initialiseGrammarUsageMap(abstractToneGrammar);
	}
	
	private void initialiseGrammarUsageMap(AbstractTonesGrammar abstractToneGrammar){
		for(ProductionRule prodRule : abstractToneGrammar.getGrammarAsList()){
			grammarUsageMap.put(prodRule, 0);
		}
	}
	
	public void addOneUsageToGrammarMap(ProductionRule prodRule){
		if(grammarUsageMap.containsKey(prodRule)){
			grammarUsageMap.put(prodRule, grammarUsageMap.get(prodRule)+1);
		}
		else{
			throw new IllegalArgumentException("Production rule: '" + prodRule.getRuleRepresentation() + "' " +
											   "does not exist in the grammar usage map, " +
											   "the grammar usage map you are trying to update is different to the " +
											   "grammar you are using now.");
		}
	}
	
	public Map<ProductionRule, Integer> getGrammarUsageMap(){
		return grammarUsageMap;
	}

}

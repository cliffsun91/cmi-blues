package org.cliffsun.individualproject.grammar;

import java.util.ArrayList;
import java.util.List;


public class AbstractTonesGrammarUsedRules {

	private List<List<ProductionRule>> grammarRulesUsed;
	
	public AbstractTonesGrammarUsedRules() {
		grammarRulesUsed = new ArrayList<List<ProductionRule>>();
	}	
	
	public void addRuleToMostRecentPhrase(ProductionRule ruleIndex){
		List<ProductionRule> usedRulesForPhrase = grammarRulesUsed.get(grammarRulesUsed.size()-1);
		usedRulesForPhrase.add(ruleIndex);
	}
	
	public void addEmptyListForNewPhrase(){
		grammarRulesUsed.add(new ArrayList<ProductionRule>());
	}
	
	public List<List<ProductionRule>> getListOfRulesUsedForScore(){
		return grammarRulesUsed;
	}

	public List<List<ProductionRule>> getProdRulesForPhrases(List<Integer> phraseIndexes) {
		List<List<ProductionRule>> neededUsedGrammarRules = new ArrayList<List<ProductionRule>>();
		for (int index: phraseIndexes){
			neededUsedGrammarRules.add(grammarRulesUsed.get(index-1));
		}
		return neededUsedGrammarRules;
	}
}

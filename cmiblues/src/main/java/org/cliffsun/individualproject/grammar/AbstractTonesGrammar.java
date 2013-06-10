package org.cliffsun.individualproject.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractTonesGrammar {

	List<ProductionRule> grammar;
	
	public AbstractTonesGrammar(Map<String, List<ProductionRule>> grammar) {
		this.grammar = convertMapToList(normaliseProbabilitiesHelper(grammar));
	}
	
	public AbstractTonesGrammar(List<ProductionRule> grammar) {
		this.grammar = grammar;
	}
	
	public AbstractTonesGrammar(AbstractTonesGrammar otherGrammar) {
		this.grammar = new ArrayList<ProductionRule>();
		for(ProductionRule rule: otherGrammar.getGrammarAsList()){
			this.grammar.add(new ProductionRule(rule));
		}
	}
	
	public void normaliseProbabilities(){
		this.grammar = convertMapToList(normaliseProbabilitiesHelper(getGrammarAsMap()));
	}

	private Map<String, List<ProductionRule>> normaliseProbabilitiesHelper(Map<String, List<ProductionRule>> grammarMap) {
		for (String key : grammarMap.keySet()) {
			List<ProductionRule> rules = grammarMap.get(key);
			double probabilityAccum = 0;
			for (ProductionRule prodRule : rules) {
				probabilityAccum += prodRule.getProbability();
			}
			for (ProductionRule prodRule : rules) {
				prodRule.setProbability(prodRule.getProbability()
						/ probabilityAccum);
			}
		}
		return grammarMap;
	}
	
	public void addRuleToGrammar(ProductionRule rule){
		grammar.add(rule);
	}
	
	private List<ProductionRule> convertMapToList(Map<String, List<ProductionRule>> grammarMap){
		List<ProductionRule> grammarRules = new ArrayList<ProductionRule>();
		for(String key : grammarMap.keySet()){
			List<ProductionRule> rulesForKey = grammarMap.get(key);
			grammarRules.addAll(rulesForKey);
		}
		return grammarRules;
	}
	
	public Map<String, List<ProductionRule>> getGrammarAsMap(){
		Map<String, List<ProductionRule>> grammarMap = new HashMap<String, List<ProductionRule>>();
		for(ProductionRule prodRule: grammar){
			String var = prodRule.getVar();
			if(grammarMap.containsKey(var)){
				List<ProductionRule> prodRuleList = grammarMap.get(var);
				prodRuleList.add(prodRule);
			}
			else{
				List<ProductionRule> prodRuleList = new ArrayList<ProductionRule>();
				prodRuleList.add(prodRule);
				grammarMap.put(var, prodRuleList);
			}
		}
		return grammarMap;
	}
	
	public List<ProductionRule> getGrammarAsList(){
		return grammar;
	}
	
	public void increaseWeighting(ProductionRule prodRule, double increaseAmount){
		if(grammar.contains(prodRule)){
			ProductionRule rule = grammar.get(grammar.indexOf(prodRule));
			rule.setProbability(rule.getProbability() + increaseAmount);
//			Map<String, List<ProductionRule>> grammarMap = getGrammarAsMap();
//			String var = prodRule.getVar();
//			List<ProductionRule> prodRulesForVar = grammarMap.get(var);
//			double newDecreaseAmount = increaseAmount/(double) (prodRulesForVar.size()-1);
//			for(ProductionRule listRule: prodRulesForVar){
//				ProductionRule grammarRule = grammar.get(grammar.indexOf(listRule));
//				grammarRule.setProbability(grammarRule.getProbability() - newDecreaseAmount);
//			}
		}
		else{
			throw new IllegalArgumentException("Production Rule not recognised: " + prodRule.getRuleRepresentation());
		}
	}
	
	
	public String getRepresentation(){
		String grammarString = "";
		for(ProductionRule prodRule: grammar){
			grammarString += prodRule.getRuleRepresentation() + "\n";
		}
		return grammarString;
	}
}

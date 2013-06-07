package org.cliffsun.individualproject.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractTonesGrammar {

	List<ProductionRule> grammar;
	
	public AbstractTonesGrammar(Map<String, List<ProductionRule>> grammar) {
		this.grammar = convertMapToList(grammar);
	}
	
	public AbstractTonesGrammar(List<ProductionRule> grammar) {
		this.grammar = grammar;
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
}

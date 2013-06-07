package org.cliffsun.individualproject.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AntlrGrammarSentenceGenerator{

	AbstractTonesGrammar abstractToneGrammar;
	
	public AntlrGrammarSentenceGenerator(AbstractTonesGrammar abstractToneGrammar) {
		this.abstractToneGrammar = normaliseProbabilities(abstractToneGrammar);	
		
		System.out.println("abstractToneGrammar = ");
		for(String key : this.abstractToneGrammar.getGrammarAsMap().keySet()){
			System.out.print(key + " = [");
			List<ProductionRule> ruleList = abstractToneGrammar.getGrammarAsMap().get(key);
			for(ProductionRule prodRule : ruleList){
				System.out.print("(" + prodRule.getRuleTokens().toString() + ", " + prodRule.getProbability() + ")");
				if (prodRule != ruleList.get(ruleList.size()-1)){
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
	}
	
	private AbstractTonesGrammar normaliseProbabilities(
								AbstractTonesGrammar absToneGrammar) {
		Map<String, List<ProductionRule>> grammarMap = absToneGrammar.getGrammarAsMap();
		for(String key : grammarMap.keySet()){
			List<ProductionRule> rules = grammarMap.get(key);
			double probabilityAccum = 0;
			for (ProductionRule prodRule : rules){
				probabilityAccum += prodRule.getProbability();
			}
			for (ProductionRule prodRule : rules){
				prodRule.setProbability(prodRule.getProbability()/probabilityAccum);
			}
		}
		return new AbstractTonesGrammar(grammarMap);
	}
	
	public AbstractTonesGrammar getAbstractToneGrammar(){
		return abstractToneGrammar;
	}
	
	public List<String> generate(String root, AbstractTonesGrammarUsedRules grammarUsedRules){
		grammarUsedRules.addEmptyListForNewPhrase(); //whenever we generate we generate a new phrase. So makes sense.
		List<String> list = generateHelper(root, grammarUsedRules);
		return list;
	}
	
	public List<String> generateHelper(String root, AbstractTonesGrammarUsedRules grammarUsedRules) {
		List<String> terminalSequence = new ArrayList<String>();
		Map<String, List<ProductionRule>> grammarMap = abstractToneGrammar.getGrammarAsMap();
		if(grammarMap.containsKey(root)){
			double probabilityAcc = 0;
			List<ProductionRule> ruleList = grammarMap.get(root);
			double rand = Math.random();
			for(ProductionRule prodRule : ruleList){
				probabilityAcc += prodRule.getProbability();
				if(rand < probabilityAcc){
					//store the production rule in the grammarUsedRules
					grammarUsedRules.addRuleToMostRecentPhrase(prodRule);
					List<String> ruleTokens = prodRule.getRuleTokens();
					for(String token : ruleTokens){
						if(!grammarMap.containsKey(token)){
							terminalSequence.add(token);
						}
						else{
							terminalSequence.addAll(generateHelper(token, grammarUsedRules));
						}
					}
					break;
				}
			}
		}
		return terminalSequence;
	}

}

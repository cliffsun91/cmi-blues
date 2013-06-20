package org.cliffsun.individualproject.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cliffsun.individualproject.namer.Namer;

public class AntlrGrammarSentenceGenerator{

	AbstractTonesGrammar abstractToneGrammar;
	
	public AntlrGrammarSentenceGenerator(AbstractTonesGrammar abstractToneGrammar) {
		this.abstractToneGrammar = abstractToneGrammar;	
		
//		System.out.println("abstractToneGrammar = ");
//		for(String key : this.abstractToneGrammar.getGrammarAsMap().keySet()){
//			System.out.print(key + " = [");
//			List<ProductionRule> ruleList = abstractToneGrammar.getGrammarAsMap().get(key);
//			for(ProductionRule prodRule : ruleList){
//				System.out.print("(" + prodRule.getRuleTokens().toString() + ", " + prodRule.getProbability() + ")");
//				if (prodRule != ruleList.get(ruleList.size()-1)){
//					System.out.print(", ");
//				}
//			}
//			System.out.println("]");
//		}
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
			double prevProbabilityAcc = 0;
			double probabilityAcc = 0;
			List<ProductionRule> ruleList = grammarMap.get(root);
			double rand = Math.random();
			for(ProductionRule prodRule : ruleList){
				prevProbabilityAcc = probabilityAcc;;
				probabilityAcc += prodRule.getProbability();
				if(rand >= prevProbabilityAcc && rand < probabilityAcc){
					Namer.addToZerosAndOnesList(true);
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
				}
				else{
					Namer.addToZerosAndOnesList(false);
				}
			}
		}
		return terminalSequence;
	}

}

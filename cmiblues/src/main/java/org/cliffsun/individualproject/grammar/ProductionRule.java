package org.cliffsun.individualproject.grammar;

import java.util.List;

import org.cliffsun.individualproject.utils.Pair;

public class ProductionRule {
	

	private String var;
	private List<String> ruleTokens;
	private Double probability;
	
	public ProductionRule(String var, Pair<List<String>, Double> ruleAndProbability){
		this(var, ruleAndProbability.getLeft(), ruleAndProbability.getRight());
	}
	
	public ProductionRule(String var, List<String> ruleTokens, double probability){
		this.var = var;
		this.ruleTokens = ruleTokens;
		this.probability = probability;
	}

	public String getVar(){
		return var;
	}
	
	public List<String> getRuleTokens(){
		return ruleTokens;
	}
	
	public double getProbability(){
		return probability;
	}
	
	public Pair<List<String>, Double> getProductionRuleAsPair(){
		return new Pair<List<String>, Double>(ruleTokens, probability);
	}
	
	public void setProbability(double newProb){
		this.probability = newProb;
	}
	
	public String getRuleRepresentation(){
		return var + " -> " + getRuleTokensRepresentation() + " " + getProbabilityRepresentation();
	}
	
	private String getRuleTokensRepresentation() {
		if (ruleTokens.size() == 1){
			return ruleTokens.get(0);
		}
		else if (ruleTokens.size() > 1){
			String tokens = "( ";
			for(String token : ruleTokens){
				tokens += token + " ";
			}
			tokens += ")";
			return tokens;
		}
		else{
			return ""; //should make it throw an exception here
		}
	}
	
	private String getProbabilityRepresentation() {
		return "[" + probability.toString() + "]";
	}

	//hashCode() and equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((probability == null) ? 0 : probability.hashCode());
		result = prime * result
				+ ((ruleTokens == null) ? 0 : ruleTokens.hashCode());
		result = prime * result + ((var == null) ? 0 : var.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductionRule other = (ProductionRule) obj;
		if (probability == null) {
			if (other.probability != null)
				return false;
		} else if (!probability.equals(other.probability))
			return false;
		if (ruleTokens == null) {
			if (other.ruleTokens != null)
				return false;
		} else if (!ruleTokens.equals(other.ruleTokens))
			return false;
		if (var == null) {
			if (other.var != null)
				return false;
		} else if (!var.equals(other.var))
			return false;
		return true;
	}

}

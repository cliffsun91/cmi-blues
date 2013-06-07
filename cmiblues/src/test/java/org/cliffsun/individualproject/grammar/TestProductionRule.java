package org.cliffsun.individualproject.grammar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestProductionRule {

	@Test
	public void testThatProductionRuleWithOneTokenReturnsCorrectly(){
		String var = "var";
		List<String> token = Arrays.asList("tok");
		double probability = 0.9;
		ProductionRule prodRule = new ProductionRule(var, token, probability);
		assertThat(prodRule.getRuleRepresentation(), equalTo("var -> tok [0.9]"));
	}
	
	@Test
	public void testThatProductionRuleWithMoreThanOneTokenReturnsCorrectly(){
		String var = "var";
		List<String> tokens = Arrays.asList("tok1", "tok2", "tok3");
		double probability = 0.7;
		ProductionRule prodRule = new ProductionRule(var, tokens, probability);
		assertThat(prodRule.getRuleRepresentation(), equalTo("var -> ( tok1 tok2 tok3 ) [0.7]"));
	}
}

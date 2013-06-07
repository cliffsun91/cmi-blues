package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.grammar.AbstractTonesGrammar;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.score.CombinedScoreLine;

public class FullMusicScore {
	
	private AbstractTonesGrammar abstractTonesGrammar;
	private AbstractTonesGrammarUsedRules grammarUsesRules;
	private CombinedScoreLine scoreLine;
	private ABCFullScoreRepresentation abcRepr;
	
	public FullMusicScore(AbstractTonesGrammar abstractTonesGrammar, AbstractTonesGrammarUsedRules grammarUsesRules, CombinedScoreLine scoreLine, ABCFullScoreRepresentation abcRepr) {
		this.abstractTonesGrammar = abstractTonesGrammar;
		this.grammarUsesRules = grammarUsesRules;
		this.scoreLine = scoreLine;
		this.abcRepr = abcRepr;
	}
	
	public ABCFullScoreRepresentation getABCFullScoreRepr(){
		return abcRepr;
	}
	
	public AbstractTonesGrammarUsedRules getGrammarUsedRules(){
		return grammarUsesRules;
	}
}

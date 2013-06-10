package org.cliffsun.individualproject.cmiblues;

import org.cliffsun.individualproject.accompaniment.BassAccompaniment;
import org.cliffsun.individualproject.accompaniment.BassProgressionParser;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.grammar.AntlrGrammarSentenceGenerator;
import org.cliffsun.individualproject.melody.MelodyGenerator;
import org.cliffsun.individualproject.score.BassClefScoreLine;
import org.cliffsun.individualproject.score.CombinedScoreLine;
import org.cliffsun.individualproject.score.TrebleClefScoreLine;

public class MusicGenerator {
	
	private AntlrGrammarSentenceGenerator sentenceGenerator;
	private String progressionFilePath;

	public MusicGenerator(AntlrGrammarSentenceGenerator sentenceGenerator, String progressionFilePath) {
		this.sentenceGenerator = sentenceGenerator;
		this.progressionFilePath = progressionFilePath;
	}
	
	public FullMusicScore generateFullMusicScore() throws Exception{
		Namer.clearList();
		
		AbstractTonesGrammarUsedRules grammarUsedRules = new AbstractTonesGrammarUsedRules();
		
        BassProgressionParser bassProgParser = new BassProgressionParser();
        BassAccompaniment accomp = bassProgParser.parseBassProgressionFile(progressionFilePath);
        MelodyGenerator melody = new MelodyGenerator(accomp, sentenceGenerator, grammarUsedRules);
        
        TrebleClefScoreLine trebleScore = melody.getScoreLine();
        BassClefScoreLine bassScore = accomp.getScoreLine();
        
        CombinedScoreLine combinedScore = new CombinedScoreLine(trebleScore, bassScore);
        
        long pieceId = Namer.getBinaryToNumberTitle();
        String title = "Comp. " + pieceId;
        ABCFullScoreRepresentation abcRepr = new ABCFullScoreRepresentation(combinedScore, title);
        
        
        FullMusicScore fullMusicScore = new FullMusicScore(sentenceGenerator.getAbstractToneGrammar(), 
        												   grammarUsedRules,
        												   combinedScore, 
        												   abcRepr);
        return fullMusicScore;
	}
}

package org.cliffsun.individualproject.cmiblues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

import org.cliffsun.individualproject.bar.Bar;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammar;
import org.cliffsun.individualproject.grammar.AbstractTonesGrammarUsedRules;
import org.cliffsun.individualproject.grammar.AntlrGrammarSentenceGenerator;
import org.cliffsun.individualproject.grammar.ProductionRule;
import org.cliffsun.individualproject.phrase.Phrase;

import abc.midi.BasicMidiConverter;
import abc.midi.MidiConverterAbstract;
import abc.midi.TunePlayer;
import abc.notation.Tune;
import abc.parser.TuneBook;
import abc.parser.TuneParser;
import abc.ui.swing.JScoreComponent;

public class GrammarUpdate {

	private String progressionFilePath;
	private String grammarFilePath;

	public GrammarUpdate(String grammarFilePath, String progressionFilePath) {
		this.grammarFilePath = grammarFilePath;
		this.progressionFilePath = progressionFilePath;
	}
	
	public void runUpdate(int noToSample) throws Exception{
        AntlrGrammarParser abstractToneParser = new AntlrGrammarParser();
        AbstractTonesGrammar grammar = abstractToneParser.parseAbstractToneGrammar(grammarFilePath);
        AntlrGrammarSentenceGenerator sentenceGenerator = new AntlrGrammarSentenceGenerator(grammar);

        GrammarRuleUsage grammarRuleUsage = new GrammarRuleUsage(grammar);
        
        MusicGenerator generator = new MusicGenerator(sentenceGenerator, progressionFilePath);
        
        System.out.println("I'm now about to show you n generated pieces (where n is the number that is\n " +
        				   "specified as the second argument to the program),\n" +
        				   "I would like you specify after each piece which bars sound pleasing to you\n " +
        				   "using your judgment. Please write out the bar numbers using spaces to seperate them");
        
		for (int i = 0; i < noToSample; i++){
			FullMusicScore fullMusicScore = generator.generateFullMusicScore();
			ABCFullScoreRepresentation abcRepr = fullMusicScore.getABCFullScoreRepr();
			 
			//InputStream is = new ByteArrayInputStream(abcRepr.getFullScoreAsString().getBytes());
			//BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
			//TuneBook tuneBook = new TuneBook();
			//tuneBook.putTune(abcRepr.getFullScoreAsString());
			//play and display the music
			//ask user which bars sound pleasant
			
			//Tune tune = new TuneParser().parse(abcRepr.getFullScoreAsString()); //retrieve the tune to display from the tunebook using its reference number.
			String score = "X:1\nT:JazzImprov\nC:CMIJazz-1.0\nQ:1/4=100\nL:1/4\nM:C\nK:C\n"+
											   "V:1\nV:2\n"+
											   //"A/2A/2F3/2A/2z/2B/2 |=D/4^D/4=D/2d/2d/2Bz/2z/2 |FG2/3B2/3E2/3F |AA/2B/4F/4EG |G/2F/2a/2a/2a/2g/2z |A/2B/2cAA/2B/2 |D/4_E/4=E/2z/2Fc/2^G/2B/2 |b/3e/3e/3_g/3=g/3f/3=gb |df/2f/2cf |=g/2=gb/4^g/4=g/2a/2b |d/2g/2f_b/2f/2_b |e3/2^c/2^c/2A/2^c/2e/2 |c/2c/2cc/2e/2f |b/2f/2d/2z/2e/2g/2d/4e/4f/4e/4 |a/2b/2zb/2a/2b/2b/2 |_a3/2=a/2 c'/3^g/3f/3^g/2f/2 ||\n");
											   "[V:1]A/2A/2F3/2A/2z/2B/2 |=D/4^D/4=D/2d/2d/2Bz/2z/2 |FG2/3B2/3E2/3F |AA/2B/4F/4EG |G/2F/2a/2a/2a/2g/2z |A/2B/2cAA/2B/2 |D/4_E/4=E/2z/2Fc/2^G/2B/2 |b/3e/3e/3_g/3=g/3f/3=gb |df/2f/2cf |=g/2=gb/4^g/4=g/2a/2b |d/2g/2f_b/2f/2_b |e3/2^c/2^c/2A/2^c/2e/2 |c/2c/2cc/2e/2f |b/2f/2d/2z/2e/2g/2d/4e/4f/4e/4 |a/2b/2zb/2a/2b/2b/2 |_a3/2=a/2 c'/3^g/3f/3^g/2f/2 ||\n" +
											   "[V:2][A,C,G,]2z2 |[D,F,C]2z2 |[G,B,F,]2z2 |[C,E,B,]2z2 |[F,A,E,]2z2 |[B,D,F,A,]2z2 |[E,^G,D]2z2 |[A,C,G,]2z2 |[D,F,C]2z2 |[G,B,F,]2z2 |[E,G,_B,D]2z2 |[A,^C,G,]2z2 |[D,F,C]2z2 |[G,B,F,]2z2 |[C,E,B,]2z2 |[B,D,F,A,]2[E,^G,D]2 ||";

//			Tune tune = new TuneParser().parse(score);
//			Tune tune1 = new TuneParser().parse("X:10\nT:Simple scale exercise\nL:1/4\nM:4/4\nC:noOne\nK:C\n" +
//											   "|| C,D,E,F, | G,A,B,C | DEFG | ABcd | efga | bc'd'e' | f'g'a'b' ||");
			
//			JScoreComponent jscore = new JScoreComponent();
//			jscore.setJustification(true);
//			jscore.setTune(tune);
//			JFrame j = new JFrame();
//			j.add(jscore);
//			j.pack();
//			j.setVisible(true);
			
//			TunePlayer player = new TunePlayer();
//			//starts the player and play the tune
//			player.start();
//			player.play(tune);
			
//			Synthesizer synth = MidiSystem.getSynthesizer();
//			synth.open();
//			//The midi file result
//			File file = new File("test.mid");
//			//Create a converter to convert a tune into midi sequence
//			MidiConverterAbstract conv = new BasicMidiConverter();
//			//convert it !
//			Sequence s = conv.toMidiSequence(tune);
//			//All available midi file type for the tune's sequence
//			int[] types = MidiSystem.getMidiFileTypes(s);
//			//Write the sequence as a midi file.
//			MidiSystem.write(s,types[0],file);
//			synth.close();

			
			System.out.println("Copy the following score to MuseScore or Ernie \n" +
							   "and listen to the music whilst keeping an eye on the bars: \n\n" 
							   + fullMusicScore.getABCFullScoreRepr().getFullScoreAsString());
			
			boolean invalidInput = true;
			List<Integer> selectedBars = new ArrayList<Integer>();
	        while(invalidInput){
	        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Enter bar numbers of bars that sound pleasing (seperate by comma's)");
		        String line = br.readLine();
		        line = line.replaceAll("\\s","");
		        String[] barStrings = line.split(",");
	        	try{
	        		selectedBars = parseInputBarStrings(barStrings);
	        		invalidInput = false;
	        	}catch(NumberFormatException nfe){
	        		System.out.println("Invalid Input, Please Re-Enter.");
	        		invalidInput = true;
	        	}
	        }
	        
	        //use selected bars to update grammar
			AbstractTonesGrammarUsedRules grammarUsedRules = fullMusicScore.getGrammarUsedRules();
			List<Bar> bars = fullMusicScore.getBarsFromTrebleClef();
			int barIndex = 0;
			int phraseIndex = 0;
			List<Integer> phraseIndexes = new ArrayList<Integer>();
			for(Bar bar : bars){
				barIndex++;
				List<Phrase> phrases = bar.getPhrases();
				for(Phrase phrase : phrases){
					phraseIndex += 1;
					if(selectedBars.contains(barIndex)){
						phraseIndexes.add(phraseIndex);
					}
				}
			}
			List<List<ProductionRule>> selectedUsedRules = grammarUsedRules.getProdRulesForPhrases(phraseIndexes);
			List<ProductionRule> flattenedUsedRulesList = new ArrayList<ProductionRule>();
			for(List<ProductionRule> ruleList : selectedUsedRules){
				flattenedUsedRulesList.addAll(ruleList);
			}
			grammarRuleUsage.addMultipleUsageToGrammarMap(flattenedUsedRulesList);
		}
		Map<ProductionRule, Integer> usageMap = grammarRuleUsage.getGrammarUsageMap();
		AbstractTonesGrammar newGrammar = new AbstractTonesGrammar(grammar);
		
		System.out.println("usageMap: " + usageMap);
		for(ProductionRule rule: usageMap.keySet()){
			
			System.out.println("rule: " + rule.getRuleRepresentation());
			if(usageMap.get(rule) == null){
				throw new Exception("fuck this!");
			}
			if(usageMap.get(rule) > 0){
				System.out.println("rule to be updated: " + rule.getRuleRepresentation());
				newGrammar.increaseWeighting(rule, 0.005); //increase all of the them
			}
		}
		
		newGrammar.normaliseProbabilities();
		
		if(writeUpdatedGrammarToFile(newGrammar)){
			System.out.println("Grammar written to filepath '" + grammarFilePath + "' was successful!");
		}
		else{
			System.out.println("Could not write to filepath: '" + grammarFilePath + 
							   "', check that the file is not being accessed by another program");
		}
	}
	
	private boolean writeUpdatedGrammarToFile(AbstractTonesGrammar grammar) throws Exception {
		try {
			File file = new File(grammarFilePath);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				throw new Exception("File should exist as grammar file already!");
			}
 
			if(file.canWrite()){
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(grammar.getRepresentation());
				bw.close();
				return true;
			}
			return false;
		} catch (IOException e) {
			System.err.println("Could not write abc tune to file, exception:");
			e.printStackTrace();
		}
		return false;
	}

	public List<Integer> parseInputBarStrings(String[] barStrings) throws NumberFormatException{
        List<Integer> selectedBars = new ArrayList<Integer>();
        System.out.print("You've select bars: ");
        for(String barString : barStrings){
        	try{
        		int bar = Integer.parseInt(barString);
        		selectedBars.add(bar);
        		System.out.print(barString + " ");
        	}catch(NumberFormatException nfe){
        		System.out.println("Invalid Format: " + barString);
        		throw nfe;
        	}
        }
        System.out.println();
        return selectedBars;
	}
}

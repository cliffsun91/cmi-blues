package org.cliffsun.individualproject.grammar.terminal;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.terminal.durationparser.DurationParser;
import org.cliffsun.individualproject.grammar.terminal.toneparser.SimpleBluesJazzToneTypeParser;
import org.cliffsun.individualproject.grammar.terminal.toneparser.ToneTypeParser;
import org.cliffsun.individualproject.grammar.terminal.tonetonote.LookAheadToChordTonesToneToNoteConverter;
import org.cliffsun.individualproject.grammar.terminal.tonetonote.SimpleLookOneBehindToneToNoteConverter;
import org.cliffsun.individualproject.grammar.terminal.tonetonote.ToneToNoteConverter;
import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.keys.CMajorSeventhScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.phrase.Phrase;
import org.cliffsun.individualproject.utils.Pair;

public class TerminalParser {
	
	Scale accompScale;
	
	public TerminalParser(Scale accompScale) {
		this.accompScale = accompScale;
	}
	
	public Phrase convertSentenceToPhrase(List<String> terminalSequenceList) throws Exception{
		
		ArrayList<Tone> toneList = new ArrayList<Tone>();
		ArrayList<Duration> durationList = new ArrayList<Duration>();
		Tone prevTone = null;
		
		for (String terminal : terminalSequenceList){
			//split up the string into the tone class and the duration and put into seperate lists
			String toneString = terminal.substring(0,1);
			//System.out.println("c is " + c);
			//Scale cMajorSeventhScale = new CMajorSeventhScale();
			
			ToneTypeParser toneParser = new SimpleBluesJazzToneTypeParser();
			Tone toneType = toneParser.parseToneAndReturnAppropriateType(toneString, accompScale, prevTone);
			toneList.add(toneType);
			prevTone = toneType;
			
			String durationString = terminal.substring(1);
			DurationParser durationParser = new DurationParser();
			Duration duration = durationParser.getDurationFromString(durationString);
			durationList.add(duration);
			//System.out.println("duration is " + duration.getActualDuration());
		}
		
		//change the converter here
		//ToneToNoteConverter converter = new SimpleLookOneBehindToneToNoteConverter();
		ToneToNoteConverter converter = new LookAheadToChordTonesToneToNoteConverter();
		return converter.generatePhrase(toneList, durationList);
	}
}

package org.cliffsun.individualproject.grammar.terminal;

import java.util.ArrayList;
import java.util.List;

import org.cliffsun.individualproject.duration.Duration;
import org.cliffsun.individualproject.grammar.terminal.durationparser.DurationParser;
import org.cliffsun.individualproject.grammar.terminal.toneparser.SimpleBluesToneTypeParser;
import org.cliffsun.individualproject.grammar.terminal.toneparser.ToneTypeParser;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.keys.CMinorBluesScale;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.note.BasicNote;
import org.cliffsun.individualproject.note.MainNoteComponent;
import org.cliffsun.individualproject.note.TimedComponent;
import org.cliffsun.individualproject.phrase.Phrase;

public class TerminalParser {
	
	public List<Phrase> convertSentenceToListOfTerminalTonesAndDurations(List<String> terminalSequenceList){
		
		ArrayList<Tone> toneList = new ArrayList<Tone>();
		ArrayList<Duration> durationList = new ArrayList<Duration>();
		
		for (String terminal : terminalSequenceList){
			//split up the string into the tone class and the duration and put into seperate lists
			String c = terminal.substring(0,1);
			//System.out.println("c is " + c);
			Scale minorBluesScale = new CMinorBluesScale();
			
			ToneTypeParser toneParser = new SimpleBluesToneTypeParser();
			Tone toneType = toneParser.parseToneAndReturnAppropriateType(c, minorBluesScale);
			toneList.add(toneType);
			
			String durationString = terminal.substring(1);
			DurationParser durationParser = new DurationParser();
			Duration duration = durationParser.getDurationFromString(durationString);
			durationList.add(duration);
			
			//System.out.println("duration is " + duration.getActualDuration());
		}
		
		ToneToNoteConverter converter = new ToneToNoteConverter();
		return converter.generateTimedComponentList(toneList, durationList);
	}
}
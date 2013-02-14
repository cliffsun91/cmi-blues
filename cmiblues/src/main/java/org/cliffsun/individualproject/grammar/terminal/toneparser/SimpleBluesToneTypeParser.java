package org.cliffsun.individualproject.grammar.terminal.toneparser;

import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.grammar.toneclass.ColourTone;
import org.cliffsun.individualproject.grammar.toneclass.HelpfulTone;
import org.cliffsun.individualproject.grammar.toneclass.RestTone;
import org.cliffsun.individualproject.grammar.toneclass.ScaleTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.keys.Scale;

public class SimpleBluesToneTypeParser implements ToneTypeParser {

	@Override
	public Tone parseToneAndReturnAppropriateType(String toneRepr, Scale scale) {
		if(toneRepr.equals("C")){
			return new ChordTone(scale);
		}
		else if(toneRepr.equals("L")) {
			return new ColourTone(scale);
		}
		else if(toneRepr.equals("A")){
			return new ApproachTone(scale);
		}
		else if(toneRepr.equals("S")){
			return new ScaleTone(scale);
		}
		else if(toneRepr.equals("H")){
			return new HelpfulTone(scale);
		}
		else if(toneRepr.equals("R")){
			return new RestTone();
		}
		else{
			throw new IllegalArgumentException("Blues Tone Parser could not parse the string: '" + 
												toneRepr + "', the string must represent a valid tone type!");
		}
	}

}
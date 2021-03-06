package org.cliffsun.individualproject.grammar.terminal.toneparser;

import org.cliffsun.individualproject.grammar.toneclass.ApproachTone;
import org.cliffsun.individualproject.grammar.toneclass.ChordTone;
import org.cliffsun.individualproject.grammar.toneclass.ColourTone;
import org.cliffsun.individualproject.grammar.toneclass.RestTone;
import org.cliffsun.individualproject.grammar.toneclass.ScaleTone;
import org.cliffsun.individualproject.grammar.toneclass.Tone;
import org.cliffsun.individualproject.keys.Scale;
import org.cliffsun.individualproject.namer.Namer;

public class SimpleBluesJazzToneTypeParser implements ToneTypeParser {

	@Override
	public Tone parseToneAndReturnAppropriateType(String toneRepr, Scale scale, Tone prevTone) {
		if(prevTone instanceof ApproachTone){
			return new ChordTone(scale); //if previous tone is approach then we force the next to be a chord tone,
										 //no matter its current designation
		}
		else{
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
				double rand = Math.random();
				if (rand < 0.4) {
					Namer.addMultipleToZerosAndOnesList(true, true);
					return new ChordTone(scale);
				}
				else if (rand < 0.8) {
					Namer.addMultipleToZerosAndOnesList(true, false);
					return new ColourTone(scale);
				}
				else { //0.2 probability
					Namer.addMultipleToZerosAndOnesList(false, false);
					return new ApproachTone(scale);
				}
			}
			else if(toneRepr.equals("R")){
				return new RestTone(scale);
			}
			else{
				throw new IllegalArgumentException("Blues Tone Parser could not parse the string: '" + 
													toneRepr + "', the string must represent a valid tone type!");
			}
		}
	}

}

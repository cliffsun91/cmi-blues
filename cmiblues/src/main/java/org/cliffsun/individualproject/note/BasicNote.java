package org.cliffsun.individualproject.note;

import java.util.Arrays;
import java.util.List;

public class BasicNote {

	private SimpleNoteEnum note;
	private AccidentalShift accidentalShift;
	
	public BasicNote(SimpleNoteEnum note){
		this.note = note;
		this.accidentalShift = AccidentalShift.Natural;
	}
	
	public BasicNote(SimpleNoteEnum note, AccidentalShift accidentalShift) {
		this.note = note;
		this.accidentalShift = accidentalShift;
	}
	
	public SimpleNoteEnum getNoteEnum(){
		return note;
	}
	
	public AccidentalShift getAccidentalShift(){
		return accidentalShift;
	}
	
	public boolean isLowerThan(BasicNote note){
		return this.getIntegerValueForNote() < note.getIntegerValueForNote();
	}
	
	public boolean isMusicallyEquivalent(BasicNote note){
		return this.getIntegerValueForNote() == note.getIntegerValueForNote();
	}
	
	public int getAbsInterval(BasicNote note){
		return Math.abs(this.getIntegerValueForNote() - note.getIntegerValueForNote());
	}
	
	public int getIntegerValueForNote(){
		return note.getNoteNumberRepresentation() + accidentalShift.getShiftValue();
	}
	
//	@Override
//	public boolean equals(Object obj){
//		if (!(obj instanceof BasicNoteWithAccidental))
//            return false;
//		BasicNoteWithAccidental note = (BasicNoteWithAccidental) obj;
//		return note.getIntegerValueForNote() == this.getIntegerValueForNote();
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accidentalShift == null) ? 0 : accidentalShift.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
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
		BasicNote other = (BasicNote) obj;
		if (accidentalShift != other.accidentalShift)
			return false;
		if (note != other.note)
			return false;
		return true;
	}

	public static BasicNote defaultNote(){
		return cNatural(); //for now just C natural
	}
	
	public static BasicNote rest(){
		return new BasicNote(SimpleNoteEnum.REST);
	}
	
	public static BasicNote cNatural(){
		return new BasicNote(SimpleNoteEnum.C);
	}
	public static BasicNote cSharp(){
		return new BasicNote(SimpleNoteEnum.C, AccidentalShift.Sharp);
	}
	
	public static BasicNote dFlat(){
		return new BasicNote(SimpleNoteEnum.D, AccidentalShift.Flat);
	}
	public static BasicNote dNatural(){
		return new BasicNote(SimpleNoteEnum.D);
	}
	public static BasicNote dSharp(){
		return new BasicNote(SimpleNoteEnum.D, AccidentalShift.Sharp);
	}
	
	public static BasicNote eFlat(){
		return new BasicNote(SimpleNoteEnum.E, AccidentalShift.Flat);
	}
	public static BasicNote eNatural(){
		return new BasicNote(SimpleNoteEnum.E);
	}
	
	public static BasicNote fNatural(){
		return new BasicNote(SimpleNoteEnum.F);
	}
	public static BasicNote fSharp(){
		return new BasicNote(SimpleNoteEnum.F, AccidentalShift.Sharp);
	}
	
	public static BasicNote gFlat(){
		return new BasicNote(SimpleNoteEnum.G, AccidentalShift.Flat);
	}
	public static BasicNote gNatural(){
		return new BasicNote(SimpleNoteEnum.G);
	}
	public static BasicNote gSharp(){
		return new BasicNote(SimpleNoteEnum.G, AccidentalShift.Sharp);
	}
	
	public static BasicNote aFlat(){
		return new BasicNote(SimpleNoteEnum.A, AccidentalShift.Flat);
	}
	public static BasicNote aNatural(){
		return new BasicNote(SimpleNoteEnum.A);
	}
	public static BasicNote aSharp(){
		return new BasicNote(SimpleNoteEnum.A, AccidentalShift.Sharp);
	}
	
	public static BasicNote bFlat(){
		return new BasicNote(SimpleNoteEnum.B, AccidentalShift.Flat);
	}
	public static BasicNote bNatural(){
		return new BasicNote(SimpleNoteEnum.B);
	}
	
	public static List<BasicNote> getChromaticNoteList(){
		return Arrays.asList(cNatural(),
							 cSharp(),
							 dFlat(),
							 dNatural(),
							 dSharp(),
							 eFlat(),
							 eNatural(),
							 fNatural(),
							 fSharp(),
							 gFlat(),
							 gNatural(),
							 gSharp(),
							 aFlat(),
							 aNatural(),
							 aSharp(),
							 bFlat(),
							 bNatural());
	}
	
}

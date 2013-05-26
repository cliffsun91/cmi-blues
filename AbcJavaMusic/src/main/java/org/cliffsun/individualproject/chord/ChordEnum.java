package org.cliffsun.individualproject.chord;


public enum ChordEnum {

	cmaj7(new CMajorSeventhChord()),
	fmaj7(new FMajorSeventhChord()),
	gmaj7(new GMajorSeventhChord()),
	dmin7(new DMinorSeventhChord());
	
	private Chord chord;
	
	ChordEnum(Chord chord) {
		this.chord = chord;
	}
	
	public Chord getChord(){
		return chord;
	}
	
    public static ChordEnum getChordEnum(String value){
        for(ChordEnum v : values()) {
            if(value.equals(v.name())) {
            	return v;
            }
        }
        throw new IllegalArgumentException("No such chord name: " + value);
    }
}

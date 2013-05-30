package org.cliffsun.individualproject.chord;


public enum ChordEnum {

	cmaj7("cmaj7", new CMajorSeventhChord()),
	cdom7("c7", new CDominantSeventhChord()),
	cdom7add9("c7add9", new CDominantSeventhAddNineChord()),
	dmin7("dmin7", new DMinorSeventhChord()),
	edom7("e7", new EDominantSeventhChord()),
	emin7addb5("emin7b5", new EMinorSeventhAddFlatFiveChord()),
	fdmaj7("fmaj7", new FMajorSeventhChord()),
	fdom7("f7", new FDominantSeventhChord()),
	gmaj7("g7", new GDominantSeventhChord()),
	adom7("a7", new ADominantSeventhChord()),
	amin7("amin7", new AMinorSeventhChord()),
	bmin7addb5("bmin7b5", new BMinorSeventhAddFlatFiveChord());
	
	private String repr;
	private Chord chord;
	
	ChordEnum(String repr, Chord chord) {
		this.chord = chord;
		this.repr = repr;
	}
	
	public Chord getChord(){
		return chord;
	}
	
	public String getRepr(){
		return repr;
	}
	
    public static ChordEnum getChordEnum(String value){
        for(ChordEnum v : values()) {
            if(value.equalsIgnoreCase(v.getRepr())) {
            	return v;
            }
        }
        throw new IllegalArgumentException("No such chord name: " + value);
    }
}

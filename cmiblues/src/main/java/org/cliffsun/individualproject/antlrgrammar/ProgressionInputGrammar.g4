/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

grammar ProgressionInputGrammar;

//@header {

//}

//@members {
//  List<String> values = new ArrayList<String>();
//}

//prog: HEADER line EOF;
prog returns [List<String> progression]
	: HEADER l=line {$progression = $l.chords;} EOF;
line returns [List<String> chords]
@init{$chords = new ArrayList<String>();}
	: (DELIM e=CHORDNAME {$chords.add($e.text);})+ END;
//line: (DELIM e=CHORDNAME {values.add($e.text);})+ END;
DELIM: '|';
END: '||';
HEADER: 'progression:';
CHORDNAME: ('a'..'z'|'A'..'Z'|'0'..'9')+;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
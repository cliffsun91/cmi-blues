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
prog returns [List<List<String>> progression]
	: HEADER l=line {$progression = $l.progLine;} EOF;
	
line returns [List<List<String>> progLine]
@init{$progLine = new ArrayList<List<String>>();}
	: (DELIM c=chords {$progLine.add($c.chordsInBar);})+ END;
//line: (DELIM e=CHORDNAME {values.add($e.text);})+ END;

chords returns [List<String> chordsInBar]
@init{$chordsInBar = new ArrayList<String>();}
	: e=CHORDNAME {$chordsInBar.add($e.text);} | 
	  e=CHORDNAME {$chordsInBar.add($e.text);} ',' e=CHORDNAME {$chordsInBar.add($e.text);};
	
DELIM: '|';
END: '||';
HEADER: 'progression:';
CHORDNAME: ('a'..'z'|'A'..'Z'|'0'..'9')+;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

grammar AbstractToneGrammar;

@header {
import org.cliffsun.individualproject.utils.Pair;
import org.cliffsun.individualproject.grammar.ProductionRule;
import java.util.Map;
import java.util.HashMap;
}

/*
grmmr returns [List<Map<String,Pair<List<String>, String>>> grammarList]
@init{
$grammarList = new ArrayList<Map<String,Pair<List<String>, String>>>();
}
: (l=line {$grammarList.add($l.productionRule);})+ EOF;
//grmmr: (line)+ EOF;

line returns [Map<String, Pair<List<String>, String>> productionRule]
@init{
$productionRule = new HashMap<String, Pair<List<String>, String>>();
}
: key=VAR RULEARROW r=prodRule {$productionRule.put($key.text, $r.pair);};
*/

grmmr returns [Map<String, List<ProductionRule>> grammarList]
@init{
$grammarList = new HashMap<String, List<ProductionRule>>();
}
: (key=VAR RULEARROW r=prodRule {
    if($grammarList.containsKey($key.text)){
        List<ProductionRule> singleRule = $grammarList.get($key.text);
        singleRule.add(new ProductionRule($key.text, $r.pair));
        $grammarList.put($key.text, singleRule);
    }
    else{
        List<ProductionRule> newSingleRule = new ArrayList<ProductionRule>();
        newSingleRule.add(new ProductionRule($key.text, $r.pair));
        $grammarList.put($key.text, newSingleRule);
    }
  })+ EOF;
  
prodRule returns [Pair<List<String>, Double> pair]
@init{
$pair = new Pair<List<String>,Double>();
}
: t=vars {$pair.setLeft($t.toks);} OPENSQUAREBRACKET p=PROBABILITY {$pair.setRight(Double.parseDouble($p.text));} CLOSESQUAREBRACKET;

vars returns [List<String> toks]
@init{
$toks = new ArrayList<String>();
}
: OPENBRACKET (v=VAR {$toks.add($v.text);})+ CLOSEBRACKET | v=VAR {$toks.add($v.text);};

fragment ALPHA: ('a'..'z' | 'A'..'Z');
fragment NUMBER: '0'..'9';

VAR: (ALPHA)+ (NUMBER)+ | (ALPHA)+ (NUMBER)* '/' (NUMBER)+;
RULEARROW: '->';
OPENBRACKET: '(';
CLOSEBRACKET: ')';
OPENSQUAREBRACKET: '[';
CLOSESQUAREBRACKET: ']';
PROBABILITY: (NUMBER)+ '.' (NUMBER)+ | (NUMBER);
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
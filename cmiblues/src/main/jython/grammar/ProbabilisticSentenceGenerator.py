'''
Created on Jan 17, 2013

@author: cliffsun91
'''
import random
from grammar.GrammarWeightingNormaliser import GrammarWeightingNormaliser


class ProbabilisticSentenceGenerator():

    def __init__(self, grammarDict = {}):
        self.grammar = GrammarWeightingNormaliser(grammarDict).normaliseProbabilities()

    def generate(self, phrase):
        "Generate a sentence or phrase"
        if isinstance(phrase, list): 
            return self.mappend(self.generate, phrase)
        elif phrase in self.grammar:
            return self.generate(self.choose(self.grammar[phrase]))
        else: return [phrase]
        
    def choose(self, rhsList):
        if len(rhsList) > 1:
            rand = random.random()
            #print rand, ", "
            acc = 0.0;
            for (word, prob) in rhsList:
                acc = acc + prob
                if (rand < acc):
                    return word
        else:
            (word, _) = rhsList[0]
            return word
        
    def generateTree(self, phrase):
        """Generate a sentence or phrase,
         with a complete parse tree."""
        if isinstance(phrase, list): 
            return map(self.generate_tree, phrase)
        elif phrase in self.grammar:
            return [phrase] + self.generate_tree(self.choose(self.grammar[phrase]))
        else: return [phrase]
    
    def mappend(self, fn, sList):
        "Append the results of calling fn on each element of list."
        return reduce(lambda x,y: x+y, map(fn, sList))


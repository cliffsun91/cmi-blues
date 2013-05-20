'''
Created on May 19, 2013

@author: cliffsun91
'''
from pyparsing import Word, OneOrMore, nums, alphanums, \
                      LineEnd, Suppress, Group, Combine
from collections import defaultdict


### GRAMMAR DEFINED HERE
EOL = LineEnd().suppress()  # matches and suppresses end of line
token = Word(alphanums + "/")
probability = Suppress("[") + ( (Combine(Word(nums) + "." + Word(nums))) | Word(nums) ) + Suppress("]")
inferenceRule = Word(alphanums + "/") + Suppress("->") \
                                            + (Group(token) | \
                                               Suppress("(") + Group(OneOrMore(token)) + Suppress(")")) \
                                            + probability \
                                            + EOL
line = inferenceRule | EOL
parser = line
### 

class GrammarPyParser(object):

    def __init__(self, fileName = ''):
        self.fileName = fileName
        
    def parseIntoDictionary(self):
        grammarList = self.parseIntoListFormat()
        print 'In list format:', grammarList
        grammarDict = defaultdict(list)
        for key, value in grammarList:
            grammarDict[key].append(value)
        print 'In reduced dictionary format:', grammarDict.items()
        return grammarDict
   
    def parseIntoListFormat(self):
        fh = open(self.fileName, 'r')
        lines = fh.readlines()
        
        grammarList = []
        for line in lines:
            #print line
            parsedLine = parser.parseString(line)
            if parsedLine:
                #print parsedLine
                (nonTerminal, productionRHS, probability) = tuple(parsedLine)
                #print nonTerminal, productionRHS, probability
                combinedList = [nonTerminal, (productionRHS.asList(), float(probability))]
                #print combinedList
                grammarList.append(combinedList)
        return grammarList
        

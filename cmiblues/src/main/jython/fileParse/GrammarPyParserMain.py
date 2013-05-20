'''
Created on May 20, 2013

@author: cliffsun91
'''
import os
from fileParse.GrammarPyParser import GrammarPyParser
from grammar.GrammarWeightingNormaliser import GrammarWeightingNormaliser

def main():
    print os.getcwd()
    basepath = os.path.dirname(__file__)
    filepath = os.path.abspath(os.path.join(basepath, "..", "..", "..", "..", "bluesGrammar.txt"))
    parser = GrammarPyParser(filepath)
    grammarDict = parser.parseIntoDictionary()
    
    grammarWeightingNormaliser = GrammarWeightingNormaliser(grammarDict)
    newGrammarDict = grammarWeightingNormaliser.normaliseProbabilities()
    print 'normalised grammar:', newGrammarDict

if __name__ == '__main__':
    main()
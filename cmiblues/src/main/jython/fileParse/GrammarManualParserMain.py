'''
Created on Jan 24, 2013

@author: cliffsun91
'''
import os.path
from fileParse.GrammarManualParser import GrammarManualParser
from grammar.GrammarWeightingNormaliser import GrammarWeightingNormaliser


def main():
    print os.getcwd()
    basepath = os.path.dirname(__file__)
    filepath = os.path.abspath(os.path.join(basepath, "..", "..", "..", "..", "bluesGrammar.txt"))
    parser = GrammarManualParser(filepath)
    grammarDict = parser.parseIntoDictionary()
    
    grammarWeightingNormaliser = GrammarWeightingNormaliser(grammarDict)
    newGrammarDict = grammarWeightingNormaliser.normaliseProbabilities()
    print 'normalised grammar:', newGrammarDict

if __name__ == '__main__':
    main()
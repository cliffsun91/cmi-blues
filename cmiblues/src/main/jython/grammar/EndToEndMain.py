'''
Created on Jan 25, 2013

@author: cliffsun91
'''
import os
from fileParse.GrammarFileParser import GrammarFileParser
from grammar.ProbabilisticSentenceGenerator import ProbabilisticSentenceGenerator

def main():
    print os.getcwd()
    basepath = os.path.dirname(__file__)
    filepath = os.path.abspath(os.path.join(basepath, "..", "..", "..", "bluesGrammar.txt"))
    parser = GrammarFileParser(filepath)
    grammarDict = parser.parseIntoDictionary()
    generator = ProbabilisticSentenceGenerator(grammarDict)
    print 'generated:', generator.generate('Q4')
    

if __name__ == '__main__':
    main()
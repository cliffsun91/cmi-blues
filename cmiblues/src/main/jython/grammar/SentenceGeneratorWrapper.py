'''
Created on Jan 29, 2013

@author: cliffsun91
'''
from org.cliffsun.individualproject.grammar import SentenceGenerator
from fileParse.GrammarFileParser import GrammarFileParser
from grammar.ProbabilisticSentenceGenerator import ProbabilisticSentenceGenerator

class SentenceGeneratorWrapper(SentenceGenerator):

    def __init__(self, grammarFileName):
        grammarFileParser = GrammarFileParser(grammarFileName)
        grammarDictionary = grammarFileParser.parseIntoDictionary()
        self.sentenceGenerator = ProbabilisticSentenceGenerator(grammarDictionary)
        
    def generate(self, var):
        return self.sentenceGenerator.generate(var)
    
    def generateTree(self, var):
        return self.sentenceGenerator.generateTree(var)
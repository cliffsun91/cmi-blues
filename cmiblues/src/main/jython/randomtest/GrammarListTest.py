'''
Created on May 20, 2013

@author: cliffsun91
'''
import os
from fileParse.GrammarPyParser import GrammarPyParser
from fileParse.GrammarManualParser import GrammarManualParser

def main():
    print os.getcwd()
    basepath = os.path.dirname(__file__)
    filepath = os.path.abspath(os.path.join(basepath, "..", "..", "..", "..", "bluesGrammar.txt"))
    parser1 = GrammarPyParser(filepath)
    parser2 = GrammarManualParser(filepath)
    grammarList1 = parser1.parseIntoListFormat()
    grammarList2 = parser2.parseIntoListFormat()
    print 'grammarList1 (pyparser):', grammarList1
    print 'grammarList2 (hand-written parser):', grammarList2
    firstElem1 = grammarList1[0]
    firstElem2 = grammarList2[0]
    if firstElem1 == firstElem2:
        print "They are the same!"
    else:
        print "They are not the same!"
    

if __name__ == '__main__':
    main()        
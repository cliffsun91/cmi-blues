'''
Created on Jan 19, 2013

@author: cliffsun91
'''
from collections import defaultdict


class GrammarFileParser(object):

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
        #print lines
        for line in lines:
            lineSplit = line.split('->')
            lineSplit = self.stripSurroundingWhiteSpaceCharactersInLine(lineSplit)
            if len(lineSplit) == 1:
                if lineSplit[0] != '': #empty line with \n but as it has been stripped it will be empty
                    raise Exception('Can\'t parse line: "%s"' % lineSplit[0])
            elif len(lineSplit) == 2: #length will be 2
                #print lineSplit
                termTuple = self.splitRHS(lineSplit[1])
                variable = lineSplit[0]
                grammarList.append([variable, termTuple])
            else:
                raise Exception('Too many "->" in the line, can\'t be parsed')
        return grammarList
        
    def stripSurroundingWhiteSpaceCharactersInLine(self, lineSplit):  
        return map(self.stripSurroundingWhiteSpaceCharactersInWord, lineSplit)
        
    def stripSurroundingWhiteSpaceCharactersInWord(self, word):
        strippedComponent = word.rstrip() 
        return strippedComponent.lstrip()
    
    def splitRHS(self, rhsTerm):
        (term, _, weighting) = rhsTerm.partition('[')
        termRSplit = term.rstrip()
        self.checkThatSentenceHasCompleteBrackets(termRSplit) #will raise exception if brackets are mismatched
        
        splitTermsList = termRSplit.rsplit() #splits them with whitespace delimiter
        finalTerms = self.reduceToFinalTermsList(splitTermsList)
        
        actualWeighting = float(weighting.rstrip(']'))
        return (finalTerms, actualWeighting)
        
    def reduceToFinalTermsList(self, splitTermsList):
        lenTerms = len(splitTermsList)
        #only do splitting one level parentheses, no nesting
        if lenTerms > 1:
            #technically we don't need to have brackets... they don't add anything
            finalTerms = self.reduceOneLevelParentheses(splitTermsList, lenTerms)
            finalTerms = self.getRidOfDoubleQuotationsForList(finalTerms) 
        else: #just one term, terminal
            finalTerms = self.getRidOfDoubleQuotationsForList(splitTermsList)[0]
        return finalTerms
        
    def reduceOneLevelParentheses(self, termsList, lenTermsList):
        if self.checkIfMultipleTermsListHasBrackets(termsList, lenTermsList):
            termsList[0] = termsList[0][1:]
            termsList[lenTermsList - 1] = termsList[lenTermsList - 1][:-1]
        else:
            raise Exception("brackets should occur at beginning and end of RHS term\n\
                             exception occured when parsing line: %s" % termsList)
        finalTermsList = filter(None, termsList) #filters out empty elements in list
        #print finalTermsList
        return finalTermsList      
        
    def checkIfMultipleTermsListHasBrackets(self, termsList, lenTermsList):
        leftBracket = termsList[0][0]
        rightBracket = termsList[lenTermsList - 1][-1]
        return leftBracket == "(" and rightBracket == ")"
        
    def checkThatSentenceHasCompleteBrackets(self, sentence):
        count = 0
        for char in sentence:
            if count < 0:
                raise Exception('bracket mismatched: closing bracket has appeared before opening bracket')
            if char == '(':
                count = count+1
            elif char == ')':
                count = count-1
        if count != 0:
            raise Exception("bracket mismatch\n\
                             exception occured when parsing line: %s" % sentence)
    
    def getRidOfDoubleQuotationsForList(self, termsList):
        return map(self.getRidOfDoubleQuotationsForWord, termsList)
    
    def getRidOfDoubleQuotationsForWord(self, word):
        if word[0] == '"' and word[-1] == '"':
            tempWord = word[1:]
            return tempWord[:-1]
        else:
            return word
    

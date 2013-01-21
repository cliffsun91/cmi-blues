'''
Created on Jan 19, 2013

@author: cliffsun91
'''
import os.path
from collections import defaultdict

def main():
    print os.getcwd()
    basepath = os.path.dirname(__file__)
    filepath = os.path.abspath(os.path.join(basepath, "..", "..", "..", "exampleGrammar.txt"))
    parser = GrammarFileParser(filepath)
    parser.parseIntoListFormat()
    parser.parseIntoDictionary()


class GrammarFileParser(object):

    def __init__(self, fileName = ''):
        self.fileName = fileName
        
    def parseIntoDictionary(self):
        grammarList = self.parseIntoListFormat()
        print grammarList
        grammarDict = defaultdict(list)
        for key, value in grammarList:
            grammarDict[key].append(value)
        print grammarDict.items()
        
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
        newLineSplit = []
        for component in lineSplit:
            strippedComponent = component.rstrip() 
            strippedComponent = strippedComponent.lstrip()
            newLineSplit.append(strippedComponent)
        return newLineSplit  
    
    def splitRHS(self, rhsTerm):
        (term, _, weighting) = rhsTerm.partition('[')
        termRSplit = term.rstrip()
        self.checkThatTermHasCompleteBrackets(termRSplit) #will raise exception if brackets are mismatched
        
        splitTermsList = termRSplit.rsplit() #splits them with whitespace delimiter
        #print splitTermsList
        
        finalTerms = self.reduceToFinalTermsList(splitTermsList)
        
        actualWeighting = float(weighting.rstrip(']'))
        return (finalTerms, actualWeighting)
        
    def reduceToFinalTermsList(self, splitTermsList):
        lenTerms = len(splitTermsList)
        #only do splitting one level parentheses, no nesting
        if lenTerms > 1:
            finalTerms = self.reduceOneLevelParentheses(splitTermsList, lenTerms)
        else: #just one term, terminal
            finalTerms = self.getRidOfDoubleQuotations(splitTermsList)[0]
        return finalTerms
        
    def reduceOneLevelParentheses(self, termsList, lenTermsList):
        leftBracket = termsList[0][0]
        rightBracket = termsList[lenTermsList - 1][-1]
        if leftBracket == "(" and rightBracket == ")":
            termsList[0] = termsList[0][1:]
            termsList[lenTermsList - 1] = termsList[lenTermsList - 1][:-1]
        else:
            raise Exception("brackets should occur at beginning and end of RHS term")
        finalTermsList = filter(None, termsList)
        #print finalTermsList
        self.getRidOfDoubleQuotations(finalTermsList) 
        return finalTermsList      
        
    def checkThatTermHasCompleteBrackets(self, term):
        count = 0
        for char in term:
            if count < 0:
                raise Exception('bracket mismatched: closing bracket has appeared before opening bracket')
            if char == '(':
                count = count+1
            elif char == ')':
                count = count-1
        if count != 0:
            raise Exception("bracket mismatch")
    
    def getRidOfDoubleQuotations(self, termsList):
        newTermsList = []
        for term in termsList:
            if term[0] == '"' and term[-1] == '"':
                tempTerm = term[1:]
                tempTerm = tempTerm[:-1]
                newTermsList.append(tempTerm)
            else:
                newTermsList.append(term)
        return newTermsList
    
    
if __name__ == '__main__':
    main()
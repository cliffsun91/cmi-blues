'''
Created on Jan 26, 2013

@author: cliffsun91
'''


class GrammarWeightingNormaliser(object):

    def __init__(self, grammarDict):
        self.grammarDict = grammarDict
        
    def normaliseProbabilities(self):
        for key, rhsList in self.grammarDict.iteritems():
            newList = self.normaliseProbabilitiesForList(rhsList)
            self.grammarDict[key] = newList
        return self.grammarDict
    
    def normaliseProbabilitiesForList(self, tupleList):
        newList = []
        accWeighting = 0
        for _, weighting in tupleList:
            accWeighting += weighting
        
        for rhsRule, weighting in tupleList:
            normalisedProbability = weighting/accWeighting
            newList.append((rhsRule, normalisedProbability))
        return newList
    
        
            
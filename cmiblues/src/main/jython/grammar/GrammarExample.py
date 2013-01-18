'''
Created on Jan 17, 2013

@author: cliffsun91
'''
#from org.cliffsun.individualproject.grammar import Example
import random
from collections import defaultdict

randomDict = dict(a = "1", a = "2", b = "3")
randomList = [["a", "1"], ["a", "2"], ["b", "3"]]

def main():
    someDict = defaultdict(list)
    for key, value in randomList:
        someDict
    


class GrammarExample():

    grammar = dict(
                   S = [ (['NP', 'VP'], 1) ],
                   NP = [ (['Art', 'N'], 1) ],
                   VP = [ (['V', 'NP'], 1) ],
                   Art = [('the', 0.5), ('a', 0.5)], 
                   #N = ['man'], 
                   #N = ['ball'], 
                   #N = ['woman'], 
                   #N = ['table'],
                   N = [('man', 0.7), ('ball', 0.1), ('woman', 0.1), ('table', 0.1)],
                   V = [('hit', 0.4) , ('took', 0.3), ('saw', 0.2), ('liked', 0.1)]
                  )

    def generate(self, phrase):
        "Generate a random sentence or phrase"
        if isinstance(phrase, list): 
            return self.mappend(self.generate, phrase)
        elif phrase in self.grammar:
            return self.generate(self.choose(self.grammar[phrase]))
        else: return [phrase]
        
    def choose(self, rhsList):
        if len(rhsList) > 1:
            rand = random.random()
            print rand, ", "
            acc = 0.0;
            for (word, prob) in rhsList:
                acc = acc + prob
                if (rand < acc):
                    return word
        else:
            (word, _) = rhsList[0]
            return word
        
    def generateTree(self, phrase):
        """Generate a random sentence or phrase,
         with a complete parse tree."""
        if isinstance(phrase, list): 
            return map(self.generate_tree, phrase)
        elif phrase in self.grammar:
            return [phrase] + self.generate_tree(self.choose(self.grammar[phrase]))
        else: return [phrase]
    
    def mappend(self, fn, sList):
        "Append the results of calling fn on each element of list."
        return reduce(lambda x,y: x+y, map(fn, sList))


if __name__ == '__main__':
    main()
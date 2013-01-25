'''
Created on Jan 23, 2013

@author: cliffsun91
'''

from collections import defaultdict

randomList = [['N', ('man', 0.7)], 
              ['N', ('ball', 0.1)], 
              ['N', ('woman', 0.1)], 
              ['N', ('table', 0.1)],
              ['NP', (['Art', 'N'], 1)]
             ]

exampleGrammar = dict(
                      NP = [ (['Art', 'N'], 1) ],
                      Art = [('the', 0.5), ('a', 0.5)], 
                      N = [('man', 0.7), ('ball', 0.1), ('woman', 0.1), ('table', 0.1)],
                     )


def main():
    someDict = defaultdict(list)
    for key, value in randomList:
        someDict[key].append(value)
    print someDict.items()
    print exampleGrammar.items()
    
if __name__ == '__main__':
    main()
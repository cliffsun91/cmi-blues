'''
Created on Jan 23, 2013

@author: cliffsun91
'''
from grammar.ProbabilisticSentenceGenerator import ProbabilisticSentenceGenerator


grammar = dict(
               #P(n) = [ (['poop', P (n-1)], 1) ],
               S = [ (['NP', 'VP'], 1) ],
               NP = [ (['Art', 'N'], 1) ],
               VP = [ (['V', 'NP'], 1) ],
               Art = [('the', 0.5), ('a', 0.5)], 
               N = [('man', 0.7), ('ball', 0.1), ('woman', 0.1), ('table', 0.1)],
               V = [('hit', 0.4) , ('took', 0.3), ('saw', 0.2), ('liked', 0.1)]
              )


def main():
    generator = ProbabilisticSentenceGenerator(grammar)
    phrase = generator.generate('S')
    print phrase


if __name__ == '__main__':
    main()
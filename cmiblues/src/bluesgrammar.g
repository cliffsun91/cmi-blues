grammar bluesgrammar;

P4 	:	(Q2 Q1 Q1) ;
Q2	:	N2  |  V1  V1 | V_2 N1 V_2 | 'H3_2' N_2 | 'H2_3' 'H2_3' 'H2_3' ;   
Q1	:	'C1' ;

V1	:	N1 | V_2 V_2 | 'H1_3' 'H1_3' 'H1_3' | 'H1_3' 'H1_3' 'A1_3' ;
V_2	:	N_2 | 'H_4' 'A_4' ;

N2	:	'C2' ;
N1	:	'C1' | 'L1' | 'S1' | 'A1' | 'R1' ;
N_2	:	'C_2' | 'L_2' | 'S_2' | 'A_2' | 'R_2' ;

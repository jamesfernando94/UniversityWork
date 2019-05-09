houses(H) :-
    length(H,5),
 % member(colour,nationality,pet,car,drink) 
    member(isHouse(red,brit,_,_,_),H),
	member(isHouse(_,greek,dog,_,_),H),
    member(isHouse(_,chinese,_,_,tea),H),
    leftOf(isHouse(white,_,_,_,_),isHouse(green,_,_,_,_),H),
    member(isHouse(green,_,_,_,coffee),H),
    member(isHouse(_,_,canary,ford,_),H),
    member(isHouse(yellow,_,_,landRover,_),H),
    H = [_,_,isHouse(_,_,_,_,milk),_,_],
    H = [isHouse(_,czech,_,_,_),_,_,_,_],
    nextTo(isHouse(_,_,_,toyota,_),isHouse(_,_,sheep,_,_),H),
    nextTo(isHouse(_,_,horse,_,_),isHouse(_,_,_,landRover,_),H),
    member(isHouse(_,_,_,vauxhall,beer),H),
    member(isHouse(_,german,_,volvo,_),H),
    nextTo(isHouse(_,czech,_,_,_),isHouse(blue,_,_,_,_),H),
    nextTo(isHouse(_,_,_,toyota,_),isHouse(_,_,_,_,water),H).

leftOf(X,Y,H) :-
    append(_,[Y,X|_],H). 
               
nextTo(X,Y,H) :-
    append(_,[Y,X|_],H);
    append(_,[X,Y|_],H).
/**
	Add a predicate to check the output
 */
method Reverse(str: array<char>) returns (reversedStr: array<char>)
  requires str.Length >= 0
  ensures Reversed(str, reversedStr)
{

  var i := 0;
  reversedStr := new char[str.Length];
  
  while (i < str.Length)
  invariant 0 <= i <= str.Length 
  {
    reversedStr[i] := str[str.Length-1-i];
    i:= i+1;
  }
}

predicate Reversed(str: array<char>, revStr: array<char>)
reads str, revStr
requires str.Length >= 0
requires str.Length == revStr.Length
{
  forall i : int :: 0 <= i < str.Length ==> str[i] == revStr[str.Length-i-1]
}


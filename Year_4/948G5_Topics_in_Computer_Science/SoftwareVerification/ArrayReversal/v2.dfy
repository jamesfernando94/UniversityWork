/**
	Add some varification to make it compileable
*/
method Reverse(str: array<char>) returns (reversedStr: array<char>)
  requires str.Length >= 0
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



/**
	Create the reverse method
*/
method Reverse(str: array<char>) returns (reversedStr: array<char>)
{

  var i := 0;
  reversedStr := new char[str.Length];
  
  while (i < str.Length)
  {
    reversedStr[i] := str[str.Length-i-1];
    i:= i+1;
  }
}


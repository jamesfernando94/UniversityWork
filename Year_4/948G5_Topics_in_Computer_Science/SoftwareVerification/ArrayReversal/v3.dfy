/**
	Generalise the array
 */
method Reverse<T(0)>(arr: array<T>) returns (revarr: array<T>)
  requires arr.Length >= 0
{

  var i := 0;
  revarr := new T[arr.Length];
  
  while (i < arr.Length)
  invariant 0 <= i <= arr.Length 
  {
    revarr[i] := arr[arr.Length-1-i];
    i:= i+1;
  }
}



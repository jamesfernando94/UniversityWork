/**
	Add a predicate to check the output
 */

method Reverse<T(0)>(arr: array<T>) returns (revarr: array<T>) //T(0) is aparrently needed to be able to limit the types of the array to the base types and therefore allow instatiation of the array
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

method Main()
{
	var input := new int[5];
	input[0],input[1],input[2],input[3],input[4] := 1,2,3,4,5;
	var output := Reverse(input);
	var i := 0;
	while i < output.Length
	invariant i <= output.Length
	{
		print output[i];
		i := i + 1;
	}

}



predicate Reversed<T>(arr: array<T>, revArr: array<T>)
reads arr, revArr
requires arr.Length >= 0
requires arr.Length == revArr.Length
{
  forall i : int :: 0 <= i < arr.Length ==> arr[i] == revArr[arr.Length-i-1]
}


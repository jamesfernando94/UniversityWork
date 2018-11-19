/**
	Add in a subarray function to provide and invariant to the while loop
 */

method Reverse<T(0)>(arr: array<T>) returns (revArr: array<T>) //T(0) is aparrently needed to be able to limit the types of the array to the base types and therefore allow instatiation of the array
	requires arr.Length > 0
	ensures revArr.Length == arr.Length
	ensures Reversed(arr, revArr)
{

	var i := 0;
	revArr := new T[arr.Length];
	
	while (i < arr.Length)
	decreases arr.Length - i
	invariant 0 <= i <= arr.Length
	invariant forall k | 0 <= k < i :: revArr[k] == arr[arr.Length-1-k] // From https://stackoverflow.com/a/45396943
	{
	revArr[i] := arr[arr.Length-1-i];
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
	decreases output.Length - i
	invariant i <= output.Length
	{
		print output[i];
		i := i + 1;
	}

}

predicate Reversed<T>(arr: array<T>, revArr: array<T>)
reads arr, revArr
{
	arr.Length == revArr.Length && forall i : int :: 0 <= i < arr.Length ==> arr[i] == revArr[arr.Length-i-1]
}


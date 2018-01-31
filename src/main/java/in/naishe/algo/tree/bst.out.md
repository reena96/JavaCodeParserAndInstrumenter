One sample run.

		Array used to create the tree: 
		[68, 32, 71, 66, 67, 42, 73, 14, 51, 85, 49, 55, 81, 92, 89]
		--------------------------------------------------------------------------------
		Tree created!
		14, 32, 42, 49, 51, 55, 66, 67, 68, 71, 73, 81, 85, 89, 92, 

		--------------------------------------------------------------------------------
		searching: 26
		Does not exist: 26
		searching: 48
		Does not exist: 48
		searching: 6
		Does not exist: 6
		searching: 14
		Found: 14
		--------------------------------------------------------------------------------
		Tree min: 14, Tree max: 92
		--------------------------------------------------------------------------------
		NOTE: 
		  :If the array contains dupes, the expected value might NOT match.
		  :You can always justify the accuracy by manually creating the tree in order of insertion and checking. 
		  :But this check good for non-dupe array.
		for: 66
			Found predecessor: 55; expected: 55
			Found successor: 67; expected: 67
		for: 42
			Found predecessor: 32; expected: 32
			Found successor: 49; expected: 49
		for: 14
			no predecessor of 14
			Found successor: 32; expected: 32
		for: 49
			Found predecessor: 42; expected: 42
			Found successor: 51; expected: 51
		for: 55
			Found predecessor: 51; expected: 51
			Found successor: 66; expected: 66
		for: 42
			Found predecessor: 32; expected: 32
			Found successor: 49; expected: 49
		for: 14
			no predecessor of 14
			Found successor: 32; expected: 32
		--------------------------------------------------------------------------------
		14, 32, 42, 49, 51, 55, 66, 67, 68, 71, 73, 81, 85, 89, 92, 
		Deleted '73', the remaing is 
		14, 32, 42, 49, 51, 55, 66, 67, 68, 71, 81, 85, 89, 92, 

		Deleted '67', the remaing is 
		14, 32, 42, 49, 51, 55, 66, 68, 71, 81, 85, 89, 92, 

		Deleted '85', the remaing is 
		14, 32, 42, 49, 51, 55, 66, 68, 71, 81, 89, 92, 



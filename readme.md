#TargetShooting CSV Parser

##Usage
The **-help** command gives you hints of how to use the parser. 

##CSV file structure
Each record in the file has to follow this conventions. 

	Date;Description;Result;Category;TypeOfProgramme
	
Each record file must contain a header. However, the naming of the header fields is not relevant and ignored by the parsing logic.

###Date
The date in big endian format.
Example: **2015-05-28**

###Description
Your personal description.

###Result
The score of this record. Use a point . as delimiter for floating point scores.

Example: **10.7** or **9**

###Category
The category of this record. Categories won't be validated by the parser himself.

They have to follow the following conventions:

**For results in elimination mode**

	<DISCIPLINE> <POSITION> <# of shots>
	
**For results in final mode**

	<DISCIPLINE> <POSITION> Final <# of shots>
	
**For combined results (elimination+final)**

	<DISCIPLINE> <# of shots> Final
	
The **discipline** always contains the weapon/rifle type and the distance.

**Examples**

 * AR10: *Air Rifle 10 meters*
 * SB50: *Small Bore 50 meters*
 * CB10: *Crossbow 10 meters*
 * CB30: *Crossbow 30 meters*

**Category examples**

	CB30 K 1
	CB30 S 10
	CB30 Match
	CB30 Match Final
	CB30 S Final 10
	AR10 60
	AR10 60

###TypeOfProgramm
Categories won't be validated by the parser himself. This field may be empty.

Choose one of the following types.

* T *for trainings*
* C *for competitions*
* CaH *for competitions at home. This may be a qualification which is being shot at the home shooting range.*

##Result concatenation

Result are depending on each other. Here's an example:

An air rifle competition for women consists of 40 shots. Those 40 shots are grouped in four ten-shot results. And each of this ten-shot result consists of ten single shots.

The result parser may handle all levels of detail.

**No details, only the final score**

		2015-05-28;My Programme;394;AR10 40;T
**Only the four ten-shot results**
		
		2015-05-28;My Programme;99;AR10 10;T
		2015-05-28;My Programme;98;AR10 10;T
		2015-05-28;My Programme;100;AR10 10;T
		2015-05-28;My Programme;97;AR10 10;T
		2015-05-28;My Programme;394;AR10 40;T
		
**Every single shot**

		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;9;AR10 1;T
		2015-05-28;My Programme;10;AR10 1;T
		2015-05-28;My Programme;99;AR10 10;T
		<...single shots of second...>
		2015-05-28;My Programme;98;AR10 10;T
		<...single shots of third...>
		2015-05-28;My Programme;100;AR10 10;T
		<...single shots of fourth...>
		2015-05-28;My Programme;97;AR10 10;T
		2015-05-28;My Programme;394;AR10 40;T
**General structure**

		<Elimination results (in the desired amount of detail)>
		<Final results (usually ten shots)>
		<Elimination+Final (combined result)>
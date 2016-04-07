
## Project "Dulux" Paint Batch Creator

*Caveat: I have not built in exhaustive validation and error checking. The goal was to mainly get a working version and refactor as much of my own TODO's as was possible given the time I had.*

### The Challenge Rules

The coding challenge rules are included for reference in the file ./challenge-rules.txt included with this bundle.

### Running the tool

For convenience there is a pre-built, executable jar included at the root of the project (*dulux-paint-creator.jar*). 

The sample data included in the 'data' directory is based on the examples described in the problem statement. To run them do the following:

For a "viable" solution use one of the following files provided:

		./data/order_1.txt
		./data/order_2.txt
		./data/order_3.txt
		./data/no_solution_1.txt

the command to run the solver is:

		java -jar dulux-paint-creator.jar data/order_1.txt
		...
		
		java -jar dulux-paint-creator.jar data/no_solution_1.txt 
		
The source code is under:

		./src/main/java
		
where the package structure is 'paint.batch.*'.

### Examples and expected output

Based on the examples included under ./data/*.txt the following are the expected results:

		data/order_1.txt -> 'G G G G M'
		data/order_2.txt -> 'M M'
		data/order_3.txt -> 'G M G M G'
		data/no_solution_1.txt -> 'No solution exists'




# Canvas Drawer

Built with Java 11 and Maven.

## Build instructions
To build the program the following command should be executed in a console
```
mvn package
```
The package will be generated under <i>target</i> directory as <b><i>canvasdrawer-1.0-jar-with-dependencies.jar</i></b>

## Run program
To run the program step into the target directory where the jar was generated and execute the following command:
```
java -jar canvasdrawer-1.0-jar-with-dependencies.jar
```


## Available commands
```
Q		Quits application.

R		Adds a rectangle to a canvas.
		R x1 y1 x2 y2
		x1		Position on the x axis for the upper left corner of the rectangle. Must be a number greater than 0.
		y1		Position on the y axis for the upper left corner of the rectangle. Must be a number greater than 0.
		x2		Position on the x axis for the lower right corner of the rectangle. Must be a number greater than 0.
		y2		Position on the y axis for the lower right corner of the rectangle. Must be a number greater than 0.
		
		e.g.: R 1 2 4 10

C		Creates and initializes a canvas.
		C w h
		w		Width of the canvas. Must be a number greater than 0.
		h		Heigt of the canvas. Must be a number greater than 0.
		
		e.g.: C 20 10

L		Adds a line to a canvas.
		L x1 y1 x2 y2
		x1		Position on the x axis for the start point. Must be a number greater than 0.
		y1		Position on the y axis for the start point. Must be a number greater than 0.
		x2		Position on the x axis for the end point. Must be a number greater than 0.
		y2		Position on the y axis for the end point. Must be a number greater than 0.
		
		e.g.: L 1 2 1 10
```
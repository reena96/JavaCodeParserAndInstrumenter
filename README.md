# Java Instrumentation #
In the context of computer programming, instrumentation refers to an ability to monitor or measure the level of a product's performance, to diagnose errors and to write trace information.  Programmers implement instrumentation in the form of code instructions that monitor specific components like variable values and expressions in a system (for example, instructions may output logging information to appear on screen). Source code instrumentation is the most powerful, flexible and accurate way to provide code coverage analysis. 

## Why have I done this project? ##
Created an instrumentation program that takes the syntactically correct source code of the Java application and parsed the application into a tree using the Eclipse Java Abstract Syntax Tree (AST) parser and used Visitor Design Pattern to traverse through the nodes of the abstract syntax tree and compute the scopes and variables & expressions and output the source code with the instrumentation statements following each line of code containing expressions. 

## How did I do it? ##
The program the nodes of the tree are traversed in order using the Visitor Design Pattern to compute scopes and variables that are declared and used in them. Each instrumenting statement has been constructed based on a well-defined template code fragment and upon insertion of the template into the parsed program, the template will be instantiated with the references to concrete variables whose values are captured in the given scope. Once the instrumentation procedure is finished, the parse tree is unparsed  (i.e., the source code is generated from the parse tree) and the instrumented source code is outputted.

#### I have resolved dependencies by adding the required libraries of org.eclipse.jdt.{annotation, apt.core, compiler, runtime} and org.osgi.core in build.gradle and build.sbt   ####
#### I have created an Abstract Syntax tree from the Java file  ####
#### I have learned about AST, ASTParser, ASTNode, TypeDeclaration, FieldDeclaration, MethodInvocation, ExpressionStatement, Assignment and so on ####
#### I have used the JDT AST plugin for viewing the Abstract Syntax Tree structure corresponding to each individual AST ####
#### I have created a scope table for mapping the variables to the scopes with line numbers  ####
#### Then, I created instrumentation of the file by creating Template.instrum() statements ####
#### The instrumentation program gives the output that containis line number, statement type and the values of the variables associated with the variables declared and used in the program. ####


## What is this repository for? ##
The repository contains the project files of HW1 plus the files that I created for doing HW2.
The files are as follows:
#### ASTCreateTree - Creates Parse tree ####
#### MyASTVisitor - contains methods for traversing through nodes by Visitor pattern for computing scopes and then for instrumenting the program.   ####
#### Template - contains the static instrum method which will be called for printing the logging values/instrumented statements. ####


## How do I get set up? ##
To get started, clone the project folder and open it on IntelliJ. Once the project is opened on IntelliJ, then access the directory 
structure. 
#### algo->src->main->java->in.naishe.algo-> ####
### Run ASTCreateTree.java ###
This will create the ASTTree, compute scopes and finally, it will create the instrumentation for the 
#### Open search-> Run BinarySearchFirstOccurence and Run SearchForAil ####
#### Open sort-> Run CountingSort, Run Heap, Run QuickSort, Run ThreeWayPartiiton ####
#### Open tree-> Run BST, Run Node, Run RedBlackTree ####
#### The above mentioned programs form the basic structure of the project folder. ####
#### All of the programs have meaningful functionality defined in terms of the category of algorithm it falls under.####

## Description of your implementation:##
#### ASTCreateTree- Creates the Abstract Syntax Tree and calls MyASTVisitor ####
#### MyASTVisitor- contains visit methods that will allow us to traverse through nodes, compute scopes and finally instrument the program. ####


## How you used Java tools for debugging with snapshots/core dumps when needed ##, 
I have run the following Java Monitoring Tools on the project and the documentation, screenshots and the description of my use of the Java Monitoring tools can be found here: https://bitbucket.org/rputho2/reenamary_puthota_hw1/src/d0a582b968b61501f89f9dbe56048d49b18e01f2/HW1%20Documentation%20for%20Java%20Monitoring%20Tools.pdf?at=master&fileviewer=file-view-default

## What are the limitations of your implementation. ##
I have build the project using Gradle. I should also build it using SBT.

## Configuration ##
#### Java 1.8.0_144 #### 
#### Scala 2.12.3 (Java HotSpot(TM) 64-Bit Server VM) ####
#### IntelliJ Idea 2017.2.3 #### 

## Dependencies:##
#### group: 'junit', name: 'junit', version:'4.12'####
#### group: org.eclipse.jdt', name: 'org.eclipse.jdt.core', version: '3.7.1'####
#### group: 'org.eclipse.jdt', name: 'org.eclipse.jdt.annotation', version: '2.0.0'####
#### group: 'org.eclipse.jdt.core.compiler', name: 'ecj', version: '4.4.2'####
#### group: 'org.eclipse.core', name: 'org.eclipse.core.runtime', version: '3.7.0'####
#### group: 'org.apache.commons', name: 'commons-io', version: '1.3.2'####
#### group: 'log4j', name: 'log4j', version: '1.2.16'####

### Project Owner ###
Nishant Neeraj

### Instructor ###
Mark Grechanik

### Teaching Assistant ###
Sri Phani Mohana Tejaswi Gorti

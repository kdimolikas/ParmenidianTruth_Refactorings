# Parmenidian Truth Refactorings
Parmenidian Truth<sup>1</sup> is a tool that takes as input a set of versions of a database schema and produces a pptx file with the visualization of the evolution of the database, one slide per version.
The original version was created by M. Kolozof in the context of his Diploma Thesis under the supervision of P. Vassiliadis, in June 2013.
This is a refactored version of the tool implemented by K. Dimolikas, M. Zerva and I. Kouvatis in partial fulfillment of the requirements of the graduate course "Software & Data Evolution", in June 2017.
In this version of the Parmenidian Truth project, we introduce a series of modifications that remedy the following design defects:
* Package Level Issues
* Lack of APIs
* Duplicated Code
* God Classes
* Misplaced Methods
* Lack of Unit Tests
 The alterations applied to the source code are determined on the basis of abiding by the following design principles and patterns:
* Open - Closed Principle (OCP)
* Single - Responsibility Principle (SRP)
* Dependency - Inversion Principle (DIP)
* Interface - Segregation Principle (ISP)
* Abstract Factory Design Pattern
* Template Method Design Pattern
 Aiming at testing the correctness of our modifications, we also implement a set of unit tests utilizing the unit testing framework for Java, [JUnit][1] and the mocking framework [Mockito][2].
 1: For more info about the Parmenidian Truth tool, please feel free to visit our team on github [DAINTINESS-Group](https://github.com/DAINTINESS-Group)
[1]: https://junit.org/junit5/
[2]: https://site.mockito.org/

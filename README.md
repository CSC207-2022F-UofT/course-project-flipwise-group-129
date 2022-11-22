# How to start and run your program
- [ ] Clone the Project Flipwise repository and open the folder in your Intellij (preferred to easily make JavaDoc)
- [ ] Go to file main.java and run main() function by clicking the green play button on the left of the method or right-clicking on the file and selecting the run main.main option. 

# Testing
## How to run the testing program 
- [ ] Clone the Project Flipwise repository and open the folder in your Intellij (preferred to easily make JavaDoc)
- [ ] Go to file main.java and run main() function by clicking the green play button on the left of the method or right-clicking on the file and selecting the run 
## How to generate the test coverage report


# Generating the Javadoc and a report on Javadoc coverage
If you could not provide this, a Javadoc in HTML files and a report on Javadoc coverage are also fine. I can evaluate the Code Style & Documentation more conveniently through Javadoc!

Check this website to see how to generate HTML files for Javadoc: https://www.jetbrains.com/help/idea/working-with-code-documentation.html#generate-javadoc
## Gradle Project
Import this project into your Intellij editor. It should automatically recognise this as a gradle repository.
The starter code was built using SDK version 11.0.1. Ensure that you are using this version for this project. (You can, of course, change the SDK version as per your requirement if your team has all agreed to use a different version)

You have been provided with two starter files for demonstration: HelloWorld and HelloWorldTest.

You will find HelloWorld in `src/main/java/tutorial` directory. Right click on the HelloWorld file and click on `Run HelloWorld.main()`.
This should run the program and print on your console.

You will find HelloWorldTest in `src/test/java/tutorial` directory. Right click on the HelloWorldTest file and click on `Run HelloWorldTest`.
All tests should pass. Your team can remove this sample of how testing works once you start adding your project code to the repo.

Moving forward, we expect you to maintain this project structure. You *should* use Gradle as the build environment, but it is fine if your team prefers to use something else -- just remove the gradle files and push your preferred project setup. Assuming you stick with Gradle, your source code should go into `src/main/java` (you can keep creating more subdirectories as per your project requirement). Every source class can auto-generate a test file for you. For example, open HelloWorld.java file and click on the `HelloWorld` variable as shown in the image below. You should see an option `Generate` and on clicking this your should see an option `Test`. Clicking on this will generate a JUnit test file for `HelloWorld` class. This was used to generate the `HelloWorldTest`.

![image](https://user-images.githubusercontent.com/5333020/196066655-d3c97bf4-fdbd-46b0-b6ae-aeb8dbcf351d.png)

You can create another simple class and try generating a test for this class.

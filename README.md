# Flipwise Submission
## 1. How to Start and Run Flipwise
- [ ] Clone the Project Flipwise repository and open the folder in your Intellij (preferred to easily make JavaDoc)
- [ ] Go to file Main.java located in the src/main/java directory
- [ ] Right click on the Main and select Run Main.main() 

## 2a. If Running on MacOS- How to run the testing program and generate the test coverage report
- [ ] Select 'Testing Configurations' in the Configuration dropdown and click on 'Edit Configurations'
- [ ] In the pop up under 'Build and Run' make sure the tests read from 'All Directory' and change the path to be your path to the test package within src
- [ ] Select 'Run Testing Configurations with Coverage' button three buttons left of Configuration dropdown
- [ ] Coverage Report will thus indicate that Entity and Controllers are tested to 100%.

## 2b. If Running on Windows computers- How to Open Test Coverage Report
- [ ] Because of Windows file system errors, the tests don't run as intended: 
- [ ] Please open the TestCoverageReport folder in files and open index.html to open the Test Coverage Report

## 3. Generating Javadocs
- [ ] On the top of Intellij, open the Tools dropdown and select GenerateJavadoc
- [ ] Select 'Whole Project' for Javadoc Scope and push the generate button

## 4. Design Pattern Used
### 1. Dependency Injection:
- [] Example: (where the code mentions it)
- [] Our project has a lot of potential for further feature extensions beyond our time in this course. Further down, we may have many different instances of Debt in our program
- [] As a result we do not want a hard dependency on the Debt class within the PurchaseBalances Class.
- [] Thus, when we want to add a Debt pair, we create the Debt pair outside the class, and inject it inside.
- [] This way, we can accomodate for future subclasses of Debt
### 2. Observer Design Pattern
 - [] In order to update the views directly whenever the presenter ihas new information, we have the observer dependency pattern implemented in all our presenter classes.
 - [] Example:
 - [] All our presenter classes follow clean architecture and as a result implement their respective outputboundary interfaces.
 - [] The corresponding use case modifies the data and once done utilizes the output boundary which notifies the presenter of the changes that have been made through the output data structures.
  - [] In this way, an object is able to notify other objects without making assumptions about who these objects are. 
### 3. Iterator Design Pattern:
- [] We have implemented the iteration design for our classes were we have stored data in List or other Collection objects (such as PurchaseBalance, PurchaseList, and PlannedList)
- [] Example: 
- [] we have implemented the Iterator design pattern for our ItemList  abstract class, where we have the abstract class implement iterable .
- [] we also have the same implemented in PurchaseBalance, and thus we can iterate through all the debts in the group without revealing the underlying List datastructure.

## 5. Github Features Used
- [] Pull Requests - We have intensively used the pull request feature in Github, along with the faetures to approve, close pushes, reject, and request changes from the author of the pull request.
- [] Github CLI - half the team used the CLI to perform github actions such as push, pull, checkout to new branches, pull from origin, and to merge branches. 
- [] Browse Files from a commit hash - often times, we found ourselves with problems relating to incorrect pushes or pushes where we made errors. This feature helped us to fix our mistaken commits that could have potentially broken the program. 
- [] View differences between files - the split difference view helped us thoroughly understand the changes our peers made in commits and pull requests. This helped us understand their changes better, suggest changes, or approve the changes.
- [] Merge Conflict Editor - we used this for some of our pull requests where they were merge conflicts in our .xml files, that were untracked in our .gitignore. Made it easier to instantly solve merge conflicts in merging to main.
- [] .gitignore - we used this to add most of our files in .idea/ and other configuration files into .gitignore to remove these files from tracking. Since we did not need to track changes in these files, adding a .gitignore helped us save time from solving avoidable merge conflict errors.

## 6. User Stories Code Walkthrough
 - [] ![UserRegister Use Case] (https://github.com/CSC207-2022F-UofT/course-project-flipwise-group-129/blob/main/UserRegisterUseCase.jpeg)
 - [] ![SettlementPayment Use Case] (https://github.com/CSC207-2022F-UofT/course-project-flipwise-group-129/blob/main/SettlementPayment_Walkthrough.png)

## 7. Pull Requests Document
 - [] https://docs.google.com/document/d/1B5JUseiYhwcYRWCrykhv1qstKCXgi4C8lS-M4P5Y0x8/edit
 
## 8. Updates Since Milestone 4
- [ ] Implemented iterator design patterns on entities that have list attributes (PlanningList, PurchaseList, PaymentBalance). This simplified use cases that required to iterate through these entities to, for example, return the correct data type. 
- [ ] Entities now store ids to avoid nested data storage and reduce redundant storage in the json files. Use cases updated implementations to accommodate for this change.
- [ ] Test database has been created to run the tests. Testing now has full coverage and all tests pass as expected. 
- [ ] Javadocs have been added for view classes. Everything is now fully documented. 
- [ ] Fixed connections passing data from the view classes to the controllers. This connected the front-end and back-end of the project. 

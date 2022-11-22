#Flipwise Submission
## 1. How to Start and Run Flipwise
- [ ] Clone the Project Flipwise repository and open the folder in your Intellij (preferred to easily make JavaDoc)
- [ ] Go to file main.java located in src/main/java and run main() function by clicking the green play button on the left of the method or right-clicking on the file and selecting the run main.main option. 

## 2. Running the Tests
### How to run the testing program 
- [ ] Clone the Project Flipwise repository and open the folder in your Intellij (preferred to easily make JavaDoc)
- [ ] Go to file main.java and run main() function by clicking the green play button on the left of the method or right-clicking on the file and selecting the run 
### How to generate the test coverage report

## 3. Generating Javadocs

## 4. Design Pattern Used
### 1. Dependency Injection:
- [] Example: (where the code is mention it)
- [] Our project has a lot of potential for further feature extensions beyond our time in this course. Further down, we may have many different instances of Debt in our program
- [] As a result we do not want a hard dependency on the Debt class within the PurchaseBalances Class.
- [] Thus, when we want to add a Debt pair, we create the Debt pair outside the class, and inject it inside.
- [] This way, we can accomodate for future subclasses of Debt
### 2. Observer Dependency Pattern
 - [] Example:
 - [] All our presenter classes follow clean architecture and as a result implement their respective outputboundary interfaces.
 - [] The corresponding use case modifies the data and once done utilizes the output boundary which notifies the presenter of the changes that have been made through the output data structures.
  - [] In this way, an object is able to notify other objects without making assumptions about who these objects are. 
  
### 3. Iterator Design Pattern (to be implemented):
- [] We plan on implementing an iteration design for our classes were we have stored data in List or other Collection objects (such as PurchaseBalance, PurchaseList, PlannedList etc.)

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

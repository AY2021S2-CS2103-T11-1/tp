---
layout: page
title: User Guide
---
# TutorBuddy
TutorBuddy is an application made for independent tutors as a management tool to cut down on admin overheads, 
by graphically managing their student’s information with a Graphical User Interface (GUI).
It allows for faster and more effective student management.

**Table of Contents**
* [Quick start](#quick-start)
* Features
    * Listing all students: `list student`
    * Locating student profile by name: `find student`
    * Adding a student: `add student`
    * Listing all tuition sessions: `list session`
    * Locating a tuition session by students name / date: `find session`
    * Adding a tuition session: `add session`
    * Deleting a tuition session: `delete session`
    * Exiting the program: `exit`
* FAQ
* Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TutorBuddy.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the home folder for your TutorBuddy application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. <br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`list student`** and pressing Enter will open the list of all students.<br>
   
   Some example commands you can try:
   
   **Students**
   * `list student`: List all students
   * `find student James`: Finds and lists all students that have the name **James**
   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 a/99999999 r/Mother`: Adds a student John Doe to the Tutor Buddy application
   * `delete student 3`: Deletes the 3rd student in the student list
   
   **Tuition Session**
   * `list session`: List all tuition sessions
   * `find session James`: Finds and lists all tuition sessions that James have
   * `add session n/John Doe d/14-02-2021 t/1300 s/Biology f/40`: Adds a tuition session for John Doe happening on 14-02-2021
   * `delete session 1`: Deletes the 1st tuition session in the tuition session list
   
   **General**
   * `exit`: Exits the application

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Listing all students : `list student`

Shows a list of all students in the TutorBuddy

Format: `list student` <br>

### Locating student profile by name : `find student`

Finds students that matches the keyword given

Format: `find student KEYWORD`
* The search will be case-insensitive. e.g. searching “stonks” will match “STONKS”.
* As long as the keyword matches the name of the student, it will be regarded as a match. For example, if a person’s name is “John Lee”, searching “John” will be sufficient.
* Only the student’s name will be searched.

Example:

# | Student Name
---- |---------
1 | John Lee
2 | Johnz Tan
3 | Jon Koh
4 | Samuel Lee

* `find student John` returns John Lee
* `find student Sam` returns nothing
* `find student Lee` returns "John Lee" and "Samuel Lee"

### Adding a student: `add student`

Adds a student to the TutorBuddy

Format: `add student n/NAME p/STUDENT_PHONE_NUMBER e/EMAIL a/ADDRESS m/PARENT_PHONE_NUMBER r/RELATIONSHIP_WITH_PARENT` <br>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 a/99999999 r/Mother`

### Deleting a student: `delete student`

Deletes the specified student from TutorBuddy

Format: `delete student STUDENT_NAME` <br>
* Deletes the student with the specified name
* `STUDENT_NAME` must have an exact match to the name in the student profile

Examples:
* `list student` followed by `delete 2` deletes the 2nd person in the address book.
* `find student Betsy` followed by `delete 1` deletes the 1st person in the results of the find student command.

### Listing all tuition sessions: `list session`

Shows a list of all tuition sessions in TutorBuddy

Format: `list session`

### Locating tuition session by student name / date: `find session`

Find tuition sessions that match the keyword given

Format: `find session KEYWORD`
* The search will be case-insensitive. e.g. searching “stonks” will match “STONKS”
* For student names:
    * Any word that a student’s name contains will be matched. For example, if a session student’s name is “moon”, searching “moo” will match it
* For dates:
    * Only the exact date in the format (DD-MM-YYYY) will be matched.

Example:<br>
The command `list session` will show the following:

# | Sessions
---- |---------
1 | John Lee<br>15/2/2021 13:00<br>2h $60/h<br>Computer Science
2 | Johnz Lee<br>16/2/2021 14:00<br>1.5h $30/h<br>Math
3 | John Dam<br>18/2/2021 15:00<br>2h $0.10/h<br>Software Engineering
4 | Sammuel Bruce Lee<br>19/2/2021 18:00<br>2h $30/h<br>Wing Chun

* `find session John` returns all John in TutorBuddy
* `find session Jo` returns all John in TutorBuddy
* `find session John Lee` returns all John Lee in TutorBuddy
* `find session Zach` returns nothing

### Adding a tuition session: `add session`

Adds a tuition session to the TutorBuddy

Format: `add session n/STUDENT_NAME d/DATE t/TIME l/LENGTH_OF_SESSION s/SUBJECT f/FEE`

* `STUDENT_NAME` should match the exact student’s name in TutorBuddy
* `DATE` should be in DD-MM-YYYY format
* `TIME` should be in 24-hr format
* `LENGTH_OF_SESSION` should be in hours
* `FEE` should be the tuition fee/hr

Examples:
* `add session n/John Doe d/14-02-2021 t/1800 l/2 s/Biology f/40`

### Deleting a tuition session: `delete session`

Deletes the specified tuition session from the TutorBuddy

Format: `delete session INDEX`

* Deletes the tuition session at the specified INDEX
* The index refers to the index number shown in the displayed session list
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `list session` followed by `delete 2` deletes the 2nd session in the TutorBuddy
* `find session Betsy` followed by `delete 1` deletes the 1st session in the results of the find session command

### Exit the program: `exit`

Exits the program

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TutorBuddy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

**Students**

Action | Format, Examples
--------|------------------
**List** | `list student`
**Find** | `find student KEYWORD`<br><br>e.g. `find student John`
**Add** | `add student n/NAME p/STUDENT_PHONE_NUMBER e/EMAIL a/ADDRESS m/PARENT_PHONE_NUMBER r/RELATIONSHIP_WITH_PARENT`<br><br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 a/99999999 r/Mother `
**Delete** | `delete student INDEX`<br><br>e.g. `delete student 3`

**Tuition Session**

Action | Format, Examples
--------|------------------
**List** | `list session`
**Find** | `find student KEYWORD`<br><br>e.g. `find session John`
**Add** | `add session n/STUDENT_NAME d/DATE t/TIME l/LENGTH_OF_SESSION s/SUBJECT f/FEE`<br><br> e.g., `add session n/John Doe d/14-02-2021 t/1800 l/2 s/Biology f/40`
**Delete** | `delete session INDEX`<br><br>e.g. `delete session 3`

---
layout: page
title: User Guide
---

ezFoodie is a **desktop application for managing members in a restaurant, optimized for use via a Command Line Interface 
(CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ezFoodie can get your 
member management tasks done faster than traditional GUI applications.

Table of Contents 
* Quick Start 
* Features
  * Viewing help : `help`
  * Adding a member: `add -m`
  * Search members: `find`
  * List members: `find -m`
  * View member: `show -m`
  * Delete members: `del -m`
  * Add Transaction: `add -t`
* FAQ
* Command Summary


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the 
   app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
   open the help window.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Meanings of shortcuts:
  * `-m`: member 
  * `-n`: name 
  * `-p`: phone number
  * `-e`: email 
  * `-u`: username
  * `-t`: transaction
  * `-d`: date
  * `-b`: bill amount


</div>

### Viewing help : `help`

Opens a new window to show how to use the commands, and a link to the User Guide.

Format: `help`


### Adding a member: `add`

Adds member to the member list

Format: `add -m -n <NAME> -p <PHONE_NUMBER> -e <EMAIL>`

Example: `add -m -n John Doe -p 98765432 -e johndoe@gmail.com`

### Searching a member: `find`

* Search members by name:
  * Format: `find -m -u <NAME>` 
  * Example: `find -m -u John Doe` 
* Search members by phone number:
  * Format: `find -m -p <PHONE_NUMBER>`
  * Example: `find -m -p 98765432`
* Search members by email:
  * Format:  `find -m -e <EMAIL>`
  * Example: `find -m -e johndoe@gmail.com`
* Search members by registration date:
  * Format:  `find -m -d <REGISTRATION_DATE>`
  * Example: `find -m -d 12-01-2021` 
* Search member by member ID:
  * Format: `find -m -id <MEMBER_ID>`
  * Example: `find -m -id 1`

### Viewing member: `view`

View member from member list by their id

Format: `show -m -id <MEMBER_ID>`

Example: `show -m -id 1`

### Listing all members : `list`

Shows a list of all members in the address book.

Format: `find -m`

Example: `find -m`

### Deleting a person : `delete`

Deletes member from member list by their id.

Format: `del -m -id <MEMBER_ID>`

Example: `del -m -id 1`

### Adding a transaction: `add`

Add transaction amount corresponding to member ID

Format: `add -t -id <MEMBER_ID> -b <BILLING>`

Example: `add -t -id 1 -b 200.00`


### Redeeming membership points `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`

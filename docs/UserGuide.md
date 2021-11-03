---
layout: page
title: User Guide
---

<div align="center"><img height="300" alt="ezFoodie Logo" src="images/Logo.png"></div>

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Introduction

Welcome to ezFoodie’s User Guide! 🤩

Have you faced some issues when managing the members in your restaurant? ezFoodie is here!

ezFoodie is a desktop application that helps restaurants **keep track of their ever-growing list of members**. Restaurant managers and staffs can easily view and update member status (e.g. personal information, tier, reservation, transaction, etc.) to manage and analyze members.

ezFoodie is simple and user-friendly. It is optimized for using via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ezFoodie can get your member management tasks done faster than traditional GUI applications.

**Looks cool and interested?** 🤩 Continue reading on to explore the wonders of ezFoodie and enhance the way you manage and analyze your members.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer from [here](https://www.oracle.com/java/technologies/downloads/).

2. Download the latest `ezFoodie.jar` from [here](https://github.com/AY2122S1-CS2103T-F12-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ezFoodie.

4. Double-click the file to start the application. The GUI similar to the below should appear in a few seconds. Note how the application contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list -mem`** : Lists out a certain number of members, show top 50 records by default.

   * **`add -mem`**`-n John Doe -p 98765432 -e johndoe@gmail.com -a 112 Amoy Street, 069907, Singapore` : Adds a contact named `John Doe` to the member list.

   * **`del -mem`**`-id 3` : Deletes the member with member ID 3 shown in the current list.

   * **`add -txn`**`-b 110.00  -id 3` : add transaction with a bill $100.00 to member ID 3 shown in the current list.
   
   * **`redeem -f`**`100 -id 3` : redeem 100 point from id 3 shown in the current list.

   * **`clear`** : Clears the program.

   * **`exit`** : Exits the program.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `find -mem -n <NAME>`, `NAME` is a parameter which can be used as `find -mem -n John Doe`.

* Items in square brackets are optional.<br>
  e.g `list -mem [-pg <PAGE>]` can be used as `list -mem -pg 1` or as `list -mem`.

* The `list` and `find` are only show top 50 records by default, can use pagination `[-pg <PAGE>]` as optional value to choose different pages.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Meanings of shortcuts:

  * `-id`: member ID or transaction ID

  * `-row`: index number of the membet list

  * `-mem`: member
  
  * `-f`: redeem

  * `-n`: name

  * `-p`: phone

  * `-e`: email

  * `-a`: address

  * `-txn`: transaction

  * `-d`: date

  * `-b`: bill amount

  * `-pg`: page

</div>

### Viewing help : `help`

Opens a new window to show how to use the commands, and a link to the User Guide.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Listing out a certain number of members : `list -mem`

Lists out a certain number of members, show top 50 records by default.

Format: `list -mem [-pg <PAGE>]`

Example:
* `list -mem`
* `list -mem -pg 1`

### Adding a member : `add -m`

Adds new member to the member list.

Format: `add -mem -n <NAME> -p <PHONE> -e <EMAIL> -a <ADDRESS>`

Example:
* `add -mem -n John Doe -p 98765432 -e johndoe@gmail.com -a 112 Amoy Street, 069907, Singapore`

### Redeeming point from a member by id: `redeem -f -id`

Redeems point from a member by id.

Format: `redeem -f <point> -id <MEMBER_ID>`

Example:
* `redeem -f 100 -id 10006`

### Redeeming point from a member by index: `redeem -f -row`

Redeems point from a member by index.

Format: `redeem -f <point> -row <INDEX>`

Example:
* `redeem -f 100 -row 1`

### Finding members : `find -mem`

Finds members by different fields which contain any of the given keywords, show top 50 records by default.

* The find is case-insensitive. e.g `hans` will match `Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`

#### Finding member by member ID

Format: `find -mem -id <MEMBER_ID>`

Example:
* `find -mem -id 10001`

#### Finding members by name

Format: `find -mem -n <NAME> [-pg <PAGE>]`

Example: 
* `find -mem -n John Doe`
 
#### Finding members by phone

Format: `find -mem -p <PHONE> [-pg <PAGE>]`

Example: 
* `find -mem -p 98765432`

#### Finding members by email

Format: `find -mem -e <EMAIL> [-pg <PAGE>]`

Example: 
* `find -mem -e johndoe@gmail.com`

#### Finding members by registration date

Format: `find -mem -d <REGISTRATION_DATE> [-pg <PAGE>]`

Example: 
* `find -mem -d 12-01-2021`

### Viewing member profile : `show -mem`

Views member from member list by member ID.

Format: `show -mem -id <MEMBER_ID>`

Example:
* `show -mem -id 10001`

### Adding transaction for members : `add -t`

Adds transaction amount corresponding to member ID.

Format: `add -txn -id <MEMBER_ID> -b <BILLING>`

Example:
* `add -txn -id 10001 -b 200.00`

### Clearing the program : `clear`

Clears the program.

Format: `clear`

### Logging in as a manager : `login`

Logs in as a manager.

Format: `login <PASSWORD>`

Example:
* `login 123456`

### Logging out as a manager : `logout`

Logs out as a manager.

Format: `logout`

Example:
* `logout`

### Sorting members by credit : `sort -mem`

#### Sorting member by credit in ascending order

Format: `sort -mem -c -a`

Example:
* `sort -mem -c -a`

#### Sorting member by credit in descending order

Format: `sort -mem -c -d`

Example:
* `sort -mem -c -d`

### Editing a member : `edit -mem`

Edits different fields in a member's profile, where the member is specified by member ID.

#### Editing member name by member ID

Format: `edit -mem -id <MEMBER_ID> -n <NAME>`

Example:
* `edit -mem -id 10001 -n John Doe`

#### Editing member phone by member ID

Format: `edit -mem -id <MEMBER_ID> -p <PHONE>`

Example:
* `edit -mem -id 10001 -p 98765432`

#### Editing member email by member ID

Format: `edit -mem -id <MEMBER_ID> -e <EMAIL>`

Example:
* `edit -mem -id 10001 -e johndoe@gmail.com`

#### Editing member address by member ID

Format: `edit -mem -id <MEMBER_ID> -e <ADDRESS>`

Example:
* `edit -mem -id 10001 -a 33 Benoi Crescent, 629979, Singapore`

#### Editing member transaction by member ID

Format: `edit -mem -id <MEMBER_ID> -txn <TRANSACTION>`

Example:
* `edit -mem -id 10001 -txn 123.45`

#### Editing member name by index number

Format: `edit -mem -row <INDEX> -n <NAME>`

Example:
* `edit -mem -row 1 -n John Doe`

#### Editing member phone by index number

Format: `edit -mem -row <INDEX> -p <PHONE>`

Example:
* `edit -mem -row 1 -p 98765432`

#### Editing member email by index number

Format: `edit -mem -row <INDEX> -e <EMAIL>`

Example:
* `edit -mem -row 1 -e johndoe@gmail.com`

#### Editing member address by index number

Format: `edit -mem -row <INDEX> -e <ADDRESS>`

Example:
* `edit -mem -row 1 -a 33 Benoi Crescent, 629979, Singapore`

#### Editing member transaction by index number

Format: `edit -mem -row <INDEX> -txn <TRANSACTION>`

Example:
* `edit -mem -row 1 -txn 123.45`

### Deleting a member : `delete -mem`

#### Deleting member from member list by member ID

Format: `del -mem -id <MEMBER_ID>`

Example:
* `del -mem -id 10001`

#### Deleting member from member list by index number

Format: `del -mem -row <INDEX>`

Example:
* `del -mem -row 1`

### Saving the data

ezFoodie data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ezFoodie data are saved as a JSON file `[JAR file location]/data/ezFoodie.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ezFoodie will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v1.3]`

### Redeeming a member’s points `[coming in v1.3]`

### Marking reservation for member `[coming in v1.3]`

### Marking reservation for member `[coming in v1.3]`

### Editing reservation for member `[coming in v1.3]`

### Finding reservations by date `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**View Help** | `help`
**Exit Program** | `exit`
**List Members** | *Format* <br> `find -mem` <br> *Example* <br> `find -mem`
**Add New Member** | *Format* <br> `add -mem -n <NAME> -p <PHONE> -e <EMAIL> -a <ADDRESS>` <br> *Example* <br> `add -mem -n John Doe -p 98765432 -e johndoe@gmail.com -a 112 Amoy Street, 069907, Singapore`
**Find Members by Name** | *Format* <br> `find n <NAME>` <br> *Example* <br> `find -mem -n John Doe`
**Find Members by Phone** | *Format* <br> `find -mem -p <PHONE>` <br> *Example* <br> `find -mem -p 98765432`
**Find Members by Email** | *Format* <br> `find -mem -e <EMAIL>​` <br> *Example* <br> `find -mem -e johndoe@gmail.com`
**Find Members by Registration Date** | *Format* <br> `find -mem -d <REGISTRATION_DATE>` <br> *Example* <br> `find -mem -d 12-01-2021`
**Find Member by Member ID** | *Format* <br> `find -mem -id <MEMBER_ID>` <br> *Example* <br> `find -mem -id 10001`
**View Member Profile** | *Format* <br> `show -mem -id <MEMBER_ID>` <br> *Example* <br> `show -mem -id 10001`
**Add Transaction for Member** | *Format* <br> `add -txn -id <MEMBER_ID> -b <BILLING>` <br> *Example* <br> `add -txn -id 10001 -b 200.00`
**Clear Program** | `clear`
**Login as Manager** | *Format* <br> `login <PASSWORD>` <br> *Example* <br> `login 123456`
**Logout as Manager** | `logout`
**Sort Members by Credit in Ascending Order** | *Format* <br> `sort -mem -c -a` <br> *Example* <br> `sort -mem -c -a`
**Sort Members by Credit in Descending Order** | *Format* <br> `sort -mem -c -d` <br> *Example* <br> `sort -mem -c -d`
**Edit Member Name by Member ID** | *Format* <br> `edit -mem -id <MEMBER_ID> -n <NAME>` <br> *Example* <br> `edit -mem -id 10001 -n John Doe`
**Edit Member Phone by Member ID** | *Format* <br> `edit -mem -id <MEMBER_ID> -p <PHONE>` <br> *Example* <br> `edit -mem -id 10001 -p 98765432`
**Edit Member Email by Member ID** | *Format* <br> `edit -mem -id <MEMBER_ID> -e <EMAIL>` <br> *Example* <br> `edit -mem -id 10001 -e johndoe@gmail.com`
**Edit Member Address by Member ID** | *Format* <br> `edit -mem -id <MEMBER_ID> -a <ADDRESS>` <br> *Example* <br> `edit -mem -id 10001 -a 33 Benoi Crescent, 629979, Singapore`
**Edit Member Transaction by Member ID** | *Format* <br> `edit -mem -id <MEMBER_ID> -txn <TRANSACTION>` <br> *Example* <br> `edit -mem -id 10001 -txn 123.45`
**Edit Member Name by Index Number** | *Format* <br> `edit -mem -row <INDEX> -n <NAME>` <br> *Example* <br> `edit -mem -row 1 -n John Doe`
**Edit Member Phone by Index Number** | *Format* <br> `edit -mem -row <INDEX> -p <PHONE>` <br> *Example* <br> `edit -mem -row 1 -p 98765432`
**Edit Member Email by Index Number** | *Format* <br> `edit -mem -row <INDEX> -e <EMAIL>` <br> *Example* <br> `edit -mem -row 1 -e johndoe@gmail.com`
**Edit Member Address by Index Number** | *Format* <br> `edit -mem -row <INDEX> -a <ADDRESS>` <br> *Example* <br> `edit -mem -row 1 -a 33 Benoi Crescent, 629979, Singapore`
**Edit Member Transaction by Index Number** | *Format* <br> `edit -mem -row <INDEX> -txn <TRANSACTION>` <br> *Example* <br> `edit -mem -row 1 -txn 123.45`
**Delete Member by Member ID** | *Format* <br> `del -mem -id <MEMBER_ID>` <br> *Example* <br> `del -mem -id 10001`
**Delete Member by Index Number** | *Format* <br> `del -mem -row <INDEX>` <br> *Example* <br> `del -mem -row 1`

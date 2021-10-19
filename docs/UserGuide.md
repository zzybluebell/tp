---
layout: page
title: User Guide
---

<div align="center"><img height="300" alt="ezFoodie Logo" src="images/ezFoodieLogo.png"></div>

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

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ezFoodie.jar` from [here](https://github.com/AY2122S1-CS2103T-F12-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ezFoodie.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list -m`** : Lists out a certain number of members, show top 50 records by default.

   * **`add -m`**`-n John Doe -p 98765432 -e johndoe@gmail.com` : Adds a contact named `John Doe` to the member list.

   * **`del -m`**`-id 3` : Deletes the member with member ID 3 shown in the current list.

   * **`clear`** : Clears the program.

   * **`exit`** : Exits the program.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `find -m -n <NAME>`, `NAME` is a parameter which can be used as `find -m -n John Doe`.

* Items in square brackets are optional.<br>
  e.g `list -m [-pg <PAGE>]` can be used as `list -m -pg 1` or as `list -m`.

* The `list` and `find` are only show top 50 records by default, can use pagination `[-pg <PAGE>]` as optional value to choose different pages.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Meanings of shortcuts:

  * `-m`: member

  * `-n`: name

  * `-p`: phone

  * `-e`: email

  * `-t`: transaction

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

### Listing out a certain number of members : `list -m`

Lists out a certain number of members, show top 50 records by default.

Format: `list -m [-pg <PAGE>]`

Example:
* `list -m`
* `list -m -pg 1`

### Adding a member : `add -m`

Adds new member to the member list.

Format: `add -m -n <NAME> -p <PHONE> -e <EMAIL>`

Example:
* `add -m -n John Doe -p 98765432 -e johndoe@gmail.com`

### Finding members : `find -m`

Finds members by different fields which contain any of the given keywords, show top 50 records by default.

* The find is case-insensitive. e.g `hans` will match `Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`

#### Finding member by member ID

Format: `find -m -id <MEMBER_ID>`

Example:
* `find -m -id 10001`

#### Finding members by name

Format: `find -m -n <NAME> [-pg <PAGE>]`

Example: 
* `find -m -n John Doe`
 
#### Finding members by phone

Format: `find -m -p <PHONE> [-pg <PAGE>]`

Example: 
* `find -m -p 98765432`

#### Finding members by email

Format: `find -m -e <EMAIL> [-pg <PAGE>]`

Example: 
* `find -m -e johndoe@gmail.com`

#### Finding members by registration date

Format: `find -m -d <REGISTRATION_DATE> [-pg <PAGE>]`

Example: 
* `find -m -d 12-01-2021`

### Viewing member profile : `view -m`

Views member from member list by member ID.

Format: `show -m -id <MEMBER_ID>`

Example:
* `show -m -id 10001`

### Adding transaction for members : `add -t`

Adds transaction amount corresponding to member ID.

Format: `add -t -id <MEMBER_ID> -b <BILLING>`

Example:
* `add -t -id 10001 -b 200.00`

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

### Sorting members by credit : `sort -m`

#### Sorting member by credit in ascending order

Format: `sort -m -c -a`

Example:
* `sort -m -c -a`

#### Sorting member by credit in descending order

Format: `sort -m -c -d`

Example:
* `sort -m -c -d`

### Editing a member : `edit -m`

Edits different fields in a member's profile, where the member is specified by member ID.

#### Editing member name by member ID

Format: `edit -m -id <MEMBER_ID> -n <NAME>`

Example:
* `edit -m -id 10001 -n John Doe`

#### Editing member phone by member ID

Format: `edit -m -id <MEMBER_ID> -p <PHONE>`

Example:
* `edit -m -id 10001 -p 98765432`

#### Editing member email by member ID

Format: `edit -m -id <MEMBER_ID> -e <EMAIL>`

Example:
* `edit -m -id 10001 -e johndoe@gmail.com`

#### Editing member address by member ID

Format: `edit -m -id <MEMBER_ID> -e <ADDRESS>`

Example:
* `edit -m -id 10001 -a 33 Benoi Crescent, 629979, Singapore`

#### Editing member transaction by member ID

Format: `edit -m -id <MEMBER_ID> -t <TRANSACTION>`

Example:
* `edit -m -id 10001 -t 123.45`

#### Editing member name by index number

Format: `edit -m -r <INDEX> -n <NAME>`

Example:
* `edit -m -r 1 -n John Doe`

#### Editing member phone by index number

Format: `edit -m -r <INDEX> -p <PHONE>`

Example:
* `edit -m -r 1 -p 98765432`

#### Editing member email by index number

Format: `edit -m -r <INDEX> -e <EMAIL>`

Example:
* `edit -m -r 1 -e johndoe@gmail.com`

#### Editing member address by index number

Format: `edit -m -r <INDEX> -e <ADDRESS>`

Example:
* `edit -m -r 1 -a 33 Benoi Crescent, 629979, Singapore`

#### Editing member transaction by index number

Format: `edit -m -r <INDEX> -t <TRANSACTION>`

Example:
* `edit -m -r 1 -t 123.45`

### Deleting a member : `delete -m`

#### Deleting member from member list by member ID

Format: `del -m -id <MEMBER_ID>`

Example:
* `del -m -id 10001`

#### Deleting member from member list by index number

Format: `del -m -r <INDEX>`

Example:
* `del -m -r 1`

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
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**View Help** | `help`
**Exit Program** | `exit`
**List Members** | *Format* <br> `find -m` <br> *Example* <br> `find -m`
**Add New Member** | *Format* <br> `add -m -n <NAME> -p <PHONE> -e <EMAIL>` <br> *Example* <br> `add -m -n John Doe -p 98765432 -e johndoe@gmail.com`
**Find Members by Name** | *Format* <br> `find n <NAME>` <br> *Example* <br> `find -m -n John Doe`
**Find Members by Phone** | *Format* <br> `find -m -p <PHONE>` <br> *Example* <br> `find -m -p 98765432`
**Find Members by Email** | *Format* <br> `find -m -e <EMAIL>​` <br> *Example* <br> `find -m -e johndoe@gmail.com`
**Find Members by Registration Date** | *Format* <br> `find -m -d <REGISTRATION_DATE>` <br> *Example* <br> `find -m -d 12-01-2021`
**Find Member by Member ID** | *Format* <br> `find -m -id <MEMBER_ID>` <br> *Example* <br> `find -m -id 10001`
**View Member Profile** | *Format* <br> `show -m -id <MEMBER_ID>` <br> *Example* <br> `show -m -id 10001`
**Add Transaction for Member** | *Format* <br> `add -t -id <MEMBER_ID> -b <BILLING>` <br> *Example* <br> `add -t -id 10001 -b 200.00`
**Clear Program** | `clear`
**Login as Manager** | *Format* <br> `login <PASSWORD>` <br> *Example* <br> `login 123456`
**Logout as Manager** | `logout`
**Sort Members by Credit in Ascending Order** | *Format* <br> `sort -m -c -a` <br> *Example* <br> `sort -m -c -a`
**Sort Members by Credit in Descending Order** | *Format* <br> `sort -m -c -d` <br> *Example* <br> `sort -m -c -d`
**Edit Member Name by Member ID** | *Format* <br> `edit -m -id <MEMBER_ID> -n <NAME>` <br> *Example* <br> `edit -m -id 10001 -n John Doe`
**Edit Member Phone by Member ID** | *Format* <br> `edit -m -id <MEMBER_ID> -p <PHONE>` <br> *Example* <br> `edit -m -id 10001 -p 98765432`
**Edit Member Email by Member ID** | *Format* <br> `edit -m -id <MEMBER_ID> -e <EMAIL>` <br> *Example* <br> `edit -m -id 10001 -e johndoe@gmail.com`
**Edit Member Address by Member ID** | *Format* <br> `edit -m -id <MEMBER_ID> -a <ADDRESS>` <br> *Example* <br> `edit -m -id 10001 -a 33 Benoi Crescent, 629979, Singapore`
**Edit Member Transaction by Member ID** | *Format* <br> `edit -m -id <MEMBER_ID> -t <TRANSACTION>` <br> *Example* <br> `edit -m -id 10001 -t 123.45`
**Edit Member Name by Index Number** | *Format* <br> `edit -m -r <INDEX> -n <NAME>` <br> *Example* <br> `edit -m -r 1 -n John Doe`
**Edit Member Phone by Index Number** | *Format* <br> `edit -m -r <INDEX> -p <PHONE>` <br> *Example* <br> `edit -m -r 1 -p 98765432`
**Edit Member Email by Index Number** | *Format* <br> `edit -m -r <INDEX> -e <EMAIL>` <br> *Example* <br> `edit -m -r 1 -e johndoe@gmail.com`
**Edit Member Address by Index Number** | *Format* <br> `edit -m -r <INDEX> -a <ADDRESS>` <br> *Example* <br> `edit -m -r 1 -a 33 Benoi Crescent, 629979, Singapore`
**Edit Member Transaction by Index Number** | *Format* <br> `edit -m -r <INDEX> -t <TRANSACTION>` <br> *Example* <br> `edit -m -r 1 -t 123.45`
**Delete Member by Member ID** | *Format* <br> `del -m -id <MEMBER_ID>` <br> *Example* <br> `del -m -id 10001`
**Delete Member by Index Number** | *Format* <br> `del -m -r <INDEX>` <br> *Example* <br> `del -m -r 1`

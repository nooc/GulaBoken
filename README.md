# GulaBoken

## About

A Java school assignment by [Ben Bright](https://github.com/nooc) and [Simon Nilsson](https://github.com/nilsson46).

In this assignment we implemented a console application in Java by following the requirement specifications in 'Gula boken.pdf' describing a simple phone book application with CRUD and search functionality.

## Project timeline

Assign spans from 2022-10-31 to 2022-11-11. See [Gantt](https://funet-my.sharepoint.com/:x:/g/personal/yhmu22_brigbe_folkuniversitetet_nu/EYg-zcBuNsRJoNPP4Lg3ivsB6WGiVKnSDK5YETkW8Z__3Q?e=23xoOA) on sharepoint.

## Usage

### Main Menu

The main menu looks has the following look:

    ----------------------------------------
    Yellow Book - Main Menu
        Hello guest
    ----------------------------------------
        COMMANDS
    freesearch KEYWORD...
    search "name"|"surname"|"street" VALUE
    add
    login USERNAME PASSWORD
    quit
    ----------------------------------------
    >

Searching it the data is performed as a case-insensitive sub-string search, ie. 'lo' will match 'HelLo'.

#### free

Free search takes one or more keywords and will return entries matching all keywords.

Example:

    > free be
    ----------------------------------------
    Yellow Book - Search Results
        Hello guest
    ----------------------------------------
    103: Bengt Bengtsson
    107: Marit Jernberg
    105: Bengt Bengtsson
        COMMANDS
    show ID
    back
    ----------------------------------------
    >

The above matches all contacts containing the keyword "be". If we made the search "free be jer" then only entry 107 would be returned as 103 and 105 do not contain "jer".

#### search

The search command will only search over one of the specified properties name, surname or street and will only take one keyword.

Example:

    > search name beng
    ----------------------------------------
    Yellow Book - Search Results
        Hello guest
    ----------------------------------------
    103: Bengt Bengtsson
    105: Bengt Bengtsson
        COMMANDS
    show ID
    login USERNAME PASSWORD
    back
    ----------------------------------------
    >

Login and logout

#### add

Add will 

#### login

Log in as administrator to get access to update and delete.

Example:

    Yellow Book - Main Menu
        Hello guest

    ...

    > login admin secret
    User admin logged in.
    ----------------------------------------
    Yellow Book - Main Menu
        Hello admin

    ...

    ----------------------------------------
    > freesearch beng
    ----------------------------------------
    Yellow Book - Search Results
        Hello admin
    ----------------------------------------
    105: Bengt Bengtsson
    103: Bengt Bengtsson
        COMMANDS
    show ID
    update ID
    delete ID
    back
    ----------------------------------------
    >

#### quit

This will simply quit the application.

Example:

		> quit






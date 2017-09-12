Beta Version:
V> 0.9.0
Added full support for maging characters.
Added commands for understanding the app.
Added commands for create, update, reset characters. (Comparision will be added soon)
Added JAR of the app, Runnable version of jar going to be added soon.
You still can run the application locally.


Aplha version:

V> 0.8.0
Version so far supports all the character formulas for calculate all statistics from the first admin post with formulas.
Calculator doest contemplate yet, Base values like
Base Damage
Base ASR
Base DSR
Health
Health Per Level
Base Health
Base Attack Speed

for example, if you create an elf in lvl 1 it already has 30 Attack Speed.
So when you run the calculator for a elf lvl 1 the attack speed will be 0.

Added Stand alone JAR wich only allows to run the full tests of mu.characters

For running the app open a terminal (Home + R) or go to desktop home and type CMD

cd <to/the/jar/binaries/last_version_number>
java -jar mu_stat_calculator.jar

Must be executed under Java 7.

<h1>Usage</h1>

All commands works the same way, you write a command and then using '-' you indicate a parameter you wanna introduce.
only supported param will be executed. this program is not anti-monkeys-with-knife so respect the rules.
Eg. create -character=BK -level:200
Here we can read the command <b>create</b> and two arguments <b>character</b> and <b>level</b>.
Notice all parameters starts with '-' then 'argument name' then '=' then the value.

<b>Most important thing to know is the words Case, they matter, its not the same listCommands than listcommand</b>

For knowing a list of commands type: "listCommands"

For more specific info of a specific command type: "explainCommand -commandName=COMMAND_NAME"

----------------------------------------------------------------------------------------

<h2>List the ID of each class type</h2>
Display a list of class type id.

Write "listCharactersTypes"

<h2>Creating a character</h2>
Creates and save a new Character.

Write "create -character=BK -level=400 -str=50% -agi=25% -vit=20% -ene=100"

-character: this parameter determines wich class will create, use "listCharactersTypes" to see the rest class ID.
-level: determines wich level your char will be created.
-str, agi. vit, ene, com: Determines how many points will be added to that statistic, if the numbers contains '%' to the
    right, it will add the porcentaje based in the total amount of points for the current level if not have '%' then will
    set se exact amount to the stat.

<h2>Updating a character</h2>

Write "update -id=0 -ene=15 -vit=463"

id: Is the id of your already created character, use "listCreatedCharacters" for see the id and all relevant info
of all saved characters.

-str: agi. vit, ene, com: Idem create.

-level: idem create

NOTE: Character type cannot be modified.

<h2>Reseting a character</h2>
Basically rollback stats initial state

Write "resetCharacter -id=0"

id: Is the id of your already created character, use "listCreatedCharacters" for see the id and all relevant info
of all saved characters.

<h2>Listing all characters</h2>
Will show all the saved charaacters.

Write "listCreatedCharacters"
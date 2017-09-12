package ui.console

import mu.app.Config
import mu.app.Logger
import mu.app.exception.MSCException
import mu.characters.Character
import mu.characters.data.CharacterData

class ConsoleApp {


    static cache = []

    static appCommandEvents = [
        //App Commands
        listCommands : [
            event : { args, commandData ->
                ConsoleApp.write( "App commands \n-${getCommandList().join("\n-")}" )
            },
            args : [:],
            description : "Shows a list of available commands."
        ],

        explainCommand : [
            event : { args, commandData ->
                def explainCommandData = appCommandEvents."${args.commandName}"
                def fullExplaniation = " -> Command ${args.commandName}: ${explainCommandData.description}. \n Arguments: \n"
                if( explainCommandData.args ){
                    explainCommandData.args.each{ arg, argData ->
                        fullExplaniation += "-$arg Data: $argData \n"
                    }
                }else{
                    fullExplaniation += "No argument required."
                }
                ConsoleApp.write( fullExplaniation )
            },
            args : [ commandName : [ rules : [ParameterRule.REQUIRED]] ],
            description : "Shows how to use specific command."
        ],

        listCharactersTypes : [
            event : { args, commandData ->
                ConsoleApp.write( CharacterData.getCharacterIdList().join(", ") )
            },
            args : [:],
            description : "Shows how to use specific command."
        ],

        listCreatedCharacters : [
            event : { args, commandData ->
                if (cache) {
                    cache.eachWithIndex { index, character -> ConsoleApp.write(character.toString()) }
                } else {
                    ConsoleApp.write("There is still no characters created. Write. 'explainCommand -commandName=create' for instructions.")
                }
            },
            args : [:],
            description : "Shows how to use specific command."
        ],

        create : [
            event : { args, commandData ->
                def hasErrorInArgs = validateEventArgs(args, commandData.args)
                if( ! hasErrorInArgs ){
                    def level = (args?.level ?: 1) as Integer
                    def version = args?.version ?: CharacterData.CHARACTER_VERSION_DEFAULT
                    def newChar = Character.newFromVersion( args.character, level, version)

                    args.remove("character")
                    args.remove("level")
                    args.remove("version")
                    args.remove("id")

                    def charParsedListData = suitCharacterParameters( args )

                    charParsedListData.each { parsedData ->
                        parsedData.each{ attr, Object[] values->
                            Closure setAttr = newChar.&"$attr"
                            setAttr.call(values)
                        }
                    }

                    cache << newChar
                    newChar.id = cache.size() - 1;

                    ConsoleApp.write( "The character has been sucessfully created. \n\n ${newChar}" )
                } else {
                    ConsoleApp.write( "The command does not accomplish the requirements.\n -" + hasErrorInArgs.join("\n -"))
                }
            },
            args : [
                (CharacterData.CHARACTER_ID_TYPE) : [
                    rules : [ParameterRule.REQUIRED, ParameterRule.IN_LIST.setList( CharacterData.getCharacterIdList() )],
                    description : ["The Identificator for the character you wanna create."]
                ],
                (CharacterData.CHARACTER_LEVEL) : [
                    rules: [],
                    description: "A numeric value for character level."
                ],
                version : [
                    rules: [],
                    description: "The name of supported version character formulas/stats etc."
                ],
                (CharacterData.CHARACTER_STAT_STR) : [
                    rules: [],
                    description: "A numeric value for add strneght, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_AGI) : [
                    rules: [],
                    description: "A numeric value for add agility, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_VIT) : [
                    rules: [],
                    description: "A numeric value for add vitality, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_ENE) : [
                    rules: [],
                    description: "A numeric value for add energy, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_COM) : [
                    rules: [],
                    description: "A numeric value for add command (Only for DL), can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ]
            ],
            description : "Creates a new character based in the input parameters."
        ],

        update : [
            event : { Map args, commandData ->
                def hasErrorInArgs = validateEventArgs(args, commandData.args, [
                        (CharacterData.CHARACTER_ID) : { val ->
                            def errors = []
                            if( (val as Integer) >= cache.size()){
                                errors << "Doesn't exit a character with that id."
                            }
                            errors
                        }
                    ]
                )
                if( ! hasErrorInArgs ){

                    def editableStat = CharacterData.getCharacterStatsList() + [CharacterData.CHARACTER_LEVEL]

                    def charId = args.id as Integer;
                    args = args.findAll { k, v ->
                        k in editableStat
                    }

                    def updateChar = cache.get( charId )
                    def charParsedListData = suitCharacterParameters( args )

                    charParsedListData.each { parsedData ->
                        parsedData.each{ attr, Object[] values->
                            Closure setAttr = updateChar.&"$attr"
                            setAttr.call(values)
                        }
                    }

                    ConsoleApp.write( "The character has been sucessfully updated. \n\n ${updateChar}" )
                } else {
                    ConsoleApp.write( "The command does not accomplish the requirements.\n -" + hasErrorInArgs.join("\n -"))
                }
            },
            args : [
                (CharacterData.CHARACTER_ID) : [
                    rules : [ParameterRule.REQUIRED, ParameterRule.NUMERIC],
                    description: "The numeric id of the character you wanna update"
                ],
                (CharacterData.CHARACTER_LEVEL) : [
                    rules: [],
                    description: "A numeric value for character level."
                ],
                (CharacterData.CHARACTER_STAT_STR) : [
                    rules: [],
                    description: "A numeric value for add strneght, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_AGI) : [
                    rules: [],
                    description: "A numeric value for add agility, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_VIT) : [
                    rules: [],
                    description: "A numeric value for add vitality, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_ENE) : [
                    rules: [],
                    description: "A numeric value for add energy, can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ],
                (CharacterData.CHARACTER_STAT_COM) : [
                    rules: [],
                    description: "A numeric value for add command (Only for DL), can add fixed numbers like 200 or porcentual 50% based on max stats count for the current char level."
                ]
            ],
            description : "Updates character stats based in the input parameters."
        ],

        resetCharacter : [
            event : { args, commandData ->
                def hasErrorInArgs = validateEventArgs(args, commandData.args, [
                    (CharacterData.CHARACTER_ID) : { val ->
                        def errors = []
                        if( (val as Integer) >= cache.size()){
                            errors << "Doesn't exit a character with that id."
                        }
                        errors
                    }
                ])

                if( ! hasErrorInArgs ){
                    def charId = args.id as Integer
                    Character character = cache.get( charId )
                    character.resetStats()

                    ConsoleApp.write( "The character has been sucessfully reseted. \n\n ${character}" )
                } else {
                    ConsoleApp.write( "The command does not accomplish the requirements.\n -" + hasErrorInArgs.join("\n -"))
                }


            },
            args : [
                (CharacterData.CHARACTER_ID) : [
                    rules : [ParameterRule.REQUIRED, ParameterRule.NUMERIC],
                    description: "The numeric id of the character you wanna update"
                ],
            ],
            description : "Rollbacks a character statistics to default."
        ],
    ]

    /**
     * Converts characters parameters into reliable character's functions.
     */
    static suitCharacterParameters( args ){
        def parsedParams = []
        args.each { param, value ->
            switch (param){
                case CharacterData.CHARACTER_STAT_STR :
                case CharacterData.CHARACTER_STAT_AGI :
                case CharacterData.CHARACTER_STAT_VIT :
                case CharacterData.CHARACTER_STAT_ENE :
                case CharacterData.CHARACTER_STAT_COM :
                    def fixed = true
                    if(value.indexOf("%") > 0){
                        value = value.replace("%", "") as Integer
                        fixed = false
                    }else{
                        value = value as Integer
                    }
                    parsedParams << ["setStat" : [ param, value, fixed ] ]
                    break;
                default: Logger.debug("suitCharacterParameters, un mapped attribute: ${param}")
            }
        }
        parsedParams
    }

    /**
     * Checks the rules of arguments of an event.
     *
     * @param args - Input args
     * @param argsData - Args data with rules.
     * @param flexRules - Must be a map with Map<<argName>, <Closure>>,
     *      closure must return an error string or empty array if no errors.
     *
     * @return a list of readable errors. or empty list of no errors found.
     */
    static validateEventArgs(args, argsData, flexRules = [:]){
        def errors = []
        argsData.each{ argName, argDetails ->
            def validateValue = args?."${argName}"
            argDetails.rules.each{ ruleType ->
                if( !verifyRule(validateValue, ruleType) ){
                    errors << "Parameter '${argName}' fails for the rule: ${ruleType} ."
                    return;
                }
            }

            if(!errors && flexRules){
                if(argName in flexRules.keySet()){
                    def resultFlex = flexRules."$argName"(validateValue)
                    errors = resultFlex
                }
            }
        }

        errors
    }

    /**
     * Executes the validation of a specific rule for a value.
     *
     * @param arg
     * @param ruleType - Any of {@link ParameterRule}
     * @return
     */
    static verifyRule(arg, ruleType){
        switch (ruleType){
            case ParameterRule.REQUIRED: return arg ? true : false
            case ParameterRule.NUMERIC: return arg?.matches( /^[0-9]*$/ )
            case ParameterRule.ALFANUMERIC: return arg?.matches( /^\w+$/ )
            case ParameterRule.IN_LIST: return arg in ruleType.wildcard
        }
    }

    /**
     * Returns the list of app capable to execute.
     *
     * @return a list.
     */
    static getCommandList(){
        appCommandEvents.keySet()
    }

    /**
     * Checks if an inputCommand is valid.
     *
     * @param inputCommand
     *
     * @return true if valid command, false otherwise.
     */
    static isValidCommand( String inputCommand ){
        inputCommand in getCommandList()
    }

    /**
     * Parse a straight command from the console.
     *
     * @param input
     *
     * @return a map with the command and parameters
     */
    static parseCommand( String input ){
        def parts = input.split("-")
        def command = parts[0].trim()
        def parameters = [:]

        if(parts.size() > 1){
            parts.eachWithIndex { part, index ->
                if ( index > 0 ){
                    def attrVal = part.trim().split("=")
                    def key = attrVal[0].trim()
                    def value = attrVal[1].trim()
                    parameters << [ (key) : value ]
                }
            }
        }

        [command : command, parameters : parameters]
    }

    /**
     * Ask an input to the user.
     *
     * @param askFor
     * @return
     */
    static def ask( askFor = "Waiting Input" ){
        if( askFor ){
            write( askFor )
        }

        System.in.newReader().readLine()
    }

    /**
     * Writes an message to the console.
     *
     * @param message
     * @return
     */
    static write( message ){
        println( message )
    }

    /**
     * Executes a command.
     *
     * @param command
     * @param args
     * @param closure
     * @return
     */
    static trigger(command, args, closure = {}){
        try{
            def commandData = appCommandEvents."$command"
            commandData.event(args, commandData)
            closure()
        }catch (Exception e){
            Logger.debug( MSCException.getStringTrace(e) )
        }
    }

    /**
     * App main listener
     */
    static void start() {
        String input

        def firstInput = true
        while ( firstInput || (input = ConsoleApp.ask( "Waiting for command." )) || input == "" ){
            def inputCommand
            try{
                if(!firstInput){
                    inputCommand = ConsoleApp.parseCommand( input )

                    if( ConsoleApp.isValidCommand( inputCommand.command ) ){
                        ConsoleApp.trigger(inputCommand.command, inputCommand.parameters)
                    } else {
                        ConsoleApp.write( "The command is invalid. Write 'listCommands' for show a list of commands." )
                    }

                    if( input == "quit" ){
                        ConsoleApp.trigger("quit", [:], {
                            input = false
                            ConsoleApp.write( "bye.")
                        })
                    }
                }
            }catch (Exception e){
                ConsoleApp.write( "Unspected error while executing your command. Verify the syntaxis. write 'explainComman <command> for more details.'" )
                Logger.debug( MSCException.getStringTrace(e) )
            }
            firstInput = false
        }
    }
}

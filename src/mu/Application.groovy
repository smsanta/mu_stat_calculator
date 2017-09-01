package mu

import mu.characters.BK
import mu.characters.CharacterData
import mu.characters.ELF
import mu.characters.config.version.beta_first.CharacterConfig

class Application {

    static def characterVersion = "beta_first"

    static void main(def args){
        //BkTests()
        ElfTests()


    }

    static void ElfTests() {
        ELF character = ELF.newInstance( CharacterConfig.CHARACTERS_VALUATIONS.ELF.INITIAL_STATS )
        println( character.toString() )

        character.addStat( CharacterData.CHARACTER_LEVEL, 16 )
        character.addStat( CharacterData.CHARACTER_STAT_AGI, 75 )
        println( character.toString() )

    }

    static def BkTests() {
        def charConfig = Class.forName("mu.characters.config.version.${characterVersion}.CharacterConfig").newInstance()

        BK character = BK.newInstance( charConfig.CHARACTERS_VALUATIONS.BK.INITIAL_STATS )

        [1,2,3,100,220,221,300,400].each { it ->
            character.level = it

            println( "Your are level: ${character.level} and must have ${character.calculatePointsForCurrentLevel()} points for distribute. " +
                    "Spent points so far: ${character.calculateSpentPoints()} " +
                    "Defence: ${character.calculateDefense()}")
        }

        println("Attribute check: ${character.checkAttributes()}" )

        println( character.getCharacterConfig() )
        println( character.getDamageMultiplierValuation() )
        println( character.getDefenseValuation() )
        println( character.getDefenseSuccessRateValuation() )
        println( character.getSpeedValuation() )
        println( character.calculateDamageMultiplier() )
    }
}

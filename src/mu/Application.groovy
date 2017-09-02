package mu

import mu.characters.BK
import mu.characters.Character
import mu.characters.data.CharacterData
import mu.characters.ELF

class Application {

    static def characterVersion = "beta_first"

    static void main(def args){
        fullTest()
    }

    static void fullTest(){
        [
            CharacterData.CHARACTER_ID_BK,
            CharacterData.CHARACTER_ID_SM,
            CharacterData.CHARACTER_ID_ELF,
            CharacterData.CHARACTER_ID_MG,
            CharacterData.CHARACTER_ID_DL,
            CharacterData.CHARACTER_ID_SU,
            CharacterData.CHARACTER_ID_RF
        ].each { characterId ->
            def charConfig = Class.forName("mu.characters.config.version.${characterVersion}.CharacterConfig").newInstance()
            def charClass = Class.forName("mu.characters.${characterId}")
            Character character = charClass.newInstance( charConfig.CHARACTERS_VALUATIONS."${characterId}".INITIAL_STATS )

            character.addStat( CharacterData.CHARACTER_LEVEL, 399 )
            if(character.getCharId().is( CharacterData.CHARACTER_ID_DL)){
                character.autoSpendPoints(20, 20, 20, 20, 20)
            }else{
                character.autoSpendPoints(25,25,25,25)
            }

            println( character.toString() )
        }
    }

    static void ElfTests() {
        def charConfig = Class.forName("mu.characters.config.version.${characterVersion}.CharacterConfig").newInstance()
        ELF character = ELF.newInstance( charConfig.CHARACTERS_VALUATIONS.ELF.INITIAL_STATS )
        println( character.toString() )

        character.addStat( CharacterData.CHARACTER_LEVEL, 16 )
        character.addStat( CharacterData.CHARACTER_STAT_AGI, 75 )
        println( character.toString() )

    }

    static def BkTests() {
        def charConfig = Class.forName("mu.characters.config.version.${characterVersion}.CharacterConfig").newInstance()

        [1,2,3,100,220,221,300,400].each { it ->
            BK character = BK.newInstance( charConfig.CHARACTERS_VALUATIONS.BK.INITIAL_STATS )
            character.level = it
            character.autoSpendPoints(50, 30, 10, 10)

            println( character )
        }
    }
}

package mu

import mu.characters.BK
import mu.characters.CharacterData
import mu.characters.ELF
import mu.characters.config.version.beta_first.CharacterConfig

class Application {

    static def characterVersion = "beta_first"

    static void main(def args){
        BkTests()
        //ElfTests()


    }

    static void ElfTests() {
        ELF character = ELF.newInstance( CharacterConfig.CHARACTERS_VALUATIONS.ELF.INITIAL_STATS )
        println( character.toString() )

        character.addStat( CharacterData.CHARACTER_LEVEL, 16 )
        character.addStat( CharacterData.CHARACTER_STAT_AGI, 75 )
        println( character.toString() )

        //OUTPUT
        /*
        ELF lvl: 1, Stats: [str:22, agi:25, vit:20, ene:15] - Points to spend: 0, already spent: 0.
        Defense 3, Defense Success Rate: PVP: 4 PVM: 6
        Attack Speed: 0
        Maic Damage: [min:1, max:3]
        Physical Damage: [min:5, max:11] - Damage Success Rate: -1
        Bow Damage: [min:6, max:16]

        ELF lvl: 17, Stats: [str:22, agi:100, vit:20, ene:15] - Points to spend: 80, already spent: 75.
        Defense 14, Defense Success Rate: PVP: 44 PVM: 25
        Attack Speed: 2
        Maic Damage: [min:1, max:3]
        Physical Damage: [min:15, max:30] - Damage Success Rate: -1
        Bow Damage: [min:14, max:31]

         */

    }

    static def BkTests() {
        def charConfig = Class.forName("mu.characters.config.version.${characterVersion}.CharacterConfig").newInstance()



        [1,2,3,100,220,221,300,400].each { it ->
            BK character = BK.newInstance( charConfig.CHARACTERS_VALUATIONS.BK.INITIAL_STATS )
            character.level = it
            character.autoSpendPoints(50, 30, 10, 10)

            println( character )
        }

        //OUTPUT
        /*
            Your are level: 1 and must have 0 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 2 and must have 5 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 3 and must have 10 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 100 and must have 495 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 220 and must have 1095 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 221 and must have 1101 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 300 and must have 1575 points for distribute. Spent points so far: 0 Defence: 6
            Your are level: 400 and must have 2175 points for distribute. Spent points so far: 0 Defence: 6
            Attribute check: [No problems detected.]
            [POINTS_PER_LEVEL:[[POINTS:5, FROM_LEVEL:1, TO_LEVEL:220], [POINTS:6, FROM_LEVEL:220, TO_LEVEL:400]], INITIAL_STATS:[str:28, agi:20, vit:25, ene:10], DAMAGE_MULTIPLIER:[MULTIPLIER:10, APPLIES_ON:ene], DEFENSE:[MULTIPLIER:3, APPLIES_ON:agi], DEFENSE_SUCCESS_RATE:[PVM:[MULTIPLIER:3, APPLIES_ON:agi], PVP:[LEVEL_MULTIPLIER:2, LEVEL_MULTIPLIER_CONTROL:1, MULTIPLIER:2, APPLIES_ON:agi]], SPEED:[MULTIPLIER:20, APPLIES_ON:agi, BASE:30]]
            [MULTIPLIER:10, APPLIES_ON:ene]
            [MULTIPLIER:3, APPLIES_ON:agi]
            null
            [MULTIPLIER:20, APPLIES_ON:agi, BASE:30]
            1
         */
    }
}

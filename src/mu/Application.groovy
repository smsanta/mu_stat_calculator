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

    /* FULL TEST OUTPUT
         BK Level: 400, Stats: [str:571, agi:563, vit:568, ene:553] - Points to spend: 2175, already spent: 2172.
        Defense 187, Defense Success Rate: PVP: 1081 PVM: 187
        Attack Speed: 28
        Maic Damage: [min:79, max:184]
        Physical Damage: [min:95, max:285] - Attack Success Rate: PVP: 3733 PVM: 2987
        Damage Multiplier: 55

        SM Level: 400, Stats: [str:561, agi:561, vit:558, ene:573] - Points to spend: 2175, already spent: 2172.
        Defense 140, Defense Success Rate: PVP: 940 PVM: 187
        Attack Speed: 56
        Maic Damage: [min:95, max:191]
        Physical Damage: [min:93, max:140] - Attack Success Rate: PVP: 3444 PVM: 2981

        ELF Level: 400, Stats: [str:565, agi:568, vit:563, ene:558] - Points to spend: 2175, already spent: 2172.
        Defense 81, Defense Success Rate: PVP: 856 PVM: 142
        Attack Speed: 11
        Maic Damage: [min:62, max:111]
        Physical Damage: [min:188, max:188] - Attack Success Rate: PVP: 1540 PVM: 2993
        Bow Damage: [min:62, max:111]

        MG Level: 400, Stats: [str:724, agi:724, vit:724, ene:724] - Points to spend: 2793, already spent: 2792.
        Defense 181, Defense Success Rate: PVP: 981 PVM: 241
        Attack Speed: 36
        Maic Damage: [min:144, max:362]
        Physical Damage: [min:225, max:482] - Attack Success Rate: PVP: 4096 PVM: 3267

        DL Level: 400, Stats: [str:584, agi:578, vit:578, ene:573, com:583] - Points to spend: 2793, already spent: 2207.
        Defense 96, Defense Success Rate: PVP: 944 PVM: 82
        Attack Speed: 28
        Maic Damage: [min:71, max:191]
        Physical Damage: [min:154, max:217] - Attack Success Rate: PVP: 3512 PVM: 3938
        Damage Multiplier: 28

        SU Level: 400, Stats: [str:564, agi:564, vit:561, ene:566] - Points to spend: 2175, already spent: 2172.
        Defense 188, Defense Success Rate: PVP: 1082 PVM: 141
        Attack Speed: 31
        Maic Damage: [min:113, max:188]
        Physical Damage: [min:141, max:141] - Attack Success Rate: PVP: 3174 PVM: 2987

        RF Level: 400, Stats: [str:568, agi:570, vit:575, ene:563] - Points to spend: 2175, already spent: 2172.
        Defense 71, Defense Success Rate: PVP: 714 PVM: 57
        Attack Speed: 22
        Maic Damage: [min:80, max:187]
        Physical Damage: [min:135, max:246] - Attack Success Rate: PVP: 3092 PVM: 2007
        Damage Multiplier: DS: 71 MELEE: 57 ROAR: 57
     */
}

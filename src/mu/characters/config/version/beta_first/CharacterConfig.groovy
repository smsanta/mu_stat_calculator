package mu.characters.config.version.beta_first

import mu.characters.BK
import mu.characters.Character
import mu.characters.CharacterData

/**
 * Configuration values for each character.
 * @see "https://oldsquad.ro/forum/topic/1829-beta-reports-suggestions-feedbacks/?do=findComment&comment=22533"
 *
 */
class CharacterConfig {

    /**
     * Generic formula calculation
     */
    static COMMON_CHARACTER_FORMULA = [
        DAMAGE_MULTIPLIER: [
            FORMULA : { Character character, wildcard = [:] ->
                if( character.hasDamageMultiplier() ){
                    def multiplierType = wildcard?.multiplierType ?: []
                    def calculatedMultiplier = character.ene / character.getDamageMultiplierValuation( multiplierType ).MULTIPLIER
                    ( (calculatedMultiplier >= CharacterData.CHARACTER_MAX_DAMAGE_MULTIPLIER) ? CharacterData.CHARACTER_MAX_DAMAGE_MULTIPLIER : calculatedMultiplier)
                } else {
                    CharacterData.CHARACTER_DEFAULT_DAMAGE_MULTIPLIER
                }
            }
        ],
        DEFENSE : [
            FORMULA : { Character character ->
                character.agi / character.getDefenseValuation().MULTIPLIER
            }
        ],
        DEFENSE_SUCCESS_RATE : [
            (CharacterData.FIGHT_TYPE_PVM) : [
                FORMULA : { Character character ->
                    character.agi / character.getDefenseSuccessRateValuation( CharacterData.FIGHT_TYPE_PVM ).MULTIPLIER
                }
            ],
            (CharacterData.FIGHT_TYPE_PVP) : [
                FORMULA : { Character character ->
                    def configDSR = character.getDefenseSuccessRateValuation( CharacterData.FIGHT_TYPE_PVP )
                    ((character.level * configDSR.LEVEL_MULTIPLIER ) / configDSR.LEVEL_MULTIPLIER_CONTROL) + (character.agi / configDSR.MULTIPLIER)
                }
            ]
        ],
        SPEED : [
            FORMULA : { Character character ->
                character.agi / character.getSpeedValuation().MULTIPLIER
            }
        ]
    ]

    static CHARACTERS_VALUATIONS =[
        (CharacterData.CHARACTER_ID_BK) : [
            POINTS_PER_LEVEL : [
                [
                    POINTS : 5,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 220
                ],
                [
                    POINTS : 6,
                    FROM_LEVEL : 220,
                    TO_LEVEL : 400
                ],
            ],
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 28,
                (CharacterData.CHARACTER_STAT_AGI) : 20,
                (CharacterData.CHARACTER_STAT_VIT) : 25,
                (CharacterData.CHARACTER_STAT_ENE) : 10
            ],
            DAMAGE_MULTIPLIER : [
                FORMULA : COMMON_CHARACTER_FORMULA.DAMAGE_MULTIPLIER.FORMULA,
                MULTIPLIER : 10
            ],
            DEFENSE : [
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 3
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 3
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 2
                ]
            ],
            SPEED : [
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 20
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    FORMULA : { Character character ->
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.MULTIPLIER
                        },
                        MULTIPLIER : 6
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.MULTIPLIER
                        },
                        MULTIPLIER : 2
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    FORMULA : { Character character ->
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.MULTIPLIER
                        },
                        MULTIPLIER : 7
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.MULTIPLIER
                        },
                        MULTIPLIER : 3
                    ]
                ]
            ]
        ],
        (CharacterData.CHARACTER_ID_ELF) : [
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 22,
                (CharacterData.CHARACTER_STAT_AGI) : 25,
                (CharacterData.CHARACTER_STAT_VIT) : 20,
                (CharacterData.CHARACTER_STAT_ENE) : 15
            ],
            POINTS_PER_LEVEL : [
                [
                    POINTS : 5,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 220
                ],
                [
                    POINTS : 6,
                    FROM_LEVEL : 220,
                    TO_LEVEL : 400
                ]
            ],
            DEFENSE : [
                MULTIPLIER : 7,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                        MULTIPLIER : 4,
                        APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 10,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 50,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    MIN_DAMAGE : [
                        MULTIPLIER : 8,
                    ],
                    MAX_DAMAGE : [
                        MULTIPLIER : 4,
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                        MIN_DAMAGE : [
                            MULTIPLIER : 8,
                        ],
                        MAX_DAMAGE : [
                            MULTIPLIER : 4,
                        ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_BOW) : [
                    MIN_DAMAGE : [
                        STR_MULTIPLIER : 5,
                        AGI_MULTIPLIER : 10
                    ],
                    MAX_DAMAGE : [
                        STR_MULTIPLIER : 2,
                        AGI_MULTIPLIER : 5
                    ]
                ]
            ]
        ],
        (CharacterData.CHARACTER_ID_SM) : [
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 18,
                (CharacterData.CHARACTER_STAT_AGI) : 18,
                (CharacterData.CHARACTER_STAT_VIT) : 15,
                (CharacterData.CHARACTER_STAT_ENE) : 30
            ],
            POINTS_PER_LEVEL : [
                [
                    POINTS : 5,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 220
                ],
                [
                    POINTS : 6,
                    FROM_LEVEL : 220,
                    TO_LEVEL : 400
                ]
            ],
            DEFENSE : [
                MULTIPLIER : 4,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    MULTIPLIER : 3,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 4,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 10,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ]
        ],
        (CharacterData.CHARACTER_ID_MG) : [
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 26,
                (CharacterData.CHARACTER_STAT_AGI) : 26,
                (CharacterData.CHARACTER_STAT_VIT) : 26,
                (CharacterData.CHARACTER_STAT_ENE) : 26
            ],
            POINTS_PER_LEVEL : [
                [
                    POINTS : 7,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 400
                ]
            ],
            DEFENSE : [
                MULTIPLIER : 4,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    MULTIPLIER : 3,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 4,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 20,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ]
        ],
        (CharacterData.CHARACTER_ID_DL) : [
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 26,
                (CharacterData.CHARACTER_STAT_AGI) : 20,
                (CharacterData.CHARACTER_STAT_VIT) : 20,
                (CharacterData.CHARACTER_STAT_ENE) : 15,
                (CharacterData.CHARACTER_STAT_COM) : 25
            ],
            DAMAGE_MULTIPLIER : [
                MULTIPLIER : 20,
                APPLIES_ON : CharacterData.CHARACTER_STAT_ENE
            ],
            DEFENSE : [
                MULTIPLIER : 6,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    MULTIPLIER : 7,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 4,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 10,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ]
        ],
        (CharacterData.CHARACTER_ID_RF) : [
            POINTS_PER_LEVEL : [
                [
                    POINTS : 5,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 220
                ],
                [
                    POINTS : 6,
                    FROM_LEVEL : 220,
                    TO_LEVEL : 400
                ]
            ],
            DAMAGE_MULTIPLIER : [
                (CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_DS) : [ MULTIPLIER : 8, APPLIES_ON: CharacterData.CHARACTER_STAT_AGI],
                (CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_MELEE) : [ MULTIPLIER : 10, APPLIES_ON: CharacterData.CHARACTER_STAT_VIT],
                (CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_ROAR) : [ MULTIPLIER : 10, APPLIES_ON: CharacterData.CHARACTER_STAT_ENE]
            ],
            DEFENSE : [
                MULTIPLIER : 8,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    MULTIPLIER : 10,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    LEVEL_MULTIPLIER : 15,
                    LEVEL_MULTIPLIER_CONTROL : 10,
                    MULTIPLIER : 5,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 25,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ]
        ],
        (CharacterData.CHARACTER_ID_SU) : [
            POINTS_PER_LEVEL : [
                [
                    POINTS : 5,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 220
                ],
                [
                    POINTS : 6,
                    FROM_LEVEL : 220,
                    TO_LEVEL : 400
                ]
            ],
            DEFENSE : [
                MULTIPLIER : 3,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    MULTIPLIER : 4,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 2,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 18,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    MIN_DAMAGE : [
                        MULTIPLIER : 8,
                    ],
                    MAX_DAMAGE : [
                        MULTIPLIER : 4,
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                        MIN_DAMAGE : [
                            MULTIPLIER : 8,
                        ],
                        MAX_DAMAGE : [
                            MULTIPLIER : 4,
                        ]
                ]
            ]
        ]
    ]

    /**
     SM - Physical : MinDMG - STR/6 , MaxDMG - STR/4
     SM - Magic : MinDMG - ENE/6 , MaxDMG - ENE/3

     FE - Physical : MinDMG - (STR+AGI)/6 , MaxDMG - (STR+AGI)/3
     FE - Magic : MinDMG - ENE/9 , MaxDMG - ENE/5
     FE - Bow : MinDMG - (STR/5) + (AGI/10) , MaxDMG - (STR/2) + (AGI/5)
     MG - Physical : MinDMG - (STR/5) + (ENE/9) , MaxDMG - (STR/2) + (ENE/6)
     MG - Magic : MinDMG - ENE/5 , MaxDMG - ENE/2
     DL - Physical : MinDMG - (STR/6) + (ENE/10) , MaxDMG - (STR/4) + (ENE/8)
     DL - Magic : MinDMG - ENE/8 , MaxDMG - ENE/3
     RF - Physical : MinDMG - (STR/6) + (VIT/14) , MaxDMG - (STR/3) + (VIT/10)
     RF - Magic : MinDMG - ENE/7 , MaxDMG - ENE/3

     SU - Physical : MinDMG - (STR+AGI)/8 , MaxDMG - (STR+AGI)/4
     SU - Magic : MinDMG - ENE/5 , MaxDMG - ENE/3

     */

}

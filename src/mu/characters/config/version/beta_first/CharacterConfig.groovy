package mu.characters.config.version.beta_first

import mu.characters.CharacterData

/**
 * Configuration values for each character.
 * @see "https://oldsquad.ro/forum/topic/1829-beta-reports-suggestions-feedbacks/?do=findComment&comment=22533"
 *
 */
class CharacterConfig {

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
                MULTIPLIER : 10,
                APPLIES_ON : CharacterData.CHARACTER_STAT_ENE
            ],
            DEFENSE : [
                MULTIPLIER : 3,
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
                    MULTIPLIER : 2,
                    APPLIES_ON : CharacterData.CHARACTER_STAT_AGI
                ]
            ],
            SPEED : [
                MULTIPLIER : 20,
                APPLIES_ON : CharacterData.CHARACTER_STAT_AGI,
                BASE : 30
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

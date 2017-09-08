package mu.characters.config.version.beta_first

import mu.characters.Character
import mu.characters.DL
import mu.characters.data.CharacterData

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
            FORMULA : { Character character ->
                if( character.hasDamageMultiplier() ){
                    def calculatedMultiplier = character.ene / character.getDamageMultiplierValuation().MULTIPLIER
                    ( (calculatedMultiplier >= CharacterData.CHARACTER_MAX_DAMAGE_MULTIPLIER) ?
                        CharacterData.CHARACTER_MAX_DAMAGE_MULTIPLIER : calculatedMultiplier ) as Integer
                } else {
                    CharacterData.CHARACTER_DEFAULT_DAMAGE_MULTIPLIER
                }
            }
        ],
        DEFENSE : [
            FORMULA : { Character character ->
                (character.agi / character.getDefenseValuation().MULTIPLIER) as Integer
            }
        ],
        DEFENSE_SUCCESS_RATE : [
            (CharacterData.FIGHT_TYPE_PVM) : [
                FORMULA : { Character character ->
                    (character.agi / character.getDefenseSuccessRateValuation( CharacterData.FIGHT_TYPE_PVM ).MULTIPLIER) as Integer
                }
            ],
            (CharacterData.FIGHT_TYPE_PVP) : [
                FORMULA : { Character character ->
                    def configDSR = character.getDefenseSuccessRateValuation( CharacterData.FIGHT_TYPE_PVP )
                    (((character.level * configDSR.LEVEL_MULTIPLIER ) / configDSR.LEVEL_MULTIPLIER_CONTROL) +
                        (character.agi / configDSR.MULTIPLIER)) as Integer
                }
            ]
        ],
        SPEED : [
            FORMULA : { Character character ->
                character.agi / character.getSpeedValuation().MULTIPLIER as Integer
            }
        ],
        ATTACK_SUCCESS_RATE : [
            (CharacterData.FIGHT_TYPE_PVM) : [
                FORMULA : { Character character ->
                    def configASR = character.getAttackSuccessRateValuation( CharacterData.FIGHT_TYPE_PVM )
                    ((character.level * configASR.LEVEL_MULTIPLIER) +
                            ((character.agi * configASR.AGI_MULTIPLIER) / configASR.AGI_MULTIPLIER_CONTROL) +
                            (character.str / configASR.STR_MULTIPLIER)) as Integer
                }
            ],
            (CharacterData.FIGHT_TYPE_PVP) : [
                FORMULA : { Character character ->
                    def configASR = character.getAttackSuccessRateValuation( CharacterData.FIGHT_TYPE_PVP )
                    (((character.level * configASR.LEVEL_MULTIPLIER) / configASR.LEVEL_MULTIPLIER_CONTROL) +
                        ((character.agi * configASR.AGI_MULTIPLIER) / configASR.AGI_MULTIPLIER_CONTROL)) as Integer
                }
            ]
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
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                        FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                        LEVEL_MULTIPLIER : 5,
                        AGI_MULTIPLIER : 3,
                        AGI_MULTIPLIER_CONTROL : 2,
                        STR_MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    AGI_MULTIPLIER : 45,
                    AGI_MULTIPLIER_CONTROL : 10
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
                //FE : AGI/7
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 7
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //FE : AGI/4
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //FE : (Level*2)/1 + AGI/10
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 10
                ]
            ],
            SPEED : [
                //FE : AGI/50
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 50
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    //FE: Physical : MinDMG - (STR+AGI)/6 , MaxDMG - (STR+AGI)/3
                    FORMULA : { Character character ->
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            (character.str + character.agi) / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_AGI_MULTIPLIER
                        },
                        STR_AGI_MULTIPLIER : 6
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            (character.str + character.agi) / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_AGI_MULTIPLIER
                        },
                        STR_AGI_MULTIPLIER : 3
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    //FE - Magic : MinDMG - ENE/9 , MaxDMG - ENE/5
                    FORMULA : { Character character ->
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 9
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 5
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_BOW) : [
                    //FE - Bow : MinDMG - (STR/5) + (AGI/10) , MaxDMG - (STR/2) + (AGI/5)
                    FORMULA : { Character character ->
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            (character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.STR_MULTIPLIER +
                                character.agi / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.AGI_MULTIPLIER )
                        },
                        STR_MULTIPLIER : 5,
                        AGI_MULTIPLIER : 10
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            (character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.STR_MULTIPLIER +
                                    character.agi / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.AGI_MULTIPLIER )
                        },
                        STR_MULTIPLIER : 2,
                        AGI_MULTIPLIER : 5
                    ]
                ]
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //FE : (Level*5) + (AGI*3)/2 + (STR/4)
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    LEVEL_MULTIPLIER : 5,
                    AGI_MULTIPLIER : 3,
                    AGI_MULTIPLIER_CONTROL : 2,
                    STR_MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //FE : (Level*3)/1 + (AGI*6)/10
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    AGI_MULTIPLIER : 6,
                    AGI_MULTIPLIER_CONTROL : 10
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
                //SM : AGI/4
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 4
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //SM : AGI/3
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 3
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //SM : (Level*2)/1 + AGI/4
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 4
                ]
            ],
            SPEED : [
                //SM : AGI/10
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 10
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    FORMULA : { Character character ->
                        //SM - Physical : MinDMG - STR/6 , MaxDMG - STR/4
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_MULTIPLIER
                        },
                        STR_MULTIPLIER : 6
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.STR_MULTIPLIER
                        },
                        STR_MULTIPLIER : 4
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    FORMULA : { Character character ->
                        //SM - Magic : MinDMG - ENE/6 , MaxDMG - ENE/3
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 6
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 3
                    ]
                ]
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //SM : (Level*5) + (AGI*3)/2 + (STR/4)
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    LEVEL_MULTIPLIER : 5,
                    AGI_MULTIPLIER : 3,
                    AGI_MULTIPLIER_CONTROL : 2,
                    STR_MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //SM : (Level*3)/1 + (AGI*4)/1
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    AGI_MULTIPLIER : 4,
                    AGI_MULTIPLIER_CONTROL : 1
                ]
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
                //MG : AGI/4
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 4
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //MG : AGI/3
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 3
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //MG : (Level*2)/1 + AGI/4
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 4
                ]
            ],
            SPEED : [
                //MG : AGI/20
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 20
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    FORMULA : { Character character ->
                        //MG - Physical : MinDMG - (STR/5) + (ENE/9) , MaxDMG - (STR/2) + (ENE/6)
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_MULTIPLIER +
                                character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        STR_MULTIPLIER : 5,
                        ENE_MULTIPLIER : 9
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.STR_MULTIPLIER +
                                    character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        STR_MULTIPLIER : 2,
                        ENE_MULTIPLIER : 6
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    FORMULA : { Character character ->
                        //MG - Magic : MinDMG - ENE/5 , MaxDMG - ENE/2
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 5
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 2
                    ]
                ]
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //SM : (Level*5) + (AGI*3)/2 + (STR/4)
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    LEVEL_MULTIPLIER : 5,
                    AGI_MULTIPLIER : 3,
                    AGI_MULTIPLIER_CONTROL : 2,
                    STR_MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //SM : (Level*3)/1 + (AGI*4)/1
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    AGI_MULTIPLIER : 4,
                    AGI_MULTIPLIER_CONTROL : 1
                ]
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
            POINTS_PER_LEVEL : [
                [
                    POINTS : 7,
                    FROM_LEVEL : 1,
                    TO_LEVEL : 400
                ]
            ],

            DAMAGE_MULTIPLIER : [
                //DL : ENE/20
                FORMULA : COMMON_CHARACTER_FORMULA.DAMAGE_MULTIPLIER.FORMULA,
                MULTIPLIER : 20
            ],
            DEFENSE : [
                //DL : AGI/6
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 6
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //DL : AGI/7
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 7
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //DL : (Level*2)/1 + AGI/2
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 4
                ]
            ],
            SPEED : [
                //DL : AGI/10
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 20
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    FORMULA : { Character character ->
                        //DL - Physical : MinDMG - (STR/6) + (ENE/10) , MaxDMG - (STR/4) + (ENE/8)
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            ((character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_MULTIPLIER) +
                                (character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.ENE_MULTIPLIER))
                        },
                        STR_MULTIPLIER : 6,
                        ENE_MULTIPLIER : 10
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            ((character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.STR_MULTIPLIER) +
                                (character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.ENE_MULTIPLIER))
                        },
                        STR_MULTIPLIER : 4,
                        ENE_MULTIPLIER : 8
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    FORMULA : { Character character ->
                        //DL - Magic : MinDMG - ENE/8 , MaxDMG - ENE/3
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 8
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 3
                    ]
                ]
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //DL: (Level*5) + (AGI*6)/2 + (STR/4) + (CMD/10)
                    FORMULA : { DL character ->
                        COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA(character) +
                            (character.com / character.getAttackSuccessRateValuation( CharacterData.FIGHT_TYPE_PVM ).COM_MULTIPLIER) as Integer
                    },
                    LEVEL_MULTIPLIER : 5,
                    AGI_MULTIPLIER : 6,
                    AGI_MULTIPLIER_CONTROL : 2,
                    STR_MULTIPLIER : 4,
                    COM_MULTIPLIER : 10
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //DL : (Level*3)/1 + (AGI*4)/1
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    AGI_MULTIPLIER : 4,
                    AGI_MULTIPLIER_CONTROL : 1
                ]
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
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 25,
                (CharacterData.CHARACTER_STAT_AGI) : 27,
                (CharacterData.CHARACTER_STAT_VIT) : 32,
                (CharacterData.CHARACTER_STAT_ENE) : 20
            ],
            DAMAGE_MULTIPLIER : [
                (CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_DS) : [
                    //RF : AGI/8 (DS)
                    FORMULA : { Character character ->
                        character.agi / character.getDamageMultiplierValuation(CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_DS).AGI_MULTIPLIER
                    },
                    AGI_MULTIPLIER : 8,
                ],
                (CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_MELEE) : [
                    //RF : VIT/10 (Melee)
                    FORMULA : { Character character ->
                        character.agi / character.getDamageMultiplierValuation(CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_MELEE).VIT_MULTIPLIER
                    },
                    VIT_MULTIPLIER : 10
                ],
                (CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_ROAR) : [
                    //RF : ENE/10 (Roar)
                    FORMULA : { Character character ->
                        character.agi / character.getDamageMultiplierValuation(CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_ROAR).ENE_MULTIPLIER
                    },
                    ENE_MULTIPLIER : 10,
                ]
            ],
            DEFENSE : [
                //RF : AGI/8
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 8
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //RF : AGI/10
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 10
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //RF : (Level*15)/10 + AGI/5
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 15,
                    LEVEL_MULTIPLIER_CONTROL : 10,
                    MULTIPLIER : 5
                ]
            ],
            SPEED : [
                //RF : AGI/25
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 25
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    FORMULA : { Character character ->
                        //RF - Physical : MinDMG - (STR/6) + (VIT/14) , MaxDMG - (STR/3) + (VIT/10)
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            ((character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_MULTIPLIER) +
                                (character.vit / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.VIT_MULTIPLIER))
                        },
                        STR_MULTIPLIER : 6,
                        VIT_MULTIPLIER : 14
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            ((character.str / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.STR_MULTIPLIER) +
                                (character.vit / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.VIT_MULTIPLIER))
                        },
                        STR_MULTIPLIER : 3,
                        VIT_MULTIPLIER : 10
                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    FORMULA : { Character character ->
                        //RF - Magic : MinDMG - ENE/7 , MaxDMG - ENE/3
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 7
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 3
                    ]
                ]
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //RF : (Level*3) + (AGI*5)/4 + (STR/6)
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    AGI_MULTIPLIER : 5,
                    AGI_MULTIPLIER_CONTROL : 4,
                    STR_MULTIPLIER : 6
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //RF : (Level*26)/10 + (AGI*36)/10
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 26,
                    LEVEL_MULTIPLIER_CONTROL : 10,
                    AGI_MULTIPLIER : 36,
                    AGI_MULTIPLIER_CONTROL : 10
                ]
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
            INITIAL_STATS : [
                (CharacterData.CHARACTER_STAT_STR) : 21,
                (CharacterData.CHARACTER_STAT_AGI) : 21,
                (CharacterData.CHARACTER_STAT_VIT) : 18,
                (CharacterData.CHARACTER_STAT_ENE) : 23
            ],
            DEFENSE : [
                //SU : AGI/3
                FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE.FORMULA,
                MULTIPLIER : 3
            ],
            DEFENSE_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //SU : AGI/4
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //SU : (Level*2)/1 + AGI/2
                    FORMULA : COMMON_CHARACTER_FORMULA.DEFENSE_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 2,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    MULTIPLIER : 2
                ]
            ],
            SPEED : [
                //SU : AGI/18
                FORMULA : COMMON_CHARACTER_FORMULA.SPEED.FORMULA,
                MULTIPLIER : 18
            ],
            DAMAGE : [
                (CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL) : [
                    FORMULA : { Character character ->
                        //SU - Physical : MinDMG - (STR+AGI)/8 , MaxDMG - (STR+AGI)/4
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            ((character.str + character.agi) / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_AGI_MULTIPLIER)
                        },
                        STR_AGI_MULTIPLIER : 8
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            ((character.str + character.agi) / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL ).MIN_DAMAGE.STR_AGI_MULTIPLIER)
                        },
                        STR_AGI_MULTIPLIER : 4

                    ]
                ],
                (CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC) : [
                    FORMULA : { Character character ->
                        //SU - Magic : MinDMG - ENE/5 , MaxDMG - ENE/3
                        [
                            min : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.FORMULA(character) as Integer,
                            max : character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.FORMULA(character) as Integer
                        ]
                    },
                    MIN_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MIN_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 5
                    ],
                    MAX_DAMAGE : [
                        FORMULA : { Character character ->
                            character.ene / character.getDamageValuation( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC ).MAX_DAMAGE.ENE_MULTIPLIER
                        },
                        ENE_MULTIPLIER : 3
                    ]
                ]
            ],
            ATTACK_SUCCESS_RATE : [
                (CharacterData.FIGHT_TYPE_PVM) : [
                    //SU : (Level*5) + (AGI*3)/2 + (STR/4)
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVM}".FORMULA,
                    LEVEL_MULTIPLIER : 5,
                    AGI_MULTIPLIER : 3,
                    AGI_MULTIPLIER_CONTROL : 2,
                    STR_MULTIPLIER : 4
                ],
                (CharacterData.FIGHT_TYPE_PVP) : [
                    //SU : (Level*3)/1 + (AGI*35)/10
                    FORMULA : COMMON_CHARACTER_FORMULA.ATTACK_SUCCESS_RATE."${CharacterData.FIGHT_TYPE_PVP}".FORMULA,
                    LEVEL_MULTIPLIER : 3,
                    LEVEL_MULTIPLIER_CONTROL : 1,
                    AGI_MULTIPLIER : 35,
                    AGI_MULTIPLIER_CONTROL : 10
                ]
            ]
        ]
    ]

}

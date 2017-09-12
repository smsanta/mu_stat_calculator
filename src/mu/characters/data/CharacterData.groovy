package mu.characters.data

abstract class CharacterData {
    //Versions
    static def CHARACTER_VERSION_DEFAULT = "beta_first"

    //Characters ID
    static CHARACTER_ID_BK = "BK"   //Dragon Knight
    static CHARACTER_ID_ELF = "ELF" //Elf
    static CHARACTER_ID_SM = "SM"   //Soul Master
    static CHARACTER_ID_MG = "MG"   //Magic Gladiator
    static CHARACTER_ID_DL = "DL"   //Dark Lord
    static CHARACTER_ID_RF = "RF"   //Rage Figther
    static CHARACTER_ID_SU = "SU"   //Summoner

    //Stats character mapping
    static CHARACTER_ID_TYPE = "character"
    static CHARACTER_ID = "id"
    static CHARACTER_LEVEL = "level"
    static CHARACTER_STAT_STR = "str"
    static CHARACTER_STAT_AGI = "agi"
    static CHARACTER_STAT_VIT = "vit"
    static CHARACTER_STAT_ENE = "ene"
    static CHARACTER_STAT_COM = "com"

    //Specific character values
    static Integer CHARACTER_MAX_DAMAGE_MULTIPLIER = 500
    static Integer CHARACTER_DEFAULT_DAMAGE_MULTIPLIER = 200

    //Damage multiplier apllication types
    static CHARACTER_DAMAGE_MULTIPLIER_TYPE_UNIQUE = "UNIQUE"
    static CHARACTER_DAMAGE_MULTIPLIER_TYPE_DS = "DS"
    static CHARACTER_DAMAGE_MULTIPLIER_TYPE_MELEE = "MELEE"
    static CHARACTER_DAMAGE_MULTIPLIER_TYPE_ROAR = "ROAR"

    //Damage Types
    static CHARACTER_DAMAGE_TYPE_PHYSICAL = "PHYSICAL"
    static CHARACTER_DAMAGE_TYPE_MAGIC = "MAGIC"
    static CHARACTER_DAMAGE_TYPE_BOW = "BOW"

    //Figtht type
    static FIGHT_TYPE_PVM = "PVM"
    static FIGHT_TYPE_PVP = "PVP"

    static def getCharacterIdList(){
        [
            CHARACTER_ID_BK,
            CHARACTER_ID_SM,
            CHARACTER_ID_ELF,
            CHARACTER_ID_MG,
            CHARACTER_ID_DL,
            CHARACTER_ID_SU,
            CHARACTER_ID_RF
        ]
    }

    static def getCharacterStatsList(){
        [
            CHARACTER_STAT_STR,
            CHARACTER_STAT_AGI,
            CHARACTER_STAT_VIT,
            CHARACTER_STAT_ENE,
            CHARACTER_STAT_COM
        ]
    }
}

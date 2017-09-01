package mu.characters

abstract class CharacterData {
    //Characters ID
    static CHARACTER_ID_BK = "BK"   //Dragon Knight
    static CHARACTER_ID_ELF = "ELF" //Elf
    static CHARACTER_ID_SM = "SM"   //Soul Master
    static CHARACTER_ID_MG = "MG"   //Magic Gladiator
    static CHARACTER_ID_DL = "DL"   //Dark Lord
    static CHARACTER_ID_RF = "RF"   //Rage Figther
    static CHARACTER_ID_SU = "SU"   //Summoner

    //Stats character mapping
    static CHARACTER_LEVEL = "level"
    static CHARACTER_STAT_STR = "str"
    static CHARACTER_STAT_AGI = "agi"
    static CHARACTER_STAT_VIT = "vit"
    static CHARACTER_STAT_ENE = "ene"
    static CHARACTER_STAT_COM = "com"

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
}

package mu.characters

class ELF extends Character{

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_ELF
    }

    @Override
    boolean hasDamageMultiplier() {
        return false
    }

    def calculatePhysicalDamage( damageConfig ) {
        //FE - Physical : MinDMG - (STR+AGI)/6 , MaxDMG - (STR+AGI)/3
        Integer minDamage = (str + agi) / damageConfig.MIN_DAMAGE.MULTIPLIER
        Integer maxDamage = (str + agi) / damageConfig.MAX_DAMAGE.MULTIPLIER
       [ min : minDamage, max : maxDamage ]
    }

    def calculateMagicDamage( damageConfig ) {
        //FE - Magic : MinDMG - ENE/9 , MaxDMG - ENE/5
        Integer minDamage = ene / damageConfig.MIN_DAMAGE.MULTIPLIER
        Integer maxDamage = ene / damageConfig.MAX_DAMAGE.MULTIPLIER
        [ min : minDamage, max : maxDamage ]
    }

    def calculateBowDamage( damageConfig ) {
        //FE - Bow : MinDMG - (STR/5) + (AGI/10) , MaxDMG - (STR/2) + (AGI/5)
        Integer minDamage = (str / damageConfig.MIN_DAMAGE.STR_MULTIPLIER) + (agi / damageConfig.MIN_DAMAGE.AGI_MULTIPLIER)
        Integer maxDamage = (str / damageConfig.MAX_DAMAGE.STR_MULTIPLIER) + (agi / damageConfig.MAX_DAMAGE.AGI_MULTIPLIER)
        [ min : minDamage, max : maxDamage ]
    }

    @Override
    String toString() {
        super.toString() + "Bow Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_BOW )}\n"
    }
}

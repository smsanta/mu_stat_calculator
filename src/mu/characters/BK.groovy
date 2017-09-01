package mu.characters

/**
 * Created by IGNACIO-PCX on 1/9/2017.
 */
class BK extends Character {
    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_BK
    }

    @Override
    boolean hasDamageMultiplier() {
        true
    }

    def calculatePhysicalDamage( damageConfig ){
        //BK - Physical : MinDMG - STR/6 , MaxDMG - STR/2
        Integer minDamage = str / damageConfig.MIN_DAMAGE.MULTIPLIER
        Integer maxDamage = str / damageConfig.MAX_DAMAGE.MULTIPLIER
        [ min : minDamage, max : maxDamage ]
    }

    def calculateMagicDamage( damageConfig ){
        //BK - Magic : MinDMG - ENE/7 , MaxDMG - ENE/3
        Integer minDamage = ene / damageConfig.MIN_DAMAGE.MULTIPLIER
        Integer maxDamage = ene / 3
        [ min : minDamage, max : maxDamage ]
    }
}

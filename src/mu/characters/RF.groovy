package mu.characters

import mu.characters.data.CharacterData

class RF extends Character{

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_RF
    }

    @Override
    boolean hasDamageMultiplier() {
        return true
    }

    @Override
    String toString() {
        "${getCharId()} Level: ${level}, Stats: ${getStats()} - Points to spend: ${calculatePointsForCurrentLevel()}, already spent: ${calculateSpentPoints()}. \n" +
            "Defense ${calculateDefense()}, Defense Success Rate: PVP: ${calculateDefenseSucessRate()} PVM: ${calculateDefenseSucessRate(CharacterData.FIGHT_TYPE_PVM)}\n" +
            "Attack Speed: ${calculateSpeed()}\n" +
            "Maic Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC )}\n" +
            "Physical Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL )} - " +
            "Attack Success Rate: PVP: ${calculateAttackSuccessRate( CharacterData.FIGHT_TYPE_PVP )} PVM: ${calculateAttackSuccessRate( CharacterData.FIGHT_TYPE_PVM )}\n" +
            "Damage Multiplier: ${CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_DS}: ${calculateDamageMultiplier( CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_DS )} " +
            "${CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_MELEE}: ${calculateDamageMultiplier( CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_MELEE )} " +
            "${CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_ROAR}: ${calculateDamageMultiplier( CharacterData.CHARACTER_DAMAGE_MULTIPLIER_TYPE_ROAR )} "
    }
}

package mu.characters

import mu.characters.data.CharacterData

class ELF extends Character{

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_ELF
    }

    @Override
    boolean hasDamageMultiplier() {
        return false
    }

    @Override
    String toString() {
        super.toString() + "Bow Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_BOW )}\n"
    }
}

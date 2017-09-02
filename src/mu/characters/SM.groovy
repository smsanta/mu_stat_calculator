package mu.characters

import mu.characters.data.CharacterData

class SM extends Character {

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_SM
    }

    @Override
    boolean hasDamageMultiplier() {
        return false
    }
}

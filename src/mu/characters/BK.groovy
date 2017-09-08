package mu.characters

import mu.characters.data.CharacterData
import mu.characters.Character

class BK extends Character {

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_BK
    }

    @Override
    boolean hasDamageMultiplier() {
        true
    }

}

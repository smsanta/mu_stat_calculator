package main.groovy.mu.characters

import main.groovy.mu.characters.data.CharacterData

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

package main.groovy.mu.characters

import main.groovy.mu.characters.data.CharacterData

class SU extends Character{

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_SU
    }

    @Override
    boolean hasDamageMultiplier() {
        return false
    }
}

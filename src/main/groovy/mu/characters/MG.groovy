package main.groovy.mu.characters

import main.groovy.mu.characters.data.CharacterData

class MG extends Character {

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_MG
    }

    @Override
    boolean hasDamageMultiplier() {
        false
    }
}
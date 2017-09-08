package mu.characters

class DL extends Character{

    Integer com

    @Override
    String getCharId() {
        CharacterData.CHARACTER_ID_DL
    }

    @Override
    boolean hasDamageMultiplier() {
        return true
    }

    void autoSpendPoints(str = 0, agi = 0, vit = 0, ene = 0, com = 0){
        super.autoSpendPoints(str, agi, vit, ene)
        def pointsToSpend = calculatePointsForCurrentLevel()
        this.com += com * pointsToSpend / 100
    }

    @Override
    def getStats() {
        super.getStats() + [ ( CharacterData.CHARACTER_STAT_COM ) : com]
    }
}

package mu.characters

import mu.characters.config.version.beta_first.CharacterConfig
import mu.characters.data.CharacterData

abstract class Character {

    Integer id

    Integer level = 1

    Integer str
    Integer agi
    Integer ene
    Integer vit

    /**
     * Must return char char id any in
     * @see mu.characters.config.CharacterData.CHARACTER_ID_XX
     * @return
     */
    abstract String getCharId();

    abstract boolean hasDamageMultiplier()

    Integer calculateDamageMultiplier( multiplierType = [] ){
        getDamageMultiplierValuation( multiplierType ).FORMULA( this )
    }

    Integer calculateDefense(){
        getDefenseValuation().FORMULA(this)
    }

    /**
     * Calculates defense success rate according if is for pvp - pvm
     * @param pvp
     * @return
     */
    Integer calculateDefenseSucessRate( fightType = CharacterData.FIGHT_TYPE_PVP ){
        getDefenseSuccessRateValuation( fightType ).FORMULA( this )
    }

    Integer calculateSpeed(){
        getSpeedValuation().FORMULA(this)
    }

    def calculateDamage( damageType ) {
        getDamageValuation( damageType ).FORMULA(this)
    }

    def calculateAttackSuccessRate( fightType = CharacterData.FIGHT_TYPE_PVP ) {
        getAttackSuccessRateValuation( fightType ).FORMULA(this)
    }

    /** CONFIG OBAINING **/
    def getCharacterConfig(){
        CharacterConfig.CHARACTERS_VALUATIONS."${ getCharId() }"
    }

    def getDamageValuation( damageType ){
        getCharacterConfig().DAMAGE."${damageType}"
    }

    def getDamageMultiplierValuation( damageType = null ){
        if( hasDamageMultiplier() ){
            if(damageType){
                getCharacterConfig().DAMAGE_MULTIPLIER."${damageType}"
            }else{
                getCharacterConfig().DAMAGE_MULTIPLIER
            }
        }
    }

    def getDefenseValuation(){
        getCharacterConfig().DEFENSE
    }

    def getDefenseSuccessRateValuation( type ){
        getCharacterConfig().DEFENSE_SUCCESS_RATE."$type"
    }

    def getLevelConfig(){
        getCharacterConfig().POINTS_PER_LEVEL
    }

    def getInitialStats(){
        getCharacterConfig().INITIAL_STATS
    }

    def getSpeedValuation(){
        getCharacterConfig().SPEED
    }

    def getAttackSuccessRateValuation( type ){
        getCharacterConfig().ATTACK_SUCCESS_RATE."$type"
    }

    /** END CONFIG OBAINING **/

    /** Utility Calculations **/
    def calculateRemainingUnspentPoints(){
        def remainingPoints = calculatePointsForCurrentLevel() - calculateSpentPoints()
        remainingPoints
    }

    def calculateSpentPoints(){
        def baseStats = getInitialStats().values().sum()
        def currentStatsAcumulation = str + agi + vit + ene

        currentStatsAcumulation - baseStats
    }

    /**
     * Calculates the total amount to be spend according the current level of the character.
     */
    Integer calculatePointsForCurrentLevel(){
        def lvlConfig = getLevelConfig();

        Integer pointStack = 0

        lvlConfig.each { configPerLevelWindow ->
            if( level >= configPerLevelWindow.TO_LEVEL ){
                pointStack += (configPerLevelWindow.TO_LEVEL - configPerLevelWindow.FROM_LEVEL) *  configPerLevelWindow.POINTS
            } else {
                if ( checkCurrentLevelApplyToConfigLevelWindow( configPerLevelWindow ) ){
                    def totalLvls = level - configPerLevelWindow.FROM_LEVEL
                    totalLvls = totalLvls > 1 ? totalLvls : 1
                    pointStack += (totalLvls) * configPerLevelWindow.POINTS
                }
            }
        }

        pointStack
    }

    /**
     * Check if the current level is in between config lvl window.
     *
     * @param configPerLevelWindow
     * @return
     */
    protected boolean checkCurrentLevelApplyToConfigLevelWindow( configPerLevelWindow ){
        level > configPerLevelWindow.FROM_LEVEL && level <= configPerLevelWindow.TO_LEVEL
    }

    /**
     * Checks if the amount of attrubest spent are valid.
     * @return
     */
    def checkAttributes(){
        def response = []

        def spent = calculateSpentPoints()
        def pointForCurrentLevel = calculatePointsForCurrentLevel()
        def poinstOK = spent <= pointForCurrentLevel

        if(!poinstOK){
            response << "This guy has more spent point than it is supposed to be. Max: ${pointForCurrentLevel} - Spent: $spent"
        }else{
            response << "No problems detected."
        }

        response
    }
    /** END Utility Calculations **/

    /** Utility Methods **/
    /**
     * Distributes the available spend points automatic.
     *
     * @param str - the % of your available points to be spend
     * @param agi - the % of your available points to be spend
     * @param vit - the % of your available points to be spend
     * @param ene - the % of your available points to be spend
     * @return
     */
    void autoSpendPoints(str = 0, agi = 0, vit = 0, ene = 0){
        def pointsToSpend = calculatePointsForCurrentLevel()

        this.str += str * pointsToSpend / 100
        this.agi += agi * pointsToSpend / 100
        this.vit += vit * pointsToSpend / 100
        this.ene += ene * pointsToSpend / 100
    }

    /**
     * Builds stats into a map.
     *
     * @return
     */
    def getStats(){
        [
            (CharacterData.CHARACTER_STAT_STR) : str,
            (CharacterData.CHARACTER_STAT_AGI) : agi,
            (CharacterData.CHARACTER_STAT_VIT) : vit,
            (CharacterData.CHARACTER_STAT_ENE) : ene
        ]
    }

    /**
     * Adds Stats to the character
     *
     * @param statType
     * @param value
     * @param fixed - If fixed will add the exact amount, otherwise will be added as a percentage of total points
     *              for the current char level.
     * @return
     */
    def addStat(statType, value, fixed = true){
        Integer valToAdd = value
        if(!fixed){
            def pointsToSpend = calculatePointsForCurrentLevel()
            valToAdd = value * pointsToSpend / 100
        }

        this."${statType}" += valToAdd
    }

    /**
     * Set fixed Stats to the character
     *
     * @param statType
     * @param value
     * @return
     */
    def setStat(statType, value, fixed = true){
        Integer valToAdd = value
        if(!fixed){
            def pointsToSpend = calculatePointsForCurrentLevel()
            valToAdd = value * pointsToSpend / 100
        }

        this."${statType}" = valToAdd
    }

    /**
     * Rollbacks character stats to it initial form.
     */
    void resetStats() {
        def initialStats = getInitialStats()
        str = initialStats.str
        agi = initialStats.agi
        ene = initialStats.ene
        vit = initialStats.vit
    }

    /**
     * Creates a new character from a specific version
     *
     * @param charId
     * @param version
     */
    static def newFromVersion(charId, level, version){
        def charConfig = Class.forName("mu.characters.config.version.${version}.CharacterConfig").newInstance()
        def charClass = Class.forName("mu.characters.${charId}")
        Character character = charClass.newInstance( charConfig.CHARACTERS_VALUATIONS."${charId}".INITIAL_STATS )
        character.level = level
        character
    }

    @Override
    String toString() {
        " Character inicial statistics ${getInitialStats()}\n" +
            "${(id >= 0 ? "ID: $id\n" : "") }${getCharId()} Level: ${level}, Stats: ${getStats()} - Total Points to spend: ${calculatePointsForCurrentLevel()}, " +
            "already spent: ${calculateSpentPoints()}, Remainig: ${calculateRemainingUnspentPoints()}. \n" +
            "Defense ${calculateDefense()}, Defense Success Rate: PVP: ${calculateDefenseSucessRate()} PVM: ${calculateDefenseSucessRate(CharacterData.FIGHT_TYPE_PVM)}\n" +
            "Attack Speed: ${calculateSpeed()}\n" +
            "Maic Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC )}\n" +
            "Physical Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL )} - " +
            "Attack Success Rate: PVP: ${calculateAttackSuccessRate( CharacterData.FIGHT_TYPE_PVP )} PVM: ${calculateAttackSuccessRate( CharacterData.FIGHT_TYPE_PVM )}\n" +
            (hasDamageMultiplier() ? "Damage Multiplier: ${calculateDamageMultiplier()}\n" : "")
    }
}

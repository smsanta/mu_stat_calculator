package mu.characters

import mu.characters.config.version.beta_first.CharacterConfig

abstract class Character {

    //Specific character values
    static final Integer MAX_DAMAGE_MULTIPLIER = 500

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

    Integer calculateDamageMultiplier(){
        if( hasDamageMultiplier() ){
            def dmgMultiplierConfig = getDamageMultiplierValuation()
            def multiplier = this."${dmgMultiplierConfig.APPLIES_ON}" / dmgMultiplierConfig.MULTIPLIER
            multiplier < MAX_DAMAGE_MULTIPLIER ? multiplier : MAX_DAMAGE_MULTIPLIER
        }else{
            200
        }
    }

    Integer calculateDefense(){
        def charDefence = getDefenseValuation()
        this."${charDefence.APPLIES_ON}" / charDefence.MULTIPLIER
    }

    /**
     * Calculates defense success rate according if is for pvp - pvm
     * @param pvp
     * @return
     */
    Integer calculateDefenseSucessRate( fightType = CharacterData.FIGHT_TYPE_PVP ){
        def configDSR = getDefenseSuccessRateValuation( fightType )

        if( fightType == CharacterData.FIGHT_TYPE_PVP ){
            ((level * configDSR.LEVEL_MULTIPLIER ) / configDSR.LEVEL_MULTIPLIER_CONTROL) + (this."${configDSR.APPLIES_ON}" / configDSR.MULTIPLIER)
        } else {
            this."${configDSR.APPLIES_ON}" / configDSR.MULTIPLIER
        }
    }

    Integer calculateSpeed(){
        def speedValuation = getSpeedValuation()
        this."${speedValuation.APPLIES_ON}" / speedValuation.MULTIPLIER
    }

    def calculateDamage( damageType ) {
        def damageConfig = getDamageValuation( damageType )
        this."calculate${damageType.toLowerCase().capitalize()}Damage"( damageConfig )
    }

    /** CONFIG OBAINING **/
    def getCharacterConfig(){
        CharacterConfig.CHARACTERS_VALUATIONS."${ getCharId() }"
    }

    def getDamageValuation( damageType ){
        getCharacterConfig().DAMAGE."${damageType}"
    }

    def getDamageMultiplierValuation(def digIn = []){
        if( hasDamageMultiplier() ){
            if(digIn){
                def damageMultiplierValuation = getCharacterConfig().DAMAGE_MULTIPLIER
                digIn.each{
                    damageMultiplierValuation = damageMultiplierValuation."${it}"
                }
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

    /** END CONFIG OBAINING **/

    /** Utility Calculations **/
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
     * @return
     */
    def addStat(statType, value){
        this."${statType}" += value
    }

    @Override
    String toString() {
        "${getCharId()} lvl: ${level}, Stats: ${getStats()} - Points to spend: ${calculatePointsForCurrentLevel()}, already spent: ${calculateSpentPoints()}. \n" +
                "Defense ${calculateDefense()}, Defense Success Rate: PVP: ${calculateDefenseSucessRate()} PVM: ${calculateDefenseSucessRate(CharacterData.FIGHT_TYPE_PVM)}\n" +
                "Attack Speed: ${calculateSpeed()}\n" +
                "Maic Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_MAGIC )}\n" +
                "Physical Damage: ${calculateDamage( CharacterData.CHARACTER_DAMAGE_TYPE_PHYSICAL )} - Damage Success Rate: -1\n" +
                (hasDamageMultiplier() ? "Damage Multiplier: ${calculateDamageMultiplier()}" : "")
    }

}

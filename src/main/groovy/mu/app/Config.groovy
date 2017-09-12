package main.groovy.mu.app

/**
 * Confirations related to the app.
 */
class Config {

    static def DEV_MODE = "" //String false
    static def SYSTEM_ENV_PREFIX = "MSC_CONFIG_"

    static boolean isDevMode(){
        DEV_MODE
    }

    static void loadSystemConfigs() {
        System.getenv().each { key, value ->
            if( key.startsWith( Config.SYSTEM_ENV_PREFIX ) ){
                def configKey = key.replaceFirst( Config.SYSTEM_ENV_PREFIX, "" )
                Logger.debug( "Found key ${key} builded configKey ${configKey} = $value" )
                Config."${configKey}" = value
            }
        }
    }
}

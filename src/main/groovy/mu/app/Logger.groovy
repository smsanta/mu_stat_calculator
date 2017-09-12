package main.groovy.mu.app

import main.groovy.mu.app.exception.MSCException
import main.groovy.console.ConsoleApp

class Logger {

    static debug( message ){
        if( Config.isDevMode() ){
            ConsoleApp.write( message )
        }
    }

    static exception( Throwable e ){
        if( Config.isDevMode() ){
            ConsoleApp.write( e.message )
            ConsoleApp.write( MSCException.getStringTrace(e) )
        }
    }
}

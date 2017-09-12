package main.groovy.mu.app

import main.groovy.console.ConsoleApp

class App {

    public static void main(String[] args){
        Config.loadSystemConfigs()

        ConsoleApp.start()
    }
}

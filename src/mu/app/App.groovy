package mu.app

import mu.characters.data.CharacterData
import mu.characters.Character
import ui.console.ConsoleApp

class App {

    public static void main(String[] args){
        Config.loadSystemConfigs()

        ConsoleApp.start()
    }
}

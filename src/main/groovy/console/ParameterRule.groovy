package main.groovy.console

enum ParameterRule {
    REQUIRED,
    NUMERIC,
    ALFANUMERIC,
    IN_LIST;

    def wildcard

    public ParameterRule( pWildcard = null){
        wildcard = pWildcard
    }

    ParameterRule setList( list ){
        wildcard = list
        this
    }

    @Override
    String toString() {
        super.toString() + (wildcard ?:"")
    }
}

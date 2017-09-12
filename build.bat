START /B /W %GRADLE_HOME%\bin\gradle build
MD releases\0.9.2
SLEEP 3
START /B /W xcopy build\distributions\mu_stat_calculator-0.9.2.zip releases\0.9.2\msc.zip* /Y
ECHO Release done!
START /B /W %GRADLE_HOME%\bin\gradle build
START /B /W xcopy build\distributions\mu_stat_calculator-0.9.0.zip releases\0.9.1\msc.zip /Y
ECHO Release done!
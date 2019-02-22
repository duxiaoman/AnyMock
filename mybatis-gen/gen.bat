@ECHO OFF
del /f /s /q ..\app\common\dal\src\main\resources\META-INF\mappers\auto
md ..\app\common\dal\src\main\resources\META-INF\mappers\auto
java -cp .;mybatis-generator-core-1.3.7.jar org.mybatis.generator.api.ShellRunner -configfile config.xml -overwrite
pause
@ECHO ON

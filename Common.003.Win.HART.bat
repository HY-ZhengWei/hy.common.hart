

del /Q hy.common.hart.jar
del /Q hy.common.hart-sources.jar


call mvn clean package
cd .\target\classes

rd /s/q .\org\hy\common\hart\junit


jar cvfm hy.common.hart.jar META-INF/MANIFEST.MF META-INF org

copy hy.common.hart.jar ..\..
del /q hy.common.hart.jar
cd ..\..





cd .\src\main\java
xcopy /S ..\resources\* .
jar cvfm hy.common.hart-sources.jar META-INF\MANIFEST.MF META-INF org
copy hy.common.hart-sources.jar ..\..\..
del /Q hy.common.hart-sources.jar
rd /s/q META-INF
cd ..\..\..

pause
@echo off
::转到当前盘符
%~d0
::打开当前目录
cd %~dp0
::你做的主JAR包的路径
set MainJar=dl2ane.jar
::第三方JAR包的路径
set jar1=downjoy_sdk_2.0.jar
::第三方JAR包顶级包名称
set packageName1=com
echo =========== start combin ==============
::解压第三方包
jar -xf %jar1%
::合并主JAR包
jar -uf %MainJar% %packageName1% 
::如果还有别的顶级包可以接着合并，例如：
::jar -uf %MainJar% %packageName2%
::jar -uf %MainJar% %packageName3%
echo =========== over ==============
echo 再点一下就结束了--小Q
pause
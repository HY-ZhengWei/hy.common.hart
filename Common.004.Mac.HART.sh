#!/bin/sh

cd ./bin


rm -R ./org/hy/common/hart/junit


jar cvfm hy.common.hart.jar MANIFEST.MF META-INF org

cp hy.common.hart.jar ..
rm hy.common.hart.jar
cd ..





cd ./src
jar cvfm hy.common.hart-sources.jar MANIFEST.MF META-INF org 
cp hy.common.hart-sources.jar ..
rm hy.common.hart-sources.jar
cd ..

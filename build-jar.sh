#!/usr/bin/env bash
OUT_DIR=out
JAR=derlin-FirstProject.jar

# clean
rm -rf $OUT_DIR 2>/dev/null
rm $JAR 2>/dev/null
mkdir $OUT_DIR

# compile
javac src/*.java -d $OUT_DIR
cp -r src/META-INF $OUT_DIR
cp resources/* $OUT_DIR

# package
cd $OUT_DIR || exit 1
jar cfmv ../$JAR ./META-INF/MANIFEST.MF *.*
cd -
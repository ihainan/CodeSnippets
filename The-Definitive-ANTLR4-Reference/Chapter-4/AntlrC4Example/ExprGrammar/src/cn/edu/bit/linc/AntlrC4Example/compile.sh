#!/bin/zsh
source ~/.zshrc
rm -rf *.java *.class
echo $1
# antlr4 Expr.g4 -package cn.edu.bit.linc.AntlrC4Example
antlr4 -no-listener -visitor "$1.g4" -package cn.edu.bit.linc.AntlrC4Example
javac "$1"*.java

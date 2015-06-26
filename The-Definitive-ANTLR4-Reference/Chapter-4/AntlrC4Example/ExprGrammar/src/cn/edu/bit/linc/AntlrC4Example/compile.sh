#!/bin/zsh
source ~/.zshrc
antlr4 Expr.g4 -package cn.edu.bit.linc.AntlrC4Example
javac Expr*.java

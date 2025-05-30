#!/bin/bash

mvn clean install -DskipTests=true || { echo "Build failed"; exit 1; }

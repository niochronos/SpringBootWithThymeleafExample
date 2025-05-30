#!/bin/bash

mvn compile jib:dockerBuild || { echo "Build failed"; exit 1; }

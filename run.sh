#!/bin/bash

GREEN='\033[1;32m'
BLUE='\033[1;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}INFO| Clean the Project${NC}"
./gradlew clean
echo ""

echo -e "${BLUE}INFO| Run Test"
./gradlew app:runTest

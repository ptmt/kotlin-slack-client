#!/bin/sh

# sudo apt-get install libgtk-3-dev
./gradlew :linux:compileKonanKotlinSlack && ./linux/build/konan/bin/linux/KotlinSlack.kexe

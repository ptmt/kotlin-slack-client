#!/bin/sh
./gradlew :linux:compileKonanKotlinSlackMacbook && ./linux/build/konan/bin/macbook/KotlinSlack.kexe

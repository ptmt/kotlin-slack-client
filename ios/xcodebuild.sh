#!/bin/bash

SCHEME="konan"
SDK="iphonesimulator"
DESTINATION="platform=iOS Simulator,name=iPhone 5s,OS=10.1"


xcodebuild \
  -project "konan.xcodeproj" \
  -configuration Debug \
  -scheme $SCHEME \
  -sdk $SDK \
  build

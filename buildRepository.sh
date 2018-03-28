#!/bin/bash
curl https://repo1.maven.org/maven2/org/apache/maven/apache-maven/3.5.0/apache-maven-3.5.0-bin.zip -o ./.mvn/wrapper/mvn-bin.zip
curl https://download-cf.jetbrains.com/idea/ideaIC-2017.3.5.dmg -o ./intelliJ/ideaIC-2017.3.5.dmg
curl https://download-cf.jetbrains.com/idea/ideaIC-2017.3.5.tar.gz -o ./intelliJ/ideaIC-2017.3.5.tar.gz
curl https://download-cf.jetbrains.com/idea/ideaIC-2017.3.5.exe -o ./intelliJ/ideaIC-2017.3.5.exe
./mvnw clean install dependency:go-offline -Dmaven.repo.local=repository -Pdev -f workaround.xml
./mvnw clean install dependency:go-offline -Dmaven.repo.local=repository -Dmaven.test.failure.ignore=true -Pdev

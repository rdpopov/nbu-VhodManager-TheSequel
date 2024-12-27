default: run

env:
	JAVA_HOME="/usr/lib/jvm/java-21-openjdk-amd64" &&  \
	cd /home/rosko/dev/nbu_java

all: env
	./gradlew clean build; 

test: env
	./gradlew clean test;

clean: env
	./gradlew clean;

run: env
	./gradlew clean run

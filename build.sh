set -e

git pull
mvn clean package
mkdir -p output

mkdir -p output/anymock-web
cp web/runner/target/anymock-web-runner-1.0-SNAPSHOT.jar output/anymock-web
cp web/runner/src/main/resources/* output/anymock-web

mkdir -p output/anymock-core
cp core/runner/target/anymock-core-runner-1.0-SNAPSHOT.jar output/anymock-core
cp core/runner/src/main/resources/* output/anymock-output
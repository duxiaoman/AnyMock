set -e

git pull
mvn clean package
mkdir -p output

cp web/runner/target/anymock-web-runner-1.0-SNAPSHOT.jar output/
cp web/runner/src/main/resources/* output/

cp core/runner/target/anymock-core-runner-1.0-SNAPSHOT.jar output/
cp core/runner/src/main/resources/* output/
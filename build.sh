set -e

git pull
mvn clean package
mkdir -p output

cp manager/runner/target/anymock-manager-runner-1.0-SNAPSHOT.jar output/
cp manager/runner/src/main/resources/* output/

cp core/runner/target/anymock-core-runner-1.0-SNAPSHOT.jar output/
cp core/runner/src/main/resources/* output/
set -e

git pull
mvn clean package
mkdir -p output


mkdir -p output/manager
cp manager/runner/target/anymock-manager-runner-1.0-SNAPSHOT.jar output/manager

mkdir -p output/core
cp core/runner/target/anymock-core-runner-1.0-SNAPSHOT.jar output/core
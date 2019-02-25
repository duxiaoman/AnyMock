set -e

git pull
cd app
mvn clean package

mkdir -p ~/anymock-web-runtime
mkdir -p ~/anymock-core-runtime

cp web/runner/target/anymock-web-runner-1.0-SNAPSHOT.jar ~/anymock-web-runtime/
cp core/runner/target/anymock-core-runner-1.0-SNAPSHOT.jar ~/anymock-core-runtime/

cd ~/anymock-web-runtime/
nohup java -jar anymock-web-runner-1.0-SNAPSHOT.jar  > /dev/null 2>/dev/null &

cd ~/anymock-core-runtime/
nohup java -jar anymock-core-runner-1.0-SNAPSHOT.jar > /dev/null 2>/dev/null &
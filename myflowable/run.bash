echo "-> Run bash individually - Type 'myflowablebuild' to build the project"
myflowablebuild() {
  echo "mvn clean package"
  mvn clean package
}
echo "-> Run bash individually - Type 'myflowablerun' to run the executable"
myflowablerun() {
  echo "Running executable > mvn spring-boot:run"
  mvn spring-boot:run
}

myflowablebuild
myflowablerun
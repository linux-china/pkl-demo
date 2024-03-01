build:
   mvn -DskipTests clean package

pkl-eval:
   pkl eval -f json demo.pkl

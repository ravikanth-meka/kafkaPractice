# kafkaPractice

c:\MyInstallations\kafka_2.12-2.1.0>zookeeper-server-start config\zookeeper.properties

c:\MyInstallations\kafka_2.12-2.1.0>kafka-server-start config\server.properties

http://localhost:8081/swagger-ui.html#!/kafka-producer-controller

http://localhost:8082/swagger-ui.html#!/kafka-subscriber-controller

kafka-topics --zookeeper localhost:2181 --list

kafka-console-consumer --bootstrap-server localhost:9092 --topic mynewtopic --from-beginning --group mygrpid



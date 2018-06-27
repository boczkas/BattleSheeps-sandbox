#Adding admin user to RabbitMQ
- rabbitmqctl add_user test test
- rabbitmqctl set_user_tags test administrator
- rabbitmqctl set_permissions -p / test ".\*" ".\*" ".\*"

#Installation of RabbitMQ on Linux
- https://www.digitalocean.com/community/tutorials/how-to-install-and-manage-rabbitmq
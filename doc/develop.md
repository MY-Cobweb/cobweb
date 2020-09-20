# 开发者文档
## 开发环境准备
1. 安装Docker, 如果已安装docker，请忽略这个步骤
``` shell script
sudo apt-get install docker
sudo systemctl start docker
```
2. 安装ClickHouse
``` shell script
sudo docker pull yandex/clickhouse-server
sudo docker run -d -p 8123:8123 --name some-clickhouse-server  --ulimit nofile=262144:262144  yandex/clickhouse-serve
```
3. 安装MYSQL
```shell script
sudo docker pull mysql/mysql-server
sudo docker run --name=mysql -e MYSQL_ROOT_PASSWORD=cobweb -e MYSQL_DATABASE=cobweb -e MYSQL_USER=cobweb -e MYSQL_PASSWORD=cobweb -p 3306:3306 -d mysql/mysql-server
```
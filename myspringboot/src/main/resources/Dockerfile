FROM ascdc/jdk8
#FROM openjdk:8-jdk-alpine
## saltice-sc-admin-1.0-SNAPSHOT.jar是我项目打包后生成的可运行的jar
## saltice-sc.jar 是在构建镜像里面的名称，就是把前面那个文件从上下文目录放到了镜像里面的根目录去了
ADD myspringboot-0.0.1-SNAPSHOT.jar myspringboot.jar
MAINTAINER SaltIce
# 暴露端口
#EXPOSE 5052
## 运行命令组装一下啦
ENTRYPOINT ["java","-Dfile.encoding=utf-8","-jar","myspringboot.jar"]


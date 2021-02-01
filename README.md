## zookeeper 源码学习

### 1、下载并安装ant 
    本人采用 version 1.9.6 
    
### 2、配置ant环境变量
    ANT_HOME = D:\Program Files\apache-ant-1.9.6
    Path = ;%ANT_HOME%\bin
    
### 3、编译项目
    # ant eclipse
    如下所示：出现“BUILD SUCCESSFUL”，即代表编译完成。
    
   ![](https://github.com/yangshj/zookeeper-3.4.12/blob/master/image/ant.png)
    
### 4、用idea导入项目，选择eclipse项目
    略
    
### 5、配置项目
    项目启动入口类为 org.apache.zookeeper.server.quorum.QuorumPeerMain
    如下图所示配置参数
    
   ![](https://github.com/yangshj/zookeeper-3.4.12/blob/master/image/main.png)


    
    
   

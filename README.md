<h1 align="center">Welcome to MelodyUserFinder </h1>
<p align="center">
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
  <img alt="Download" src="https://img.shields.io/github/downloads/yuuiyu/MelodyUserFinder/total"/>
  <img alt="V" src="https://img.shields.io/badge/Java-8-green" style=""/>
  <img alt="M" src="https://img.shields.io/badge/MinecraftForge-1.8.9-yellow" style=""/>
</p>



> A Minecraft Mod for finding Melody-User in Hypixel-SkyBlock
> 裤子删库了id现在不是最新的 4/25等我！

## Prerequisites

- Minecraft forge 1.8.9
- Java version 8

## Install

```java
File GET /file/para/ 可变参数的文件请求
File GET /file/target/ 直接的的文件请求
可变参数文件请求{file:"xxx.jar",encryption:"true",count:3,sign:"可以是一段文字用于标记文件"}

Auth POST /auth/signup 登陆
Auth POST /auth/signin 注册
Auth POST AToken /authme/accesstoken 用于验证accesstoken
Auth POST AToken /authme/uuid 用于验证UUID是否被验证

IRC Websocket AToken /irc/connect 
IRC POST AToken /irc/message 发送irc消息
IRC POST AToken /irc/data 请求用户数据
```

## Usage

```sh
/melodyfinder toggle
/melodyfinder debug
/melodyfinder reload
/melodyfinder recheck
```

## Author

👤 **Yuuiyu**

* Github: [@yuuiyu](https://github.com/yuuiyu)

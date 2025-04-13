<div align="center">
<h1 align="center" style="margin-top: 0">TIM_MYSQL</h1>
<p align="center">
<strong>自动导入AH数据至MYSQL，可自定义数据仓库</strong>

[![GitHub](https://img.shields.io/badge/-GitHub-181717?logo=github)](https://github.com/SIMM93/TIM_MYSQL)
![GitHub License](https://img.shields.io/github/license/SIMM93/TIM_MYSQL)
[![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/SIMM93/TIM_MYSQL?color=blue&label=download&sort=semver)](https://github.com/SIMM93/TIM_MYSQL/releases/latest)




</div>



## 环境要求
Java 1.8 下载地址 https://repo.huaweicloud.com/java/jdk/8u151-b12/jdk-8u151-windows-x64.exe

请提前配置好数据库，库表文件在 其他/数据库 文件夹下，数据库名WOW，默认地址 localhost:3306/WOW 

## 使用方式
1. 安装目录中的TSM插件至游戏（4.13修复版可扫描、上架）
2. 进入游戏扫描拍卖行/TSMSCAN
3. 下线
4. 配置TsmEConfig.txt  主要是输入tsm扫描后的文件
5. 双击WOW_TSM-MYSQL_1.0.bat
6. 项目会自动插入最新扫描的AH数据，并每5分钟定时插入（如有更新），且返回简要信息，这里可以自行定义。（当前默认返回倒卖数据。目录下有示例） 

## 爱发电

<a href="https://afdian.com/a/Gazlowe" target="_blank">
  <img src="https://github.com/SIMM93/TIM_MYSQL/blob/main/support_aifadian.svg" alt="support_aifadian">
</a>

若是项目对您有帮助，可以到爱发电为我买杯咖啡。
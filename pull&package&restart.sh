#!/bin/sh
echo"开始获取最新代码"
git pull origin master
git add .
git commit -m":sparkles: feat:增加图片上传到七牛云的模块"
git push origin master
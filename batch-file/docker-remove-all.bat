@rem docker-remove-all.bat
@echo off

REM for /f "tokens=*" %%i in ('docker ps -aq') do docker rm %%i
powershell docker rm $(docker ps -aq)

REM for /f "tokens=*" %%i in ('docker images -aq') do docker rmi %%i
powershell docker rmi $(docker images -aq)
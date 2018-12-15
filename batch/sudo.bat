@rem sudo.bat
@echo off
set COMMAND=%1

if "%COMMAND%"=="cmd" goto promt

:issue
	echo '%COMMAND%' is not recognized as an internal or external command,
	echo operable program or batch file.
	goto end

:promt
	powershell "start cmd -v runAS"
	goto end
	
:end

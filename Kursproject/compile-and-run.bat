@echo off
chcp 65001 >nul
set SRC=src\com\course\warehouse
set OUT=out
if not exist %OUT% mkdir %OUT%

javac -encoding UTF-8 -d %OUT% -sourcepath src ^
  %SRC%\Documentable.java ^
  %SRC%\WarehouseApp.java ^
  %SRC%\exception\RequestNotFoundException.java ^
  %SRC%\model\RequestStatus.java ^
  %SRC%\model\StorageRequest.java ^
  %SRC%\model\InboundRequest.java ^
  %SRC%\model\OutboundRequest.java ^
  %SRC%\service\Warehouse.java

if errorlevel 1 (
  echo Compilation failed. Install JDK and add javac to PATH.
  exit /b 1
)

java -classpath %OUT% com.course.warehouse.WarehouseApp

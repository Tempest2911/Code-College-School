@echo off
cd /d "D:\CODE CAO DANG"
git add .
git commit -m "Auto commit %date% %time%"
git pull --rebase origin main
git push origin main
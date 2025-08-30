# UTE PhoneHub - Demo Script
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "     UTE PHONEHUB - PROJECT DEMO SCRIPT        " -ForegroundColor Yellow
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "Backend:   Java Servlet + Maven                " -ForegroundColor Green
Write-Host "Frontend:  ReactJS + TypeScript                " -ForegroundColor Blue
Write-Host "Scripts:   PowerShell Management Tools         " -ForegroundColor Magenta
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

$PROJECT_ROOT = $PSScriptRoot | Split-Path -Parent

Write-Host "DEMO: Building and Running UTE PhoneHub..." -ForegroundColor Yellow
Write-Host ""

# Step 1: Check status
Write-Host "Step 1: Checking project status..." -ForegroundColor Cyan
& "$PROJECT_ROOT\scripts\manager.ps1" status
Write-Host ""

# Step 2: Build backend
Write-Host "Step 2: Building backend..." -ForegroundColor Cyan
& "$PROJECT_ROOT\scripts\backend\backend.ps1" build
Write-Host ""

# Step 3: Build frontend
Write-Host "Step 3: Building frontend..." -ForegroundColor Cyan
& "$PROJECT_ROOT\scripts\frontend\frontend.ps1" build
Write-Host ""

# Step 4: Show results
Write-Host "DEMO COMPLETED!" -ForegroundColor Green
Write-Host ""
Write-Host "Build Results:" -ForegroundColor Yellow

$backendWar = Join-Path $PROJECT_ROOT "backend\target\ute-phonehub.war"
if (Test-Path $backendWar) {
    $size = (Get-Item $backendWar).Length / 1KB
    Write-Host "  Backend WAR: $([math]::Round($size, 2)) KB" -ForegroundColor Green
} else {
    Write-Host "  Backend WAR: Not found" -ForegroundColor Red
}

$frontendBuild = Join-Path $PROJECT_ROOT "frontend\build"
if (Test-Path $frontendBuild) {
    $buildSize = (Get-ChildItem $frontendBuild -Recurse | Measure-Object -Property Length -Sum).Sum / 1KB
    Write-Host "  Frontend Build: $([math]::Round($buildSize, 2)) KB" -ForegroundColor Green
} else {
    Write-Host "  Frontend Build: Not found" -ForegroundColor Red
}

Write-Host ""
Write-Host "Available Commands:" -ForegroundColor Yellow
Write-Host "  .\scripts\manager.ps1 status" -ForegroundColor Gray
Write-Host "  .\scripts\backend\backend.ps1 build" -ForegroundColor Gray
Write-Host "  .\scripts\frontend\frontend.ps1 dev" -ForegroundColor Gray
Write-Host "  .\scripts\frontend\frontend.ps1 build" -ForegroundColor Gray
Write-Host ""
Write-Host "Frontend is already running at: http://localhost:3000" -ForegroundColor Green
Write-Host ""

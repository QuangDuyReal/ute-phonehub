# UTE PhoneHub - Simple Project Manager
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help",
    [Parameter(Mandatory=$false)]
    [string]$Service = "all"
)

$SCRIPT_PATH = $PSScriptRoot

function Show-Help {
    Write-Host "UTE PHONEHUB - PROJECT MANAGER" -ForegroundColor Yellow
    Write-Host "==============================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "MAIN ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help     - Show this help" -ForegroundColor Gray
    Write-Host "  setup    - Setup development environment" -ForegroundColor Gray
    Write-Host "  start    - Start development servers" -ForegroundColor Gray
    Write-Host "  stop     - Stop all servers" -ForegroundColor Gray
    Write-Host "  build    - Build all components" -ForegroundColor Gray
    Write-Host "  test     - Run all tests" -ForegroundColor Gray
    Write-Host "  clean    - Clean all artifacts" -ForegroundColor Gray
    Write-Host "  deploy   - Deploy to production" -ForegroundColor Gray
    Write-Host "  status   - Show project status" -ForegroundColor Gray
    Write-Host ""
    Write-Host "COMPONENT SCRIPTS:" -ForegroundColor Yellow
    Write-Host "  backend  - Backend management (.\\scripts\\backend\\backend.ps1)" -ForegroundColor Gray
    Write-Host "  frontend - Frontend management (.\\scripts\\frontend\\frontend.ps1)" -ForegroundColor Gray
    Write-Host "  database - Database management (.\\scripts\\database\\database.ps1)" -ForegroundColor Gray
    Write-Host "  docker   - Docker management (.\\scripts\\docker\\docker.ps1)" -ForegroundColor Gray
    Write-Host "  deploy   - Deployment management (.\\scripts\\deployment\\deploy.ps1)" -ForegroundColor Gray
    Write-Host "  utils    - Utilities (.\\scripts\\utils\\utils.ps1)" -ForegroundColor Gray
    Write-Host ""
    Write-Host "EXAMPLES:" -ForegroundColor Yellow
    Write-Host "  .\\manager.ps1 setup" -ForegroundColor Gray
    Write-Host "  .\\manager.ps1 start" -ForegroundColor Gray
    Write-Host "  .\\manager.ps1 backend build" -ForegroundColor Gray
    Write-Host "  .\\manager.ps1 frontend dev" -ForegroundColor Gray
    Write-Host ""
}

function Setup-Project {
    Write-Host "SETTING UP PROJECT..." -ForegroundColor Yellow
    
    & "$SCRIPT_PATH\utils\utils.ps1" setup
}

function Start-Development {
    Write-Host "STARTING DEVELOPMENT SERVERS..." -ForegroundColor Yellow
    
    Write-Host "Starting backend..." -ForegroundColor Cyan
    Start-Process PowerShell -ArgumentList "-Command", "cd '$SCRIPT_PATH\..' ; .\scripts\backend\backend.ps1 run" -WindowStyle Normal
    
    Start-Sleep -Seconds 3
    
    Write-Host "Starting frontend..." -ForegroundColor Cyan
    Start-Process PowerShell -ArgumentList "-Command", "cd '$SCRIPT_PATH\..' ; .\scripts\frontend\frontend.ps1 dev" -WindowStyle Normal
    
    Write-Host ""
    Write-Host " Development servers started!" -ForegroundColor Green
    Write-Host "Frontend: http://localhost:3000" -ForegroundColor Green
    Write-Host "Backend:  http://localhost:8080/ute-phonehub" -ForegroundColor Green
}

function Stop-AllServers {
    Write-Host "STOPPING ALL SERVERS..." -ForegroundColor Yellow
    
    # Stop Java processes (backend)
    Write-Host "Stopping backend servers..." -ForegroundColor Cyan
    Get-Process -Name "java" -ErrorAction SilentlyContinue | Where-Object { $_.CommandLine -like "*jetty*" -or $_.CommandLine -like "*tomcat*" } | Stop-Process -Force
    
    # Stop Node processes (frontend)
    Write-Host "Stopping frontend servers..." -ForegroundColor Cyan
    Get-Process -Name "node" -ErrorAction SilentlyContinue | Where-Object { $_.CommandLine -like "*react-scripts*" } | Stop-Process -Force
    
    Write-Host " All servers stopped!" -ForegroundColor Green
}

function Build-All {
    Write-Host "BUILDING ALL COMPONENTS..." -ForegroundColor Yellow
    
    Write-Host "Building backend..." -ForegroundColor Cyan
    & "$SCRIPT_PATH\backend\backend.ps1" build
    
    Write-Host "Building frontend..." -ForegroundColor Cyan
    & "$SCRIPT_PATH\frontend\frontend.ps1" build
    
    Write-Host " Build completed!" -ForegroundColor Green
}

function Test-All {
    Write-Host "RUNNING ALL TESTS..." -ForegroundColor Yellow
    
    & "$SCRIPT_PATH\utils\utils.ps1" test
}

function Clean-All {
    Write-Host "CLEANING ALL ARTIFACTS..." -ForegroundColor Yellow
    
    & "$SCRIPT_PATH\utils\utils.ps1" clean
}

function Deploy-Production {
    Write-Host "DEPLOYING TO PRODUCTION..." -ForegroundColor Yellow
    
    & "$SCRIPT_PATH\deployment\deploy.ps1" deploy -Environment railway
}

function Show-ProjectStatus {
    Write-Host "PROJECT STATUS" -ForegroundColor Yellow
    Write-Host "==============" -ForegroundColor Cyan
    Write-Host ""
    
    # Check if backend is running
    Write-Host "Backend Status:" -ForegroundColor Cyan
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:8080/ute-phonehub" -UseBasicParsing -TimeoutSec 5
        Write-Host "   Backend running at http://localhost:8080/ute-phonehub" -ForegroundColor Green
    } catch {
        Write-Host "   Backend not running" -ForegroundColor Red
    }
    
    # Check if frontend is running
    Write-Host "Frontend Status:" -ForegroundColor Cyan
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:3000" -UseBasicParsing -TimeoutSec 5
        Write-Host "   Frontend running at http://localhost:3000" -ForegroundColor Green
    } catch {
        Write-Host "   Frontend not running" -ForegroundColor Red
    }
    
    Write-Host ""
    Write-Host "Available Scripts:" -ForegroundColor Yellow
    Write-Host "  Backend:  .\scripts\backend\backend.ps1 [action]" -ForegroundColor Gray
    Write-Host "  Frontend: .\scripts\frontend\frontend.ps1 [action]" -ForegroundColor Gray
    Write-Host "  Database: .\scripts\database\database.ps1 [action]" -ForegroundColor Gray
    Write-Host "  Docker:   .\scripts\docker\docker.ps1 [action]" -ForegroundColor Gray
    Write-Host "  Deploy:   .\scripts\deployment\deploy.ps1 [action]" -ForegroundColor Gray
    Write-Host "  Utils:    .\scripts\utils\utils.ps1 [action]" -ForegroundColor Gray
}

# Handle component-specific actions
if ($Action -in @("backend", "frontend", "database", "docker", "deploy", "utils")) {
    $component = $Action
    $componentAction = $Service
    
    switch ($component) {
        "backend" { & "$SCRIPT_PATH\backend\backend.ps1" $componentAction }
        "frontend" { & "$SCRIPT_PATH\frontend\frontend.ps1" $componentAction }
        "database" { & "$SCRIPT_PATH\database\database.ps1" $componentAction }
        "docker" { & "$SCRIPT_PATH\docker\docker.ps1" $componentAction }
        "deploy" { & "$SCRIPT_PATH\deployment\deploy.ps1" $componentAction }
        "utils" { & "$SCRIPT_PATH\utils\utils.ps1" $componentAction }
    }
    exit
}

# Handle main actions
switch ($Action.ToLower()) {
    "help" { Show-Help }
    "setup" { Setup-Project }
    "start" { Start-Development }
    "stop" { Stop-AllServers }
    "build" { Build-All }
    "test" { Test-All }
    "clean" { Clean-All }
    "deploy" { Deploy-Production }
    "status" { Show-ProjectStatus }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

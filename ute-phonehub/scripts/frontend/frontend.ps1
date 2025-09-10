# UTE PhoneHub - Frontend Management Script
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help"
)

$PROJECT_ROOT = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$FRONTEND_PATH = Join-Path $PROJECT_ROOT "frontend"

function Show-Help {
    Write-Host "FRONTEND MANAGEMENT" -ForegroundColor Yellow
    Write-Host "===================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help     - Show this help" -ForegroundColor Gray
    Write-Host "  install  - Install dependencies (npm install)" -ForegroundColor Gray
    Write-Host "  dev      - Start development server (npm start)" -ForegroundColor Gray
    Write-Host "  build    - Build production (npm run build)" -ForegroundColor Gray
    Write-Host "  test     - Run tests (npm test)" -ForegroundColor Gray
    Write-Host "  clean    - Clean node_modules and build" -ForegroundColor Gray
    Write-Host ""
}

function Test-Environment {
    Write-Host "Checking frontend environment..." -ForegroundColor Cyan
    
    try {
        $nodeVersion = node --version 2>&1
        Write-Host "  Node.js: $nodeVersion" -ForegroundColor Green
    } catch {
        Write-Host "  Node.js not found. Please install Node.js 16+" -ForegroundColor Red
        return $false
    }
    
    try {
        $npmVersion = npm --version 2>&1
        Write-Host "  npm: $npmVersion" -ForegroundColor Green
    } catch {
        Write-Host "  npm not found" -ForegroundColor Red
        return $false
    }
    
    if (!(Test-Path $FRONTEND_PATH)) {
        Write-Host "  Frontend folder not found: $FRONTEND_PATH" -ForegroundColor Red
        return $false
    }
    Write-Host "  Frontend folder: $FRONTEND_PATH" -ForegroundColor Green
    
    $packageJson = Join-Path $FRONTEND_PATH "package.json"
    if (!(Test-Path $packageJson)) {
        Write-Host "  package.json not found" -ForegroundColor Red
        return $false
    }
    Write-Host "  package.json found" -ForegroundColor Green
    
    return $true
}

function Install-Dependencies {
    Write-Host "INSTALLING DEPENDENCIES..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $FRONTEND_PATH
    
    Write-Host "npm install..." -ForegroundColor Cyan
    npm install
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Dependencies installed!" -ForegroundColor Green
    } else {
        Write-Host "Installation failed!" -ForegroundColor Red
    }
}

function Start-DevServer {
    Write-Host "STARTING DEVELOPMENT SERVER..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $FRONTEND_PATH
    
    if (!(Test-Path "node_modules")) {
        Write-Host "node_modules not found. Installing dependencies..." -ForegroundColor Yellow
        npm install
    }
    
    Write-Host "Starting React dev server..." -ForegroundColor Cyan
    Write-Host "URL: http://localhost:3000" -ForegroundColor Green
    
    npm start
}

function Build-Production {
    Write-Host "BUILDING FOR PRODUCTION..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $FRONTEND_PATH
    
    if (!(Test-Path "node_modules")) {
        Write-Host "node_modules not found. Installing dependencies..." -ForegroundColor Yellow
        npm install
    }
    
    Write-Host "npm run build..." -ForegroundColor Cyan
    npm run build
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Build successful!" -ForegroundColor Green
        $buildPath = Join-Path $FRONTEND_PATH "build"
        Write-Host "Build folder: $buildPath" -ForegroundColor Green
    } else {
        Write-Host "Build failed!" -ForegroundColor Red
    }
}

function Run-Tests {
    Write-Host "RUNNING TESTS..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $FRONTEND_PATH
    
    Write-Host "npm test..." -ForegroundColor Cyan
    npm test -- --watchAll=false --coverage
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Tests passed!" -ForegroundColor Green
    } else {
        Write-Host "Tests failed!" -ForegroundColor Red
    }
}

function Clean-Frontend {
    Write-Host "CLEANING FRONTEND..." -ForegroundColor Yellow
    
    Set-Location $FRONTEND_PATH
    
    if (Test-Path "node_modules") {
        Write-Host "Removing node_modules..." -ForegroundColor Cyan
        Remove-Item "node_modules" -Recurse -Force
        Write-Host "node_modules removed" -ForegroundColor Green
    }
    
    if (Test-Path "build") {
        Write-Host "Removing build folder..." -ForegroundColor Cyan
        Remove-Item "build" -Recurse -Force
        Write-Host "build folder removed" -ForegroundColor Green
    }
    
    if (Test-Path "package-lock.json") {
        Write-Host "Removing package-lock.json..." -ForegroundColor Cyan
        Remove-Item "package-lock.json" -Force
        Write-Host "package-lock.json removed" -ForegroundColor Green
    }
    
    Write-Host "Frontend cleaned!" -ForegroundColor Green
}

# Main execution
switch ($Action.ToLower()) {
    "help" { Show-Help }
    "install" { Install-Dependencies }
    "dev" { Start-DevServer }
    "build" { Build-Production }
    "test" { Run-Tests }
    "clean" { Clean-Frontend }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

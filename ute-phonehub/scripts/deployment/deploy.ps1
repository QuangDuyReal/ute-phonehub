# UTE PhoneHub - Deployment Management Script
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help",
    [Parameter(Mandatory=$false)]
    [string]$Environment = "railway",  # railway, heroku, aws, local
    [Parameter(Mandatory=$false)]
    [string]$Service = "all"  # all, backend, frontend
)

$PROJECT_ROOT = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$BACKEND_PATH = Join-Path $PROJECT_ROOT "backend"
$FRONTEND_PATH = Join-Path $PROJECT_ROOT "frontend"

function Show-Help {
    Write-Host "DEPLOYMENT MANAGEMENT" -ForegroundColor Yellow
    Write-Host "====================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help      - Show this help" -ForegroundColor Gray
    Write-Host "  build     - Build for deployment" -ForegroundColor Gray
    Write-Host "  deploy    - Deploy to platform" -ForegroundColor Gray
    Write-Host "  status    - Check deployment status" -ForegroundColor Gray
    Write-Host "  logs      - Show deployment logs" -ForegroundColor Gray
    Write-Host "  rollback  - Rollback to previous version" -ForegroundColor Gray
    Write-Host "  env       - Manage environment variables" -ForegroundColor Gray
    Write-Host ""
    Write-Host "ENVIRONMENTS:" -ForegroundColor Yellow
    Write-Host "  -Environment railway  - Railway.app (default)" -ForegroundColor Gray
    Write-Host "  -Environment heroku   - Heroku" -ForegroundColor Gray
    Write-Host "  -Environment aws      - AWS" -ForegroundColor Gray
    Write-Host "  -Environment local    - Local deployment" -ForegroundColor Gray
    Write-Host ""
    Write-Host "SERVICES:" -ForegroundColor Yellow
    Write-Host "  -Service all       - Deploy both (default)" -ForegroundColor Gray
    Write-Host "  -Service backend   - Backend only" -ForegroundColor Gray
    Write-Host "  -Service frontend  - Frontend only" -ForegroundColor Gray
    Write-Host ""
    Write-Host "EXAMPLES:" -ForegroundColor Yellow
    Write-Host "  .\deploy.ps1 build" -ForegroundColor Gray
    Write-Host "  .\deploy.ps1 deploy -Environment railway" -ForegroundColor Gray
    Write-Host "  .\deploy.ps1 status -Service backend" -ForegroundColor Gray
    Write-Host ""
}

function Test-DeploymentEnvironment {
    param([string]$Platform)
    
    Write-Host "Checking deployment environment for $Platform..." -ForegroundColor Cyan
    
    switch ($Platform.ToLower()) {
        "railway" {
            try {
                $railwayVersion = railway --version 2>&1
                Write-Host "  Railway CLI: $railwayVersion" -ForegroundColor Green
                return $true
            } catch {
                Write-Host "  Railway CLI not found. Install from: https://railway.app/cli" -ForegroundColor Red
                return $false
            }
        }
        "heroku" {
            try {
                $herokuVersion = heroku --version 2>&1
                Write-Host "  Heroku CLI: $herokuVersion" -ForegroundColor Green
                return $true
            } catch {
                Write-Host "  Heroku CLI not found. Install from: https://devcenter.heroku.com/articles/heroku-cli" -ForegroundColor Red
                return $false
            }
        }
        "aws" {
            try {
                $awsVersion = aws --version 2>&1
                Write-Host "  AWS CLI: $awsVersion" -ForegroundColor Green
                return $true
            } catch {
                Write-Host "  AWS CLI not found. Install from: https://aws.amazon.com/cli/" -ForegroundColor Red
                return $false
            }
        }
        "local" {
            Write-Host "  Local deployment - no CLI required" -ForegroundColor Green
            return $true
        }
        default {
            Write-Host "  Unknown platform: $Platform" -ForegroundColor Red
            return $false
        }
    }
}

function Build-ForDeployment {
    param([string]$ServiceFilter, [string]$Platform)
    
    Write-Host "BUILDING FOR DEPLOYMENT..." -ForegroundColor Yellow
    Write-Host "Platform: $Platform" -ForegroundColor Gray
    Write-Host "Service: $ServiceFilter" -ForegroundColor Gray
    Write-Host ""
    
    if ($ServiceFilter -eq "all" -or $ServiceFilter -eq "backend") {
        Write-Host "Building Backend..." -ForegroundColor Cyan
        Set-Location $BACKEND_PATH
        
        # Clean and build WAR file
        mvn clean package -DskipTests
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "  ✅ Backend build successful" -ForegroundColor Green
        } else {
            Write-Host "  ❌ Backend build failed" -ForegroundColor Red
            return
        }
    }
    
    if ($ServiceFilter -eq "all" -or $ServiceFilter -eq "frontend") {
        Write-Host "Building Frontend..." -ForegroundColor Cyan
        Set-Location $FRONTEND_PATH
        
        # Set production environment
        if ($Platform -eq "railway") {
            $env:REACT_APP_API_BASE_URL = "https://ute-phonehub-testdeploy-production.up.railway.app/ute-phonehub"
        }
        
        # Install and build
        npm ci
        npm run build
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "  ✅ Frontend build successful" -ForegroundColor Green
        } else {
            Write-Host "  ❌ Frontend build failed" -ForegroundColor Red
            return
        }
    }
    
    Write-Host "Build completed successfully!" -ForegroundColor Green
}

function Deploy-ToRailway {
    param([string]$ServiceFilter)
    
    Write-Host "DEPLOYING TO RAILWAY..." -ForegroundColor Yellow
    
    if (!(Test-DeploymentEnvironment "railway")) {
        return
    }
    
    # Login check
    railway whoami 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Please login to Railway first: railway login" -ForegroundColor Red
        return
    }
    
    Set-Location $PROJECT_ROOT
    
    if ($ServiceFilter -eq "all") {
        Write-Host "Deploying both services..." -ForegroundColor Cyan
        
        # Deploy using railway.json configuration
        railway up
        
    } elseif ($ServiceFilter -eq "backend") {
        Write-Host "Deploying backend only..." -ForegroundColor Cyan
        Set-Location $BACKEND_PATH
        railway up
        
    } elseif ($ServiceFilter -eq "frontend") {
        Write-Host "Deploying frontend only..." -ForegroundColor Cyan
        Set-Location $FRONTEND_PATH
        railway up
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Deployment completed successfully!" -ForegroundColor Green
        Show-DeploymentUrls "railway"
    } else {
        Write-Host "Deployment failed!" -ForegroundColor Red
    }
}

function Deploy-ToHeroku {
    param([string]$ServiceFilter)
    
    Write-Host "DEPLOYING TO HEROKU..." -ForegroundColor Yellow
    
    if (!(Test-DeploymentEnvironment "heroku")) {
        return
    }
    
    # Login check
    heroku auth:whoami 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Please login to Heroku first: heroku login" -ForegroundColor Red
        return
    }
    
    Write-Host "Heroku deployment not fully implemented yet." -ForegroundColor Yellow
    Write-Host "Please configure Heroku apps manually." -ForegroundColor Gray
}

function Deploy-Local {
    param([string]$ServiceFilter)
    
    Write-Host "LOCAL DEPLOYMENT..." -ForegroundColor Yellow
    
    # Build first
    Build-ForDeployment $ServiceFilter "local"
    
    # Start services locally
    if ($ServiceFilter -eq "all" -or $ServiceFilter -eq "backend") {
        Write-Host "Starting backend locally..." -ForegroundColor Cyan
        Start-Process PowerShell -ArgumentList "-Command", "cd '$PROJECT_ROOT'; .\scripts\backend\backend.ps1 run" -WindowStyle Normal
    }
    
    if ($ServiceFilter -eq "all" -or $ServiceFilter -eq "frontend") {
        Write-Host "Starting frontend locally..." -ForegroundColor Cyan
        Start-Process PowerShell -ArgumentList "-Command", "cd '$PROJECT_ROOT'; .\scripts\frontend\frontend.ps1 dev" -WindowStyle Normal
    }
    
    Write-Host "Local deployment started!" -ForegroundColor Green
    Show-DeploymentUrls "local"
}

function Show-DeploymentStatus {
    param([string]$Platform, [string]$ServiceFilter)
    
    Write-Host "DEPLOYMENT STATUS" -ForegroundColor Yellow
    Write-Host "=================" -ForegroundColor Cyan
    Write-Host "Platform: $Platform" -ForegroundColor Gray
    Write-Host "Service: $ServiceFilter" -ForegroundColor Gray
    Write-Host ""
    
    switch ($Platform.ToLower()) {
        "railway" {
            if (Test-DeploymentEnvironment "railway") {
                railway status
            }
        }
        "heroku" {
            if (Test-DeploymentEnvironment "heroku") {
                heroku ps
            }
        }
        "local" {
            Write-Host "Local services:" -ForegroundColor Cyan
            Write-Host "  Backend:  Check http://localhost:8080/ute-phonehub" -ForegroundColor Gray
            Write-Host "  Frontend: Check http://localhost:3000" -ForegroundColor Gray
        }
    }
}

function Show-DeploymentLogs {
    param([string]$Platform, [string]$ServiceFilter)
    
    Write-Host "DEPLOYMENT LOGS" -ForegroundColor Yellow
    Write-Host "===============" -ForegroundColor Cyan
    
    switch ($Platform.ToLower()) {
        "railway" {
            if (Test-DeploymentEnvironment "railway") {
                railway logs
            }
        }
        "heroku" {
            if (Test-DeploymentEnvironment "heroku") {
                heroku logs --tail
            }
        }
        "local" {
            Write-Host "Local logs are shown in terminal windows" -ForegroundColor Gray
        }
    }
}

function Show-DeploymentUrls {
    param([string]$Platform)
    
    Write-Host ""
    Write-Host "DEPLOYMENT URLS:" -ForegroundColor Yellow
    
    switch ($Platform.ToLower()) {
        "railway" {
            Write-Host "  Frontend: https://ute-phonehub-frontend.up.railway.app" -ForegroundColor Green
            Write-Host "  Backend:  https://ute-phonehub-backend.up.railway.app/ute-phonehub" -ForegroundColor Green
        }
        "local" {
            Write-Host "  Frontend: http://localhost:3000" -ForegroundColor Green
            Write-Host "  Backend:  http://localhost:8080/ute-phonehub" -ForegroundColor Green
        }
        default {
            Write-Host "  URLs depend on your $Platform configuration" -ForegroundColor Gray
        }
    }
}

function Manage-Environment {
    param([string]$Platform)
    
    Write-Host "ENVIRONMENT VARIABLES" -ForegroundColor Yellow
    Write-Host "=====================" -ForegroundColor Cyan
    
    switch ($Platform.ToLower()) {
        "railway" {
            Write-Host "Railway environment variables:" -ForegroundColor Gray
            railway variables
        }
        "heroku" {
            Write-Host "Heroku config vars:" -ForegroundColor Gray
            heroku config
        }
        "local" {
            Write-Host "Local environment files:" -ForegroundColor Gray
            Write-Host "  Frontend: .env.local, .env.development, .env.production" -ForegroundColor Gray
            Write-Host "  Backend:  application.properties" -ForegroundColor Gray
        }
    }
}

# Main execution
switch ($Action.ToLower()) {
    "help" { Show-Help }
    "build" { Build-ForDeployment $Service $Environment }
    "deploy" {
        switch ($Environment.ToLower()) {
            "railway" { Deploy-ToRailway $Service }
            "heroku" { Deploy-ToHeroku $Service }
            "local" { Deploy-Local $Service }
            default { 
                Write-Host "Unsupported platform: $Environment" -ForegroundColor Red
                Show-Help
            }
        }
    }
    "status" { Show-DeploymentStatus $Environment $Service }
    "logs" { Show-DeploymentLogs $Environment $Service }
    "rollback" { 
        Write-Host "Rollback functionality depends on platform" -ForegroundColor Yellow
        Write-Host "Railway: Use Railway dashboard" -ForegroundColor Gray
        Write-Host "Heroku: heroku rollback" -ForegroundColor Gray
    }
    "env" { Manage-Environment $Environment }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

# UTE PhoneHub - Docker Management Script
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help",
    [Parameter(Mandatory=$false)]
    [string]$Service = "all"  # all, backend, frontend, db
)

$PROJECT_ROOT = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$BACKEND_PATH = Join-Path $PROJECT_ROOT "backend"
$FRONTEND_PATH = Join-Path $PROJECT_ROOT "frontend"

function Show-Help {
    Write-Host "DOCKER MANAGEMENT" -ForegroundColor Yellow
    Write-Host "=================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help      - Show this help" -ForegroundColor Gray
    Write-Host "  build     - Build Docker images" -ForegroundColor Gray
    Write-Host "  start     - Start containers" -ForegroundColor Gray
    Write-Host "  stop      - Stop containers" -ForegroundColor Gray
    Write-Host "  restart   - Restart containers" -ForegroundColor Gray
    Write-Host "  logs      - Show container logs" -ForegroundColor Gray
    Write-Host "  status    - Show container status" -ForegroundColor Gray
    Write-Host "  clean     - Remove containers and images" -ForegroundColor Gray
    Write-Host "  shell     - Open shell in container" -ForegroundColor Gray
    Write-Host ""
    Write-Host "SERVICES:" -ForegroundColor Yellow
    Write-Host "  -Service all       - All services (default)" -ForegroundColor Gray
    Write-Host "  -Service backend   - Backend only" -ForegroundColor Gray
    Write-Host "  -Service frontend  - Frontend only" -ForegroundColor Gray
    Write-Host "  -Service db        - Database only" -ForegroundColor Gray
    Write-Host ""
    Write-Host "EXAMPLES:" -ForegroundColor Yellow
    Write-Host "  .\docker.ps1 build" -ForegroundColor Gray
    Write-Host "  .\docker.ps1 start -Service backend" -ForegroundColor Gray
    Write-Host "  .\docker.ps1 logs -Service frontend" -ForegroundColor Gray
    Write-Host ""
}

function Test-DockerEnvironment {
    Write-Host "Checking Docker environment..." -ForegroundColor Cyan
    
    try {
        $dockerVersion = docker --version 2>&1
        Write-Host "  Docker: $dockerVersion" -ForegroundColor Green
    } catch {
        Write-Host "  Docker not found. Please install Docker Desktop" -ForegroundColor Red
        return $false
    }
    
    try {
        $composeVersion = docker-compose --version 2>&1
        Write-Host "  Docker Compose: $composeVersion" -ForegroundColor Green
    } catch {
        Write-Host "  Docker Compose not found" -ForegroundColor Red
        return $false
    }
    
    # Check if Docker is running
    docker info 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "  Docker daemon is not running. Please start Docker Desktop" -ForegroundColor Red
        return $false
    }
    
    Write-Host "  Docker is ready!" -ForegroundColor Green
    return $true
}

function Build-Images {
    param([string]$ServiceFilter)
    
    Write-Host "BUILDING DOCKER IMAGES..." -ForegroundColor Yellow
    
    if (!(Test-DockerEnvironment)) {
        return
    }
    
    Set-Location $PROJECT_ROOT
    
    switch ($ServiceFilter.ToLower()) {
        "backend" {
            Write-Host "Building backend image..." -ForegroundColor Cyan
            docker build -t ute-phonehub-backend ./backend
        }
        "frontend" {
            Write-Host "Building frontend image..." -ForegroundColor Cyan
            docker build -t ute-phonehub-frontend ./frontend
        }
        "all" {
            Write-Host "Building all images..." -ForegroundColor Cyan
            docker-compose build
        }
        default {
            Write-Host "Unknown service: $ServiceFilter" -ForegroundColor Red
            return
        }
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Build completed successfully!" -ForegroundColor Green
    } else {
        Write-Host "Build failed!" -ForegroundColor Red
    }
}

function Start-Containers {
    param([string]$ServiceFilter)
    
    Write-Host "STARTING CONTAINERS..." -ForegroundColor Yellow
    
    if (!(Test-DockerEnvironment)) {
        return
    }
    
    Set-Location $PROJECT_ROOT
    
    switch ($ServiceFilter.ToLower()) {
        "all" {
            Write-Host "Starting all services..." -ForegroundColor Cyan
            docker-compose up -d
        }
        default {
            Write-Host "Starting $ServiceFilter service..." -ForegroundColor Cyan
            docker-compose up -d $ServiceFilter
        }
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Containers started successfully!" -ForegroundColor Green
        Write-Host ""
        Show-ContainerStatus
    } else {
        Write-Host "Failed to start containers!" -ForegroundColor Red
    }
}

function Stop-Containers {
    param([string]$ServiceFilter)
    
    Write-Host "STOPPING CONTAINERS..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    switch ($ServiceFilter.ToLower()) {
        "all" {
            Write-Host "Stopping all services..." -ForegroundColor Cyan
            docker-compose down
        }
        default {
            Write-Host "Stopping $ServiceFilter service..." -ForegroundColor Cyan
            docker-compose stop $ServiceFilter
        }
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Containers stopped successfully!" -ForegroundColor Green
    } else {
        Write-Host "Failed to stop containers!" -ForegroundColor Red
    }
}

function Restart-Containers {
    param([string]$ServiceFilter)
    
    Write-Host "RESTARTING CONTAINERS..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    switch ($ServiceFilter.ToLower()) {
        "all" {
            Write-Host "Restarting all services..." -ForegroundColor Cyan
            docker-compose restart
        }
        default {
            Write-Host "Restarting $ServiceFilter service..." -ForegroundColor Cyan
            docker-compose restart $ServiceFilter
        }
    }
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Containers restarted successfully!" -ForegroundColor Green
    }
}

function Show-Logs {
    param([string]$ServiceFilter)
    
    Write-Host "SHOWING CONTAINER LOGS..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    switch ($ServiceFilter.ToLower()) {
        "all" {
            Write-Host "Showing logs for all services..." -ForegroundColor Cyan
            docker-compose logs -f
        }
        default {
            Write-Host "Showing logs for $ServiceFilter service..." -ForegroundColor Cyan
            docker-compose logs -f $ServiceFilter
        }
    }
}

function Show-ContainerStatus {
    Write-Host "CONTAINER STATUS:" -ForegroundColor Yellow
    Write-Host "=================" -ForegroundColor Cyan
    
    Set-Location $PROJECT_ROOT
    docker-compose ps
    
    Write-Host ""
    Write-Host "URLs:" -ForegroundColor Yellow
    Write-Host "  Frontend: http://localhost:3000" -ForegroundColor Gray
    Write-Host "  Backend:  http://localhost:8080/ute-phonehub" -ForegroundColor Gray
    Write-Host "  Database: localhost:3306" -ForegroundColor Gray
}

function Clean-Docker {
    param([string]$ServiceFilter)
    
    Write-Host "CLEANING DOCKER RESOURCES..." -ForegroundColor Yellow
    Write-Host "⚠️  This will remove containers and images!" -ForegroundColor Red
    
    $confirmation = Read-Host "Are you sure? Type 'yes' to continue"
    if ($confirmation -ne "yes") {
        Write-Host "Operation cancelled." -ForegroundColor Yellow
        return
    }
    
    Set-Location $PROJECT_ROOT
    
    # Stop and remove containers
    Write-Host "Stopping and removing containers..." -ForegroundColor Cyan
    docker-compose down --remove-orphans
    
    # Remove images
    if ($ServiceFilter.ToLower() -eq "all") {
        Write-Host "Removing images..." -ForegroundColor Cyan
        docker rmi ute-phonehub-backend ute-phonehub-frontend 2>$null
    }
    
    # Clean up unused resources
    Write-Host "Cleaning up unused Docker resources..." -ForegroundColor Cyan
    docker system prune -f
    
    Write-Host "Docker cleanup completed!" -ForegroundColor Green
}

function Open-Shell {
    param([string]$ServiceFilter)
    
    if ($ServiceFilter.ToLower() -eq "all") {
        Write-Host "Please specify a service: backend, frontend, or db" -ForegroundColor Red
        return
    }
    
    Write-Host "Opening shell in $ServiceFilter container..." -ForegroundColor Cyan
    
    Set-Location $PROJECT_ROOT
    
    switch ($ServiceFilter.ToLower()) {
        "backend" {
            docker-compose exec backend /bin/bash
        }
        "frontend" {
            docker-compose exec frontend /bin/sh
        }
        "db" {
            docker-compose exec db mysql -u root -p
        }
        default {
            Write-Host "Unknown service: $ServiceFilter" -ForegroundColor Red
        }
    }
}

# Main execution
switch ($Action.ToLower()) {
    "help" { Show-Help }
    "build" { Build-Images $Service }
    "start" { Start-Containers $Service }
    "stop" { Stop-Containers $Service }
    "restart" { Restart-Containers $Service }
    "logs" { Show-Logs $Service }
    "status" { Show-ContainerStatus }
    "clean" { Clean-Docker $Service }
    "shell" { Open-Shell $Service }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

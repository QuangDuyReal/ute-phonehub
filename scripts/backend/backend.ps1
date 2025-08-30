# UTE PhoneHub - Backend Management Script
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help",
    [Parameter(Mandatory=$false)]
    [string]$Server = "jetty"  # jetty hoặc tomcat
)

$PROJECT_ROOT = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$BACKEND_PATH = Join-Path $PROJECT_ROOT "backend"
$WAR_FILE = Join-Path $BACKEND_PATH "target\ute-phonehub.war"

function Show-Help {
    Write-Host "BACKEND MANAGEMENT" -ForegroundColor Yellow
    Write-Host "==================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help    - Show this help" -ForegroundColor Gray
    Write-Host "  build   - Build with Maven" -ForegroundColor Gray
    Write-Host "  run     - Build and run with embedded server" -ForegroundColor Gray
    Write-Host "  clean   - Clean Maven cache" -ForegroundColor Gray
    Write-Host "  test    - Run tests" -ForegroundColor Gray
    Write-Host ""
    Write-Host "SERVER OPTIONS:" -ForegroundColor Yellow
    Write-Host "  -Server jetty   - Use Jetty for development (default)" -ForegroundColor Gray
    Write-Host "  -Server tomcat  - Use Tomcat 11 for testing" -ForegroundColor Gray
    Write-Host ""
    Write-Host "EXAMPLES:" -ForegroundColor Yellow
    Write-Host "  .\backend.ps1 run" -ForegroundColor Gray
    Write-Host "  .\backend.ps1 run -Server jetty" -ForegroundColor Gray
    Write-Host "  .\backend.ps1 run -Server tomcat" -ForegroundColor Gray
    Write-Host ""
}

function Test-Environment {
    Write-Host "Checking backend environment..." -ForegroundColor Cyan
    
    try {
        $javaVersion = java -version 2>&1 | Select-Object -First 1
        Write-Host "  Java: $javaVersion" -ForegroundColor Green
    } catch {
        Write-Host "  Java not found. Please install JDK 11+" -ForegroundColor Red
        return $false
    }
    
    try {
        $mavenVersion = mvn -version 2>&1 | Select-Object -First 1
        Write-Host "  Maven: $mavenVersion" -ForegroundColor Green
    } catch {
        Write-Host "  Maven not found. Please install Maven" -ForegroundColor Red
        return $false
    }
    
    if (!(Test-Path $BACKEND_PATH)) {
        Write-Host "  Backend folder not found: $BACKEND_PATH" -ForegroundColor Red
        return $false
    }
    Write-Host "  Backend folder: $BACKEND_PATH" -ForegroundColor Green
    
    $pomFile = Join-Path $BACKEND_PATH "pom.xml"
    if (!(Test-Path $pomFile)) {
        Write-Host "  pom.xml not found" -ForegroundColor Red
        return $false
    }
    Write-Host "  pom.xml found" -ForegroundColor Green
    
    return $true
}

function Build-Project {
    Write-Host "BUILDING BACKEND..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $BACKEND_PATH
    
    Write-Host "Maven clean compile package..." -ForegroundColor Cyan
    mvn clean compile package -DskipTests
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Build successful!" -ForegroundColor Green
        Write-Host "WAR file: $WAR_FILE" -ForegroundColor Green
    } else {
        Write-Host "Build failed!" -ForegroundColor Red
    }
}

function Test-Project {
    Write-Host "RUNNING TESTS..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $BACKEND_PATH
    
    Write-Host "Maven test..." -ForegroundColor Cyan
    mvn test
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Tests passed!" -ForegroundColor Green
    } else {
        Write-Host "Tests failed!" -ForegroundColor Red
    }
}

function Clean-Project {
    Write-Host "CLEANING BACKEND..." -ForegroundColor Yellow
    
    Set-Location $BACKEND_PATH
    
    Write-Host "Maven clean..." -ForegroundColor Cyan
    mvn clean
    
    Write-Host "Cleaned!" -ForegroundColor Green
}

function Run-Backend {
    Write-Host "RUNNING BACKEND..." -ForegroundColor Yellow
    
    if (!(Test-Environment)) {
        return
    }
    
    Set-Location $BACKEND_PATH
    
    # Build first if WAR doesn't exist
    if (!(Test-Path $WAR_FILE)) {
        Write-Host "WAR file not found. Building first..." -ForegroundColor Yellow
        Build-Project
        if ($LASTEXITCODE -ne 0) {
            Write-Host "Build failed. Cannot run backend." -ForegroundColor Red
            return
        }
    }
    
    if ($Server.ToLower() -eq "tomcat") {
        Write-Host "Starting backend server with Tomcat 11 Maven Plugin..." -ForegroundColor Cyan
        Write-Host "Backend will be available at: http://localhost:8080/ute-phonehub/" -ForegroundColor Green
        Write-Host "Press Ctrl+C to stop the server" -ForegroundColor Yellow
        Write-Host ""
        
        # Use Tomcat 11 Maven plugin
        mvn tomcat11:run
    } else {
        Write-Host "Starting backend server with Jetty Maven Plugin..." -ForegroundColor Cyan
        Write-Host "Backend will be available at: http://localhost:8080/ute-phonehub/" -ForegroundColor Green
        Write-Host "Press Ctrl+C to stop the server" -ForegroundColor Yellow
        Write-Host ""
        
        # Use Jetty Maven plugin (supports Jakarta EE)
        mvn jetty:run
    }
}

# Main execution
switch ($Action.ToLower()) {
    "help" { Show-Help }
    "build" { Build-Project }
    "run" { Run-Backend }
    "test" { Test-Project }
    "clean" { Clean-Project }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

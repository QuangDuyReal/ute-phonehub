# UTE PhoneHub - Utilities Management Script
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help"
)

$PROJECT_ROOT = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)

function Show-Help {
    Write-Host "UTILITIES MANAGEMENT" -ForegroundColor Yellow
    Write-Host "===================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help        - Show this help" -ForegroundColor Gray
    Write-Host "  setup       - Setup development environment" -ForegroundColor Gray
    Write-Host "  clean       - Clean all build artifacts" -ForegroundColor Gray
    Write-Host "  reset       - Reset entire project" -ForegroundColor Gray
    Write-Host "  check       - Check system requirements" -ForegroundColor Gray
    Write-Host "  update      - Update dependencies" -ForegroundColor Gray
    Write-Host "  backup      - Backup project" -ForegroundColor Gray
    Write-Host "  restore     - Restore project from backup" -ForegroundColor Gray
    Write-Host "  test        - Run all tests" -ForegroundColor Gray
    Write-Host "  lint        - Run code linting" -ForegroundColor Gray
    Write-Host "  format      - Format code" -ForegroundColor Gray
    Write-Host ""
    Write-Host "EXAMPLES:" -ForegroundColor Yellow
    Write-Host "  .\utils.ps1 check" -ForegroundColor Gray
    Write-Host "  .\utils.ps1 setup" -ForegroundColor Gray
    Write-Host "  .\utils.ps1 clean" -ForegroundColor Gray
    Write-Host ""
}

function Check-SystemRequirements {
    Write-Host "CHECKING SYSTEM REQUIREMENTS..." -ForegroundColor Yellow
    Write-Host ""
    
    $allGood = $true
    
    # Java
    Write-Host "Java:" -ForegroundColor Cyan
    try {
        $javaVersion = java -version 2>&1 | Select-Object -First 1
        Write-Host "  ✅ $javaVersion" -ForegroundColor Green
    } catch {
        Write-Host "  ❌ Java not found (Required: JDK 11+)" -ForegroundColor Red
        $allGood = $false
    }
    
    # Maven
    Write-Host "Maven:" -ForegroundColor Cyan
    try {
        $mavenVersion = mvn -version 2>&1 | Select-Object -First 1
        Write-Host "  ✅ $mavenVersion" -ForegroundColor Green
    } catch {
        Write-Host "  ❌ Maven not found" -ForegroundColor Red
        $allGood = $false
    }
    
    # Node.js
    Write-Host "Node.js:" -ForegroundColor Cyan
    try {
        $nodeVersion = node --version 2>&1
        Write-Host "  ✅ Node.js $nodeVersion" -ForegroundColor Green
    } catch {
        Write-Host "  ❌ Node.js not found (Required: 16+)" -ForegroundColor Red
        $allGood = $false
    }
    
    # npm
    Write-Host "npm:" -ForegroundColor Cyan
    try {
        $npmVersion = npm --version 2>&1
        Write-Host "  ✅ npm $npmVersion" -ForegroundColor Green
    } catch {
        Write-Host "  ❌ npm not found" -ForegroundColor Red
        $allGood = $false
    }
    
    # Git
    Write-Host "Git:" -ForegroundColor Cyan
    try {
        $gitVersion = git --version 2>&1
        Write-Host "  ✅ $gitVersion" -ForegroundColor Green
    } catch {
        Write-Host "  ❌ Git not found" -ForegroundColor Red
        $allGood = $false
    }
    
    # Docker (Optional)
    Write-Host "Docker:" -ForegroundColor Cyan
    try {
        $dockerVersion = docker --version 2>&1
        Write-Host "  ✅ $dockerVersion (Optional)" -ForegroundColor Green
    } catch {
        Write-Host "  ⚠️  Docker not found (Optional for containerization)" -ForegroundColor Yellow
    }
    
    # MySQL (Optional)
    Write-Host "MySQL:" -ForegroundColor Cyan
    try {
        $mysqlVersion = mysql --version 2>&1
        Write-Host "  ✅ $mysqlVersion (Optional)" -ForegroundColor Green
    } catch {
        Write-Host "  ⚠️  MySQL client not found (Optional for database)" -ForegroundColor Yellow
    }
    
    Write-Host ""
    if ($allGood) {
        Write-Host "✅ All required tools are installed!" -ForegroundColor Green
    } else {
        Write-Host "❌ Some required tools are missing. Please install them." -ForegroundColor Red
    }
    
    return $allGood
}

function Setup-Environment {
    Write-Host "SETTING UP DEVELOPMENT ENVIRONMENT..." -ForegroundColor Yellow
    
    if (!(Check-SystemRequirements)) {
        Write-Host "Please install missing requirements first." -ForegroundColor Red
        return
    }
    
    Set-Location $PROJECT_ROOT
    
    # Setup backend
    Write-Host "Setting up backend..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "backend")
    mvn clean compile
    
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Backend setup failed!" -ForegroundColor Red
        return
    }
    
    # Setup frontend
    Write-Host "Setting up frontend..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "frontend")
    npm install
    
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Frontend setup failed!" -ForegroundColor Red
        return
    }
    
    # Create environment files if not exist
    $envLocal = Join-Path $PROJECT_ROOT "frontend\.env.local"
    if (!(Test-Path $envLocal)) {
        Write-Host "Creating .env.local file..." -ForegroundColor Cyan
        @"
# Local development environment variables
REACT_APP_API_BASE_URL=http://localhost:8080/ute-phonehub
REACT_APP_ENV=local
REACT_APP_DEBUG=true
"@ | Out-File -FilePath $envLocal -Encoding UTF8
    }
    
    Write-Host "✅ Development environment setup completed!" -ForegroundColor Green
    Write-Host ""
    Write-Host "Next steps:" -ForegroundColor Yellow
    Write-Host "  1. Run backend:  .\scripts\backend\backend.ps1 run" -ForegroundColor Gray
    Write-Host "  2. Run frontend: .\scripts\frontend\frontend.ps1 dev" -ForegroundColor Gray
}

function Clean-Project {
    Write-Host "CLEANING PROJECT..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    # Clean backend
    Write-Host "Cleaning backend..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "backend")
    mvn clean
    
    # Clean frontend
    Write-Host "Cleaning frontend..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "frontend")
    
    if (Test-Path "node_modules") {
        Remove-Item -Recurse -Force "node_modules"
        Write-Host "  Removed node_modules" -ForegroundColor Gray
    }
    
    if (Test-Path "build") {
        Remove-Item -Recurse -Force "build"
        Write-Host "  Removed build" -ForegroundColor Gray
    }
    
    if (Test-Path "dist") {
        Remove-Item -Recurse -Force "dist"
        Write-Host "  Removed dist" -ForegroundColor Gray
    }
    
    # Clean logs
    $logsPath = Join-Path $PROJECT_ROOT "logs"
    if (Test-Path $logsPath) {
        Remove-Item -Recurse -Force $logsPath
        Write-Host "  Removed logs" -ForegroundColor Gray
    }
    
    # Clean temp files
    Get-ChildItem -Path $PROJECT_ROOT -Recurse -Name "*.log" | Remove-Item -Force
    Get-ChildItem -Path $PROJECT_ROOT -Recurse -Name "*.tmp" | Remove-Item -Force
    
    Write-Host "✅ Project cleaned successfully!" -ForegroundColor Green
}

function Reset-Project {
    Write-Host "RESETTING PROJECT..." -ForegroundColor Yellow
    Write-Host "⚠️  This will clean everything and reset to fresh state!" -ForegroundColor Red
    
    $confirmation = Read-Host "Are you sure? Type 'yes' to continue"
    if ($confirmation -ne "yes") {
        Write-Host "Operation cancelled." -ForegroundColor Yellow
        return
    }
    
    Clean-Project
    Setup-Environment
    
    Write-Host "✅ Project reset completed!" -ForegroundColor Green
}

function Update-Dependencies {
    Write-Host "UPDATING DEPENDENCIES..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    # Update backend dependencies
    Write-Host "Updating backend dependencies..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "backend")
    mvn clean compile
    
    # Update frontend dependencies
    Write-Host "Updating frontend dependencies..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "frontend")
    npm update
    
    Write-Host "✅ Dependencies updated!" -ForegroundColor Green
}

function Backup-Project {
    Write-Host "BACKING UP PROJECT..." -ForegroundColor Yellow
    
    $timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
    $backupName = "ute-phonehub-backup-$timestamp.zip"
    $backupPath = Join-Path (Split-Path $PROJECT_ROOT -Parent) $backupName
    
    Write-Host "Creating backup: $backupPath" -ForegroundColor Cyan
    
    # Exclude build artifacts and dependencies
    $excludePatterns = @(
        "*/node_modules/*",
        "*/target/*",
        "*/build/*",
        "*/dist/*",
        "*/.git/*",
        "*/logs/*",
        "*.log",
        "*.tmp"
    )
    
    try {
        Compress-Archive -Path $PROJECT_ROOT -DestinationPath $backupPath -Force
        Write-Host "✅ Backup created: $backupPath" -ForegroundColor Green
    } catch {
        Write-Host "❌ Backup failed: $($_.Exception.Message)" -ForegroundColor Red
    }
}

function Run-AllTests {
    Write-Host "RUNNING ALL TESTS..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    # Backend tests
    Write-Host "Running backend tests..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "backend")
    mvn test
    
    $backendTestResult = $LASTEXITCODE
    
    # Frontend tests
    Write-Host "Running frontend tests..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "frontend")
    npm test -- --coverage --passWithNoTests --watchAll=false
    
    $frontendTestResult = $LASTEXITCODE
    
    Write-Host ""
    if ($backendTestResult -eq 0 -and $frontendTestResult -eq 0) {
        Write-Host "✅ All tests passed!" -ForegroundColor Green
    } else {
        Write-Host "❌ Some tests failed!" -ForegroundColor Red
    }
}

function Run-Linting {
    Write-Host "RUNNING CODE LINTING..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    # Frontend linting
    Write-Host "Linting frontend code..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "frontend")
    
    if (Test-Path "node_modules") {
        npm run lint 2>$null
        if ($LASTEXITCODE -eq 0) {
            Write-Host "  ✅ Frontend lint passed" -ForegroundColor Green
        } else {
            Write-Host "  ❌ Frontend lint issues found" -ForegroundColor Red
        }
    } else {
        Write-Host "  ⚠️  Run 'npm install' first" -ForegroundColor Yellow
    }
    
    # Backend linting (if configured)
    Write-Host "Backend linting..." -ForegroundColor Cyan
    Write-Host "  ⚠️  Configure Checkstyle or SpotBugs for Java linting" -ForegroundColor Yellow
}

function Format-Code {
    Write-Host "FORMATTING CODE..." -ForegroundColor Yellow
    
    Set-Location $PROJECT_ROOT
    
    # Frontend formatting
    Write-Host "Formatting frontend code..." -ForegroundColor Cyan
    Set-Location (Join-Path $PROJECT_ROOT "frontend")
    
    if (Test-Path "node_modules") {
        npm run format 2>$null
        if ($LASTEXITCODE -eq 0) {
            Write-Host "  ✅ Frontend code formatted" -ForegroundColor Green
        } else {
            Write-Host "  ❌ Frontend formatting failed" -ForegroundColor Red
        }
    } else {
        Write-Host "  ⚠️  Run 'npm install' first" -ForegroundColor Yellow
    }
    
    # Backend formatting
    Write-Host "Backend formatting..." -ForegroundColor Cyan
    Write-Host "  ⚠️  Configure google-java-format or similar for Java formatting" -ForegroundColor Yellow
}

# Main execution
Set-Location $PROJECT_ROOT

switch ($Action.ToLower()) {
    "help" { Show-Help }
    "check" { Check-SystemRequirements }
    "setup" { Setup-Environment }
    "clean" { Clean-Project }
    "reset" { Reset-Project }
    "update" { Update-Dependencies }
    "backup" { Backup-Project }
    "restore" { 
        Write-Host "Restore functionality not implemented yet" -ForegroundColor Yellow
        Write-Host "Please restore manually from backup ZIP file" -ForegroundColor Gray
    }
    "test" { Run-AllTests }
    "lint" { Run-Linting }
    "format" { Format-Code }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

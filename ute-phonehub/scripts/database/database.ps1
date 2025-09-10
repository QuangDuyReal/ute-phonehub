# UTE PhoneHub - Database Management Script
param(
    [Parameter(Mandatory=$false)]
    [string]$Action = "help",
    [Parameter(Mandatory=$false)]
    [string]$Environment = "local"  # local, dev, prod
)

$PROJECT_ROOT = Split-Path -Parent (Split-Path -Parent $PSScriptRoot)
$SQL_PATH = Join-Path $PROJECT_ROOT "database"

function Show-Help {
    Write-Host "DATABASE MANAGEMENT" -ForegroundColor Yellow
    Write-Host "===================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "ACTIONS:" -ForegroundColor Yellow
    Write-Host "  help      - Show this help" -ForegroundColor Gray
    Write-Host "  status    - Check database connection" -ForegroundColor Gray
    Write-Host "  create    - Create database and tables" -ForegroundColor Gray
    Write-Host "  migrate   - Run migrations" -ForegroundColor Gray
    Write-Host "  seed      - Insert sample data" -ForegroundColor Gray
    Write-Host "  backup    - Backup database" -ForegroundColor Gray
    Write-Host "  restore   - Restore database" -ForegroundColor Gray
    Write-Host "  reset     - Reset database (drop + create)" -ForegroundColor Gray
    Write-Host ""
    Write-Host "ENVIRONMENTS:" -ForegroundColor Yellow
    Write-Host "  -Environment local  - Local MySQL/PostgreSQL (default)" -ForegroundColor Gray
    Write-Host "  -Environment dev    - Development database" -ForegroundColor Gray
    Write-Host "  -Environment prod   - Production database" -ForegroundColor Gray
    Write-Host ""
    Write-Host "EXAMPLES:" -ForegroundColor Yellow
    Write-Host "  .\database.ps1 status" -ForegroundColor Gray
    Write-Host "  .\database.ps1 create -Environment local" -ForegroundColor Gray
    Write-Host "  .\database.ps1 seed -Environment dev" -ForegroundColor Gray
    Write-Host ""
}

function Get-DatabaseConfig {
    param([string]$Env)
    
    switch ($Env.ToLower()) {
        "local" {
            return @{
                Host = "localhost"
                Port = "3306"
                Database = "ute_phonehub"
                Username = "root"
                Password = "123456"
                Type = "mysql"
            }
        }
        "dev" {
            return @{
                Host = "localhost"
                Port = "3306"
                Database = "ute_phonehub_dev"
                Username = "root"
                Password = "123456"
                Type = "mysql"
            }
        }
        "prod" {
            return @{
                Host = "production-server"
                Port = "3306"
                Database = "ute_phonehub_prod"
                Username = "admin"
                Password = "secure_password"
                Type = "mysql"
            }
        }
        default {
            Write-Host "Unknown environment: $Env" -ForegroundColor Red
            return $null
        }
    }
}

function Test-DatabaseConnection {
    param([hashtable]$Config)
    
    Write-Host "Testing database connection..." -ForegroundColor Cyan
    Write-Host "  Host: $($Config.Host):$($Config.Port)" -ForegroundColor Gray
    Write-Host "  Database: $($Config.Database)" -ForegroundColor Gray
    Write-Host "  User: $($Config.Username)" -ForegroundColor Gray
    
    try {
        # Test MySQL connection
        if ($Config.Type -eq "mysql") {
            mysql -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) -e "SELECT 1;" 2>$null
            if ($LASTEXITCODE -eq 0) {
                Write-Host "  ✅ Connection successful!" -ForegroundColor Green
                return $true
            }
        }
        
        Write-Host "  ❌ Connection failed!" -ForegroundColor Red
        return $false
    } catch {
        Write-Host "  ❌ MySQL client not found. Please install MySQL client." -ForegroundColor Red
        return $false
    }
}

function Create-Database {
    param([hashtable]$Config)
    
    Write-Host "CREATING DATABASE..." -ForegroundColor Yellow
    
    if (!(Test-DatabaseConnection $Config)) {
        Write-Host "Cannot connect to database server!" -ForegroundColor Red
        return
    }
    
    Write-Host "Creating database: $($Config.Database)" -ForegroundColor Cyan
    
    # Create database
    $createDbSql = "CREATE DATABASE IF NOT EXISTS $($Config.Database) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
    mysql -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) -e $createDbSql
    
    # Run schema scripts if exist
    $schemaFile = Join-Path $SQL_PATH "schema.sql"
    if (Test-Path $schemaFile) {
        Write-Host "Running schema.sql..." -ForegroundColor Cyan
        mysql -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) $Config.Database < $schemaFile
    }
    
    Write-Host "Database created successfully!" -ForegroundColor Green
}

function Run-Migration {
    param([hashtable]$Config)
    
    Write-Host "RUNNING MIGRATIONS..." -ForegroundColor Yellow
    
    $migrationPath = Join-Path $SQL_PATH "migrations"
    if (!(Test-Path $migrationPath)) {
        Write-Host "No migrations folder found: $migrationPath" -ForegroundColor Yellow
        return
    }
    
    $sqlFiles = Get-ChildItem -Path $migrationPath -Filter "*.sql" | Sort-Object Name
    
    foreach ($file in $sqlFiles) {
        Write-Host "Running migration: $($file.Name)" -ForegroundColor Cyan
        mysql -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) $Config.Database < $file.FullName
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "  ✅ $($file.Name) completed" -ForegroundColor Green
        } else {
            Write-Host "  ❌ $($file.Name) failed" -ForegroundColor Red
        }
    }
}

function Seed-Database {
    param([hashtable]$Config)
    
    Write-Host "SEEDING DATABASE..." -ForegroundColor Yellow
    
    $seedFile = Join-Path $SQL_PATH "seed.sql"
    if (!(Test-Path $seedFile)) {
        Write-Host "No seed.sql file found: $seedFile" -ForegroundColor Yellow
        return
    }
    
    Write-Host "Running seed data..." -ForegroundColor Cyan
    mysql -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) $Config.Database < $seedFile
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Seed data inserted successfully!" -ForegroundColor Green
    } else {
        Write-Host "Seed data insertion failed!" -ForegroundColor Red
    }
}

function Backup-Database {
    param([hashtable]$Config)
    
    Write-Host "BACKING UP DATABASE..." -ForegroundColor Yellow
    
    $timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
    $backupPath = Join-Path $SQL_PATH "backups"
    
    if (!(Test-Path $backupPath)) {
        New-Item -ItemType Directory -Path $backupPath -Force | Out-Null
    }
    
    $backupFile = Join-Path $backupPath "$($Config.Database)_$timestamp.sql"
    
    Write-Host "Creating backup: $backupFile" -ForegroundColor Cyan
    mysqldump -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) $Config.Database > $backupFile
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Backup created successfully!" -ForegroundColor Green
        Write-Host "Backup location: $backupFile" -ForegroundColor Green
    } else {
        Write-Host "Backup failed!" -ForegroundColor Red
    }
}

function Reset-Database {
    param([hashtable]$Config)
    
    Write-Host "RESETTING DATABASE..." -ForegroundColor Yellow
    Write-Host "⚠️  This will drop and recreate the database!" -ForegroundColor Red
    
    $confirmation = Read-Host "Are you sure? Type 'yes' to continue"
    if ($confirmation -ne "yes") {
        Write-Host "Operation cancelled." -ForegroundColor Yellow
        return
    }
    
    # Drop database
    Write-Host "Dropping database: $($Config.Database)" -ForegroundColor Cyan
    $dropDbSql = "DROP DATABASE IF EXISTS $($Config.Database);"
    mysql -h $Config.Host -P $Config.Port -u $Config.Username -p$($Config.Password) -e $dropDbSql
    
    # Recreate
    Create-Database $Config
}

# Main execution
$config = Get-DatabaseConfig $Environment
if ($config -eq $null) {
    exit 1
}

switch ($Action.ToLower()) {
    "help" { Show-Help }
    "status" { Test-DatabaseConnection $config }
    "create" { Create-Database $config }
    "migrate" { Run-Migration $config }
    "seed" { Seed-Database $config }
    "backup" { Backup-Database $config }
    "restore" { 
        Write-Host "Restore functionality not implemented yet" -ForegroundColor Yellow
        Write-Host "Please restore manually using: mysql -u user -p database < backup_file.sql" -ForegroundColor Gray
    }
    "reset" { Reset-Database $config }
    default { 
        Write-Host "Invalid action: $Action" -ForegroundColor Red
        Show-Help 
    }
}

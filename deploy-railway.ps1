# UTE PhoneHub - Deploy to Railway Script
# Chạy script này để deploy lên Railway.app

Write-Host "🚀 UTE PhoneHub - Railway Deployment Script" -ForegroundColor Green
Write-Host "=============================================" -ForegroundColor Green

# Check if Railway CLI is installed
Write-Host "📋 Checking Railway CLI..." -ForegroundColor Yellow
if (Get-Command railway -ErrorAction SilentlyContinue) {
    Write-Host "✅ Railway CLI found" -ForegroundColor Green
} else {
    Write-Host "❌ Railway CLI not found. Installing..." -ForegroundColor Red
    npm install -g @railway/cli
}

# Login check
Write-Host "🔐 Checking Railway login..." -ForegroundColor Yellow
railway whoami 2>$null
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Not logged in to Railway. Please login first:" -ForegroundColor Red
    Write-Host "   railway login" -ForegroundColor Cyan
    exit 1
} else {
    Write-Host "✅ Logged in to Railway" -ForegroundColor Green
}

# Get project info
Write-Host ""
Write-Host "📝 Project Information:" -ForegroundColor Blue
Write-Host "   Repository: ute-phonehub" -ForegroundColor Cyan
Write-Host "   Backend: Java Servlet + Tomcat" -ForegroundColor Cyan
Write-Host "   Frontend: ReactJS + Nginx" -ForegroundColor Cyan
Write-Host ""

# Choice menu
Write-Host "🎯 What do you want to deploy?" -ForegroundColor Yellow
Write-Host "   1. Backend only" -ForegroundColor Cyan
Write-Host "   2. Frontend only" -ForegroundColor Cyan  
Write-Host "   3. Both Backend & Frontend" -ForegroundColor Cyan
Write-Host "   4. Exit" -ForegroundColor Cyan

$choice = Read-Host "Enter your choice (1-4)"

switch ($choice) {
    "1" {
        Write-Host "🔨 Deploying Backend..." -ForegroundColor Green
        Set-Location backend
        railway up
        Write-Host "✅ Backend deployment initiated!" -ForegroundColor Green
    }
    "2" {
        Write-Host "🔨 Deploying Frontend..." -ForegroundColor Green
        Set-Location frontend
        railway up
        Write-Host "✅ Frontend deployment initiated!" -ForegroundColor Green
    }
    "3" {
        Write-Host "🔨 Deploying Backend first..." -ForegroundColor Green
        Set-Location backend
        railway up
        
        Write-Host "🔨 Deploying Frontend..." -ForegroundColor Green
        Set-Location ../frontend
        railway up
        Write-Host "✅ Both deployments initiated!" -ForegroundColor Green
    }
    "4" {
        Write-Host "👋 Goodbye!" -ForegroundColor Yellow
        exit 0
    }
    default {
        Write-Host "❌ Invalid choice!" -ForegroundColor Red
        exit 1
    }
}

Write-Host ""
Write-Host "🎉 Deployment process completed!" -ForegroundColor Green
Write-Host "📊 Check your Railway dashboard for deployment status:" -ForegroundColor Yellow
Write-Host "   https://railway.app/dashboard" -ForegroundColor Cyan

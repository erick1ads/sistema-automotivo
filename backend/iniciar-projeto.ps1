$ErrorActionPreference = "Stop"

# Configura o Java 21
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.13.0.3"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

# Verifica o Java
Write-Host "Verificando Java 21..."
java -version

# Configura o acesso ao MySQL
$env:DB_USERNAME = "root"
$senha = Read-Host "Digite a senha do MySQL" -AsSecureString
$env:DB_PASSWORD = [System.Net.NetworkCredential]::new("", $senha).Password

# Inicia o Sistema Automotivo pelo Maven Wrapper
Write-Host "Iniciando Sistema Automotivo..."
& "$PSScriptRoot\mvnw.cmd" spring-boot:run "-Dspring-boot.run.profiles=mysql"
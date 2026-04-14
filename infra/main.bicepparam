using './main.bicep'

param location = 'swedencentral'
param environmentName = 'prod'
param postgresAdminLogin = 'catalog'
param postgresAdminPassword = ''        // GitHub Secrets から注入
param azureOpenAiEndpoint = ''          // GitHub Secrets から注入
param azureOpenAiApiKey = ''            // GitHub Secrets から注入
param azureOpenAiDeploymentName = 'text-embedding-3-large'

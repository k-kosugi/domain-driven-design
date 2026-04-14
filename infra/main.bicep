param location string = resourceGroup().location
param environmentName string
param postgresAdminLogin string

@secure()
param postgresAdminPassword string

@secure()
param azureOpenAiEndpoint string

@secure()
param azureOpenAiApiKey string

param azureOpenAiDeploymentName string = 'text-embedding-3-large'

var prefix = 'catalog-${environmentName}'

module acr 'modules/acr.bicep' = {
  params: {
    location: location
    acrName: replace('${prefix}acr', '-', '')
  }
}

module postgresql 'modules/postgresql.bicep' = {
  params: {
    location: location
    serverName: '${prefix}-postgres'
    adminLogin: postgresAdminLogin
    adminPassword: postgresAdminPassword
  }
}

module keyvault 'modules/keyvault.bicep' = {
  params: {
    location: location
    keyVaultName: '${prefix}-kv'
    azureOpenAiEndpoint: azureOpenAiEndpoint
    azureOpenAiApiKey: azureOpenAiApiKey
    postgresConnectionString: 'jdbc:postgresql://${postgresql.outputs.fqdn}:5432/catalog?sslmode=require'
    postgresUsername: postgresAdminLogin
    postgresPassword: postgresAdminPassword
  }
}

module containerapp 'modules/containerapp.bicep' = {
  params: {
    location: location
    appName: '${prefix}-app'
    acrLoginServer: acr.outputs.loginServer
    keyVaultName: keyvault.outputs.keyVaultName
    azureOpenAiDeploymentName: azureOpenAiDeploymentName
  }
}

output acrLoginServer string = acr.outputs.loginServer
output containerAppFqdn string = containerapp.outputs.fqdn
output keyVaultName string = keyvault.outputs.keyVaultName

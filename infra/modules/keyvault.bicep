param location string
param keyVaultName string

@secure()
param azureOpenAiEndpoint string

@secure()
param azureOpenAiApiKey string

@secure()
param postgresConnectionString string

@secure()
param postgresUsername string

@secure()
param postgresPassword string

resource keyVault 'Microsoft.KeyVault/vaults@2023-07-01' = {
  name: keyVaultName
  location: location
  properties: {
    sku: {
      family: 'A'
      name: 'standard'
    }
    tenantId: tenant().tenantId
    enableRbacAuthorization: true
    enableSoftDelete: true
    softDeleteRetentionInDays: 7
  }
}

resource secretOpenAiEndpoint 'Microsoft.KeyVault/vaults/secrets@2023-07-01' = {
  name: 'azure-openai-endpoint'
  parent: keyVault
  properties: {
    value: azureOpenAiEndpoint
  }
}

resource secretOpenAiApiKey 'Microsoft.KeyVault/vaults/secrets@2023-07-01' = {
  name: 'azure-openai-api-key'
  parent: keyVault
  properties: {
    value: azureOpenAiApiKey
  }
}

resource secretPostgresConnectionString 'Microsoft.KeyVault/vaults/secrets@2023-07-01' = {
  name: 'spring-datasource-url'
  parent: keyVault
  properties: {
    value: postgresConnectionString
  }
}

resource secretPostgresUsername 'Microsoft.KeyVault/vaults/secrets@2023-07-01' = {
  name: 'spring-datasource-username'
  parent: keyVault
  properties: {
    value: postgresUsername
  }
}

resource secretPostgresPassword 'Microsoft.KeyVault/vaults/secrets@2023-07-01' = {
  name: 'spring-datasource-password'
  parent: keyVault
  properties: {
    value: postgresPassword
  }
}

output keyVaultName string = keyVault.name
output keyVaultUri string = keyVault.properties.vaultUri

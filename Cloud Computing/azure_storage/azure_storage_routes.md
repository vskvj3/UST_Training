#### Azure CMD, creating new table
```powershell
$storageName = "storage account name"

$storageAccountKey = "storage account key"

$context = New-AzStorageContext -StorageAccountName $StorageName -StorageAccountKey $storageAccountKey

$tableName = "newTableName"

New-AzStorageTable -Name $tableName -Context $context
```
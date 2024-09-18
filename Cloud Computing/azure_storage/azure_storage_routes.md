#### Azure CMD, creating new table
```powershell
$storageName = "storage account name"

$storageAccountKey = "storage account key"

# add context
$context = New-AzStorageContext -StorageAccountName $StorageName -StorageAccountKey $storageAccountKey

$tableName = "newTableName"

# create  new table
New-AzStorageTable -Name $tableName -Context $context

# select the table
$cloudTable = (Get-AzStorageTable -Name $tableName -Context $context).CloudTable

$partitionKey1 = "partition1"
$partitionKey2 = "partition2"

# insert items into table
Add-AzTableRow -table $cloudTable -partitionKey $partitionKey1 -rowKey ("CA") -property @{"username"="Uday"; "userid"=1}

Add-AzTableRow -table $cloudTable -partitionKey $partitionKey2 -rowKey ("LA") -property @{"username"="Elias"; "userid"=4}

Add-AzTableRow -table $cloudTable -partitionKey $partitionKey2 -rowKey ("FL") -property @{"username"="Issac"; "userid"=5}

Add-AzTableRow -table $cloudTable -partitionKey $partitionKey2 -rowKey ("CA") -property @{"username"="Anu"; "userid"=8}

# show all records
Get-AzTableRow -table $cloudTable | ft
```
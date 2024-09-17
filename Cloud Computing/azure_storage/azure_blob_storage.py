from azure.storage.blob import BlobServiceClient, BlobClient, ContainerClient
from dotenv import load_dotenv
import os

load_dotenv()

connection_str = os.getenv("AZURE_CN_STR")
account_key = os.getenv("AZURE_ACCOUNT_KEY")
account_name = os.getenv("AZURE_ACCOUNT_NAME")

# blob client from connection string
blob_storage_client = BlobServiceClient.from_connection_string(connection_str)

# or blob client from account name and credential
blobClient = BlobServiceClient(account_url=f"https://{account_name}.blob.core.windows.net/", 
                               credential=account_key)

# Access container and list blobs
container_name = "azureblobcontainer285"
container_client = blob_storage_client.get_container_client(container_name)

# upload to blob
local_file_path = r"C:\Users\Administrator\Downloads\transformed_sales_data.json"
with open(local_file_path, 'rb') as fileobj:
    container_client.upload_blob("sales_data.json", fileobj, overwrite=True)
    print(f"Blob uploaded")

# list all blobs in container
blob_list = container_client.list_blob_names()

print("blob list:")
for blob in blob_list:
    print(blob)
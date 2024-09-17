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

# list all blobs
blob_list = container_client.list_blob_names()

for blob in blob_list:
    print(blob)

# create a container if it does not exists
def create_container(container_name):
    container_client = blob_storage_client.get_container_client(container_name)
    try:
        container_client.create_container()
        print(f"Container created: {container_name}")
    except Exception as e:
        print(f"Container creation failed: {e}")

def upload_blob(container_name, blob_name, data):
    blob_client = blobClient.get_blob_client(container=container_name, 
                                             blob=blob_name)
    try:
        blob_client.upload_blob(data=data, overwrite=True)
        print("Blob uploaded successfully")
    except Exception as e:
        print(f"Blob upload failed: {e}")

def delete_blob(container_name, blob_name):
    blob_client = blobClient.get_blob_client(container=container_name, 
                                             blob=blob_name)
    
    try:
        blob_client.delete_blob()
        print("Blob deleted successfully")
    except Exception as e:
        print(f"Blob deletion failed: {e}")

def download_blob(container_name, blob_name, download_file_path):
    blob_client = blobClient.get_blob_client(container=container_name, 
                                             blob=blob_name)

    try:
        with open(download_file_path, "wb") as fileobj:
            fileobj.write(blob_client.download_blob().readall())
        print("Blob downloaded successfully")

    except Exception as e:
        print(f"Blob downloading failed: {e}")

if __name__ == "__main__":
    create_container("azurecontainer")
    upload_blob("azurecontainer", "blogfile.txt", "Hola!, this is an sample of blob upload")
    download_blob("azurecontainer", 
                  "blogfile.txt", 
                  r"C:\Users\Administrator\Documents\UST_Training\Cloud Computing\azure_storage\blogfile.txt")
    delete_blob("azurecontainer", "blogfile.txt")
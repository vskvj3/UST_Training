import json
from azure.cosmos import CosmosClient, PartitionKey, exceptions
from dotenv import load_dotenv
import os

load_dotenv()

endpoint = os.getenv('AZURE_ENDPOINT')
key = os.getenv('AZURE_KEY')
DATABASE_NAME = 'bank-azuredb'
CONTAINER_NAME = 'customer-container'

client = CosmosClient(endpoint, key)

database = client.get_database_client(DATABASE_NAME)

container = database.get_container_client(CONTAINER_NAME)

customer_id = 'ust10010'
partition_key = 'India'

try: 
    container.delete_item(customer_id, partition_key=partition_key)
    
except  exceptions.CosmosHttpResponseError as E:
    print(f'Error: ${E.message}')
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

customer_id = 'ust10009'
partition_key = 'Canada'

try:
    item = container.read_item(customer_id, partition_key=partition_key)
    print(item)
    item["name"] = 'visakh'
    
    response = container.replace_item(item=customer_id, body=item)
    print(response)
    
except  exceptions.CosmosHttpResponseError as E:
    print(f'Error: ${E.message}')
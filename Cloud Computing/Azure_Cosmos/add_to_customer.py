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

input_file = open ('customer.json')
json_array = json.load(input_file)

try:
    for item in json_array:
        container.create_item(body=item)
    
except  exceptions.CosmosHttpResponseError as E:
    print(f'Error: ${E.message}')
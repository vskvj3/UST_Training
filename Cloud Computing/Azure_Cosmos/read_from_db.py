import json
from azure.cosmos import CosmosClient, PartitionKey, exceptions
from dotenv import load_dotenv
import os

load_dotenv()

endpoint = os.getenv('AZURE_ENDPOINT')
key = os.getenv('AZURE_KEY')
DATABASE_NAME = 'bank-azuredb'
CONTAINER_NAME = 'bankcontainer'

client = CosmosClient(endpoint, key)

database = client.get_database_client(DATABASE_NAME)

container = database.get_container_client(CONTAINER_NAME)


query = 'SELECT * FROM c'

try:
    items = container.query_items(
        query = query,
        enable_cross_partition_query = True
    )
    result = [item for item in items]

except  exceptions.CosmosHttpResponseError as E:
    print(f'Error: ${E.message}')

with open("output.json", "w") as outfile:
    json.dump(result, outfile, indent=4)
from dotenv import load_dotenv
import os
import json
import pandas as pd
import base64
from requests import post, get
import csv

load_dotenv()

client_id = os.getenv("CID")
client_secret = os.getenv("SECRET")

def get_token():
    auth_string = client_id + ":" + client_secret
    auth_bytes = auth_string.encode("utf-8")
    auth_base64 = str(base64.b64encode(auth_bytes), "utf-8")

    url = "https://accounts.spotify.com/api/token"
    headers = {
        "Authorization": "Basic " + auth_base64,
        "Content-Type": "application/x-www-form-urlencoded"
    }

    data = {"grant_type": "client_credentials"}

    result = post(url, headers=headers, data=data)
    json_result = json.loads(result.content)
    token = json_result["access_token"]

    return token

def get_auth_header(token):
    return {"Authorization": "Bearer " + token}
    
    

import random

def getRandomSearch():
    # Una lista de todos los caracteres que se pueden elegir.
    characters = 'abcdefghijklmnopqrstuvwxyz'
    
    # Obtiene un carácter aleatorio de la cadena de caracteres.
    randomCharacter = random.choice(characters)
    randomSearch = ''
    
    # Coloca el carácter comodín al principio, o al principio y al final, de forma aleatoria.
    if random.randint(0, 1) == 0:
        randomSearch = randomCharacter + '%'
    else:
        randomSearch = '%' + randomCharacter + '%'
    
    return randomSearch

def search():
    token = get_token()
    auth_header = get_auth_header(token)
    url = "https://api.spotify.com/v1/search"
    randomSearch = getRandomSearch()
    randomOffset = random.randint(0, 1000)
    params = {
        "q": randomSearch,
        "type": "track",
        "limit": 50,
        "offset": randomOffset,
    }
    response = get(url, headers=auth_header, params=params)
    json_response = json.loads(response.content)
    return json_response

#json to pandas dataframe
def json_to_df(json_response):
    tracks = json_response["tracks"]
    items = tracks["items"]
    df = pd.DataFrame(items)
    return df


json_response = search()
df = json_to_df(json_response)
print(df.head())
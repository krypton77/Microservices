from aiogram import types, executor, Dispatcher, Bot
import requests
import json
from config import TOKEN

bot = Bot(token=TOKEN)
dp = Dispatcher(bot)

url = "http://172.20.10.4:8444/"

def data_recieving(url):
    response = requests.get(url+"get/allPersons")

    my_bytes_value = response.content

    my_json = my_bytes_value.decode('utf8').replace("'", '"')
    # Load the JSON to a Python list & dump it back out as formatted JSON
    rc_data = json.loads(my_json)
    s = json.dumps(rc_data, indent=4, sort_keys=True)
    print(s)
    return rc_data

def data_id_recieving(url, body):
    response = requests.post(url+"get/byId", data = body)
    my_bytes_value = response.content
    my_json = my_bytes_value.decode('utf8').replace("'", '"')
    print(my_json)
    id_data = json.loads(my_json)
    return id_data

@dp.message_handler(commands=['start'])
async def start(message: types.Message):
    markup = types.InlineKeyboardMarkup(row_width=1)
    item_get_data = types.InlineKeyboardButton('Get information about all user',callback_data='get_data')
    markup.add(item_get_data)

    await bot.send_message(message.chat.id,'The bot receives data about the person \n\n\n If you want to get info about specific person, send id of the person', reply_markup=markup)


@dp.callback_query_handler(lambda call: True)
async def answer(call):
    if call.data == 'get_data':
        rc_data = data_recieving(url)
        for person in rc_data['Persons']:
            text = 'Name: {name} \n Age: {age} \n Address: {address} \n Profession: {profession}'.format(profession=person['profession'], name=person['name'], age=person['age'], address=person['address'])
            await bot.send_message(call.message.chat.id, text)

@dp.message_handler()
async def get_id(msg: types.Message):
    body = '{"id":"' + msg.text + '"}'
    id_data = data_id_recieving(url, body)
    person = id_data['Persons']

    if len(id_data['Persons']) == 1:
        await bot.send_message(msg.from_user.id, 'Id not found')
    else:
        text = 'Name: {name} \n Age: {age} \n Address: {address} \n Profession: {profession}'.format(profession=person['profession'], name=person['name'], age=person['age'], address=person['address'])
        await bot.send_message(msg.from_user.id, text)

executor.start_polling(dp)

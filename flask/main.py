from config import DevConfig
from flask import Flask, abort, jsonify, request
import json


app = Flask(__name__)
app.config.from_object(DevConfig)

tasks = [
    {
        'id': 1,
        'title': 'Hi',
        'description': 'Say Hi to everyone',
        'done': True
    },{
        'id': 2,
        'title': 'Implement Kotlin Code',
        'description': 'Implement Kotlin code about...',
        'done': False
    }
]


@app.route('/api/v1/task/', methods=['POST'])
def create_task():
    _json = json.loads(request.data)
    if not _json or not 'title' in _json:
        abort(400)
    task = {
        'id': tasks[-1]['id'] + 1,
        'title': _json['title'],
        'description': _json.get('description', ''),
        'done': False
    }
    tasks.append(task)
    return jsonify({'tasks': [tasks[-1]]}), 201

@app.route('/api/v1/tasks', methods=['GET'])
def get_tasks():
    return jsonify({'tasks': tasks})

@app.route('/api/v1/tasks/<int:task_id>', methods=['GET'])
def get_task(task_id):
    task = list(filter(lambda t: t['id'] == task_id, tasks))
    if len(task) == 0:
        abort(404)
    return jsonify({'task': task})



if __name__ == '__main__':
    app.run()

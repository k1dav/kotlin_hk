from flask_script import Manager, Server
from main import app

manager = Manager(app)
manager.add_command('runserver', Server(host="0.0.0.0", port=5000))

@manager.shell
def make_shell_context():
    return dict(app=app)

if __name__ == '__main__':
    manager.run()

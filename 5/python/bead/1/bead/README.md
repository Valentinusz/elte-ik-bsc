# Python-React-IOT-dashboard
An IOT device dashboard with a REST API mode with FastAPI REST and frontend made with React.

The application is unfinished.

## Setup


### Server
To setup the backend, run the following command in the `server` directory:

```shell
pip install -r requirements.txt
```

After that execute `main.py` with:

```shell
python.exe -m uvicorn main:app
```

(Or `py`/`python3` if `python` doesn't work.)

### Client
To setup the frontend run the following command in the `client` directory (required Node.js):

```shell
npm i
```

After that to start the dev server for the client run:

```shell
npm run dev
```

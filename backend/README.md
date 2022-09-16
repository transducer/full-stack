# software.rooijakkers.full-stack

Backend for vending machine API.

## Getting Started

1. Start the application: `lein run`
1. Go to [localhost:8080](http://localhost:8080/) to see: `Hello World!`
1. Read your app's source code at src/software/rooijakkers/full_stack/service.clj. Explore the docs of functions
   that define routes and responses.
1. Run your app's tests with `make test`. Read the tests at test/software/rooijakkers/full_stack/service_test.clj.
1. Run only unit tests using `make unit-test`
1. Run only integration tests using `make integration-test`
1. Learn more! See the [Links section below](#links).

## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).

## Developing your service

1. Start a new REPL: `lein repl`
1. Start your service in dev-mode: `(def dev-serv (run-dev))`
1. Connect your editor to the running REPL session.
   Re-evaluated code will be seen immediately in the service.

### [Docker](https://www.docker.com/) container support

1. Configure your service to accept incoming connections (edit service.clj and add  ::http/host "0.0.0.0")
1. Build an uberjar of your service: `lein uberjar`
1. Build a Docker image: `sudo docker build -t software.rooijakkers.full-stack .`
1. Run your Docker image: `docker run -p 8080:8080 software.rooijakkers.full-stack`

or

```sh
make
```

## Links
* [Other Pedestal examples](http://pedestal.io/samples)

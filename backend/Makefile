.PHONY: test all

all: prod

test:
	lein test

unit-test:
	lein test :unit

integration-test:
	lein test :integration

prod:
	lein uberjar
	docker build . -t full-stack-backend

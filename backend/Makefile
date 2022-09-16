.PHONY: test all

all: prod

test:
	lein test

prod:
	lein uberjar
	docker build . -t full-stack-backend

.PHONY: all

all: build run

build:
	pushd backend && make; popd
	pushd frontend && make; popd

test:
	pushd backend && make test; popd
	pushd frontend && make test; popd

run:
	docker-compose up

.PHONY: all

all: build run

build:
	pushd frontend && make; popd
	pushd backend && make; popd
run:
	docker-compose up

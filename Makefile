build:
	pushd frontend && make prod; popd
run:
	docker-compose up

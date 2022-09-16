.PHONY: test all

all: prod

css:
	npm run build-css

deps:
	npm install
	npm run build-css

watch:
	npx shadow-cljs watch dev

test: deps
	npx shadow-cljs compile ci
	npm run karma

prod:
	npm install
	npm run build-css
	npx shadow-cljs release prod
	docker build . -t full-stack-frontend

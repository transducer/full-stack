.PHONY: test

all: deps

css:
	npm run build-css

deps:
	npm install
	npm run build-css

watch:
	npx shadow-cljs watch dev

prod:
	npm install
	npm run build-css
	npx shadow-cljs release prod

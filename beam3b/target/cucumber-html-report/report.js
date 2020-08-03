$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/github.feature");
formatter.feature({
  "name": "Github website tests",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Search files in the repository",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Scenario3"
    },
    {
      "name": "@All"
    }
  ]
});
formatter.step({
  "name": "User launches the browser and enters desired URL",
  "keyword": "Given "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "repo",
        "fileName",
        "occurrence"
      ]
    },
    {
      "cells": [
        "bootstrap",
        ".babelrc.js",
        "1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Search files in the repository",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Scenario3"
    },
    {
      "name": "@All"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User launches the browser and enters desired URL",
  "keyword": "Given "
});
formatter.match({
  "location": "StpSearchRepo.userLaunchesTheBrowserAndEntersDesiredURL()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});
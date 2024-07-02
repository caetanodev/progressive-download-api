#!/bin/bash

LIQUIBASE_DIR="./liquibase-4.23.0"
DB_URL=jdbc:mysql://localhost:3306/progressive_download?createDatabaseIfNotExist=true
DATABASE_PROPERTIES="liquibase.properties"
CHANGELOG="changelog.yml"

"$LIQUIBASE_DIR"/liquibase --url="$DB_URL" --defaultsFile="$DATABASE_PROPERTIES" --changelog-file="$CHANGELOG" update

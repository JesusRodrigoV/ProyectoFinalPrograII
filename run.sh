#!/bin/bash
set -e

echo "==> Levantando MySQL..."
docker compose up -d db

echo "==> Esperando a que MySQL esté listo..."
until docker compose exec db mysqladmin ping -h localhost --silent 2>/dev/null; do
    sleep 2
done

echo "==> Ejecutando aplicación..."
java -jar target/banco-mundial-1.0.0.jar
